package com.dhony.learning.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhony.learning.models.LocationStats;
import com.dhony.learning.services.CoronaVirusDateServices;

@RestController
@RequestMapping("/api")
public class statsController {
	
	@Autowired
	private final CoronaVirusDateServices covid19Service;
	
	public statsController(CoronaVirusDateServices covid19Service) { 
		this.covid19Service = covid19Service;
	}
	
	
	@GetMapping("/confirmedStats")
	public List<LocationStats> getAllConfirmed() throws IOException, InterruptedException{
	
		 System.out.println(covid19Service.getAllConfirmedData());
		return covid19Service.getAllConfirmedData();
	}
	
	@GetMapping("/deathStats")
	public List<LocationStats> getAllDeathConfirmed() throws IOException, InterruptedException{
		 System.out.println(covid19Service.getAllDeathData());
		return covid19Service.getAllDeathData();
	}
	
	
	@GetMapping("/totalConfirmed")
	public int getTotalConfirmed() throws IOException, InterruptedException{
		List<LocationStats> allstats = covid19Service.getAllConfirmedData();
		int totalConfirmed = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		 System.out.println(covid19Service.getAllConfirmedData());
		return totalConfirmed;
	}
	
	@GetMapping("/totalDeath")
	public int getTotalDeathConfirmed() throws IOException, InterruptedException{
		List<LocationStats> allstats = covid19Service.getAllDeathData();
		int totalDeath = allstats.stream().mapToInt(stat -> stat.getLatestDeathCases()).sum();
		 System.out.println(covid19Service.getAllDeathData());
		return totalDeath;
	}
	
	

}
