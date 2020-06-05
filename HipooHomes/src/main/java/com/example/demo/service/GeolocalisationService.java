package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Geolocalisation;

public interface GeolocalisationService {

	public Geolocalisation AddGeolocalisation (Geolocalisation g);
	public List<Geolocalisation> GetAll();
	public Geolocalisation findByProvince(String province);
}
