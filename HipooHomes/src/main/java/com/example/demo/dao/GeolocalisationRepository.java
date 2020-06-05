package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Geolocalisation;
import com.example.demo.entities.Province;

@RepositoryRestResource
public interface GeolocalisationRepository extends MongoRepository<Geolocalisation, String>   {
	public Geolocalisation findByProvince(Province province);
}
