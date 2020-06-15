package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Property;
import com.example.demo.entities.Province;

public interface PropertyService {
	public Property AddProperty (Property p);
	public Property updateProperty(String id  );
	public List<Property> GetAll();
	public long getPropertiesCount();
	public List<Property> getPropertiesByProvince(Province province);
	public Property getProperty(String id);
	public void bookProperty(String username , String propertyId);
	public List<Property> addFavourite(String username , String propertyId);
	public List<Property> getRelatedProperties (String propertyId);
}
