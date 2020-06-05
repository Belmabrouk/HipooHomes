package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.example.demo.entities.Property;
import com.example.demo.entities.Province;
@RepositoryRestController
public interface PropertyRepository extends MongoRepository<Property, String> {

	public long count();
	public List<Property> findByProvince(Province province);
}
