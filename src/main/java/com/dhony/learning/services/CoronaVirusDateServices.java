package com.dhony.learning.services;



import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLStreamHandler;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dhony.learning.models.LocationStats;


@Service
public class CoronaVirusDateServices {
	
	private static String CONFIRMEDCASES_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String DEATHCASES_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
//	private static String RECOVERED_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	
	private String[] urls = {CONFIRMEDCASES_VIRUS_DATA_URL,DEATHCASES_VIRUS_DATA_URL };
////	private List<String> urlList = new ArrayList<>();
	
	
	private int caseNumber = 0;
	private List<LocationStats> ConfirmedAllStats = new ArrayList<>();
	private List<LocationStats> DeathAllStats = new ArrayList<>();

	/***
	 * fetchVirusData make http calls 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	
	@PostConstruct
	@Scheduled(cron = "*  *  1  *  *  *")
	public List<LocationStats> getAllConfirmedData() throws IOException, InterruptedException{
		
//		//count ++;
//		//fetchVirusData(DEATHCASES_VIRUS_DATA_URL);
//		//fetchVirusData(RECOVERED_VIRUS_DATA_URL);
//		for (String url : urls) {
//			fetchVirusData(url);
//			count ++;
//		}
		caseNumber = 0;
		fetchVirusData(CONFIRMEDCASES_VIRUS_DATA_URL);
		System.out.println(ConfirmedAllStats);
		
		return ConfirmedAllStats;
	}
	public List<LocationStats> getAllDeathData() throws IOException, InterruptedException{
		caseNumber = 1;
		fetchVirusData(DEATHCASES_VIRUS_DATA_URL);
		System.out.println(DeathAllStats);
		return DeathAllStats;
	}
	

	public List<LocationStats> fetchVirusData(String VIRUS_DATA_URL) throws IOException, InterruptedException {
		 List<LocationStats> newStats = new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL))
				.build();
		
		HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
		
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
			locationStat.setCountry(record.get("Country/Region"));
		    locationStat.setState(record.get("Province/State"));
		    locationStat.setLatitude(Double.parseDouble(record.get("Lat")));
		    locationStat.setLongitude(Double.parseDouble(record.get("Long")));
		    
		    if(caseNumber == 0) {
		    	int latesConfirmed = Integer.parseInt(record.get(record.size() -1));
		    	int previousConfirmed = Integer.parseInt(record.get(record.size() -2));
		    	
		    	locationStat.setLatestTotalCases(latesConfirmed);
		    	locationStat.setPreviousDayConfiCases(previousConfirmed);
				   
		    }else if(caseNumber == 1){
		    	int latesDeath = Integer.parseInt(record.get(record.size() -1));
		    	int previousDeath = Integer.parseInt(record.get(record.size() -2));
		    	locationStat.setLatestDeathCases(latesDeath);
		    	locationStat.setPreviousDayDeathCases(previousDeath);
//		    }else {
//		    	locationStat.setLatestRecoveredCases(Integer.parseInt(record.get(record.size() -1)));
		    }
		    
		   
		    newStats.add(locationStat);
	}
		if(caseNumber == 0 ) {
			this.ConfirmedAllStats = newStats;
			return ConfirmedAllStats;
		}
		else if(caseNumber == 1){
			this.DeathAllStats =  newStats;
			return DeathAllStats;
		} else {
			return ConfirmedAllStats;
		}
		
	
	
	     
	}
	
	
}