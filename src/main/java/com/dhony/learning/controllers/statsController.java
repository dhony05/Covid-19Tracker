package com.dhony.learning.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhony.learning.models.LocationStats;
import com.dhony.learning.services.CoronaVirusDateServices;

@RestController
@RequestMapping("/api")
public class statsController {
	
	private final CoronaVirusDateServices covid19Service;
	
	public statsController(CoronaVirusDateServices covid19Service) { 
		this.covid19Service = covid19Service;
	}
	
	
	@GetMapping("/locationStats")
	public List<LocationStats> getAll() throws IOException, InterruptedException{
		 System.out.println(covid19Service.fetchVirusData());
		return covid19Service.fetchVirusData();
	}

}
