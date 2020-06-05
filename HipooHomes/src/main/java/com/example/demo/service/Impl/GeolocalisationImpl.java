package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GeolocalisationRepository;
import com.example.demo.entities.Geolocalisation;
import com.example.demo.service.GeolocalisationService;


@Service
public class GeolocalisationImpl implements GeolocalisationService{

	@Autowired
	GeolocalisationRepository geolocalisationRepo;
	
	@Override
	public Geolocalisation AddGeolocalisation(Geolocalisation g) {
		geolocalisationRepo.save(g);
		
		return null;
	}

	@Override
	public List<Geolocalisation> GetAll() {
		
		return geolocalisationRepo.findAll();
	}

	@Override
	public Geolocalisation findByProvince(String province) {
		// TODO Auto-generated method stub
		return null;
	}

}
