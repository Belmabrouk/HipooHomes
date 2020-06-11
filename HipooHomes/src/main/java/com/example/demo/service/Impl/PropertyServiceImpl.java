package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PropertyRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Property;
import com.example.demo.entities.Province;
import com.example.demo.entities.User;
import com.example.demo.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	PropertyRepository propertyRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Property AddProperty(Property p) {
		propertyRepo.save(p);
		return p;
	}

	@Override
	public Property updateProperty(String id ) {
		Property property = propertyRepo.findById(id).get();
		propertyRepo.save(property);
		
		return null;
	}

	@Override
	public List<Property> GetAll() {
		return propertyRepo.findAll();
	}

	@Override
	public long getPropertiesCount() {
		
		return propertyRepo.count();
	}

	@Override
	public List<Property> getPropertiesByProvince(Province province) {
		return propertyRepo.findByProvince(province);
	}

	@Override
	public Property getProperty(String id) {
		return propertyRepo.findById(id).get();
	}

	@Override
	public void bookProperty(String username, String propertyId) {
		User currentUser = userRepo.findByUsername(username);
		Property property = propertyRepo.findById(propertyId).get();
		currentUser.setProperty(property);
		currentUser.setSelectedProperty(true);
		userRepo.save(currentUser);
		
	}

	@Override
	public List<Property> addFavourite(String username, String propertyId) {

		User currentUser = userRepo.findByUsername(username);
		Property property = propertyRepo.findById(propertyId).get();
		currentUser.getFavourites().add(property);
		userRepo.save(currentUser);
		return currentUser.getFavourites();
	}

}
