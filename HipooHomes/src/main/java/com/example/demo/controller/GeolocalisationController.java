package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.GeolocalisationRepository;
import com.example.demo.entities.Geolocalisation;
import com.example.demo.entities.Province;

@RestController
public class GeolocalisationController {

	
	@Autowired
	GeolocalisationRepository geoRepo ;
	
	@GetMapping("/getCoordinatesByProvince/{province}")
	Geolocalisation getCoordinatesByProvince(@PathVariable("province") Province province)
	{
		
		
		return geoRepo.findByProvince(province);
		
	}
	
	@GetMapping("/getAll")
	List<Geolocalisation> getAll()
	{
		
		
		return geoRepo.findAll();
		
	}
}
