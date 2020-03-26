package com.dhony.learning.services;



import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
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
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	
	private List<LocationStats> allStats = new ArrayList<>();
	/***
	 * fetchVirusData make http calls 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	
	@PostConstruct
	@Scheduled(cron = "*  *  1  *  *  *")
	public List<LocationStats> fetchVirusData() throws IOException, InterruptedException {
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
		    locationStat.setState(record.get("Province/State"));
		    locationStat.setCountry(record.get("Country/Region"));
		    locationStat.setLatitude(Double.parseDouble(record.get("Lat")));
		    locationStat.setLongitude(Double.parseDouble(record.get("Long")));
		    locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size() -1)));
		   
		   
		    newStats.add(locationStat);
	}
		
		this.allStats = newStats;
	
	
	     return allStats;
	}
}
