package com.example.demo.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
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
	
	Double minPrice = null;
	Double maxPrice = null;
	
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

	@Override
	public List<Property> getRelatedProperties(String propertyId) {

		
		Property currentProperty = propertyRepo.findById(propertyId).get();
		if(currentProperty.getPrice() <= 200000) {
		minPrice = currentProperty.getPrice()-25000;
		maxPrice = currentProperty.getPrice()+25000;
		}
		else if (currentProperty.getPrice() >= 200000 && currentProperty.getPrice() <= 600000) {
			minPrice = currentProperty.getPrice()-50000;
			maxPrice = currentProperty.getPrice()+50000;
		}
		else if (currentProperty.getPrice() >= 600000 && currentProperty.getPrice() <= 1000000) {
			minPrice = currentProperty.getPrice()-100000;
			maxPrice = currentProperty.getPrice()+100000;
		}
		else  {
			minPrice = currentProperty.getPrice()-200000;
			maxPrice = currentProperty.getPrice()+200000;
		}
		
		List<Property> propertiesByProvince = propertyRepo.findByProvince(currentProperty.getProvince());
		List<Property> relatedProperties = propertiesByProvince.stream()
				 .filter(property ->
				 (property.getPrice() <= maxPrice && property.getPrice() >= minPrice) &&
				 (property.getRooms() <= currentProperty.getRooms()+1 && property.getRooms() >= currentProperty.getRooms()-1) &&
				 (property.isHaslift() == currentProperty.isHaslift() || property.isPool() == currentProperty.isPool() || property.isGarage() ==currentProperty.isGarage() ||property.isGarden() ==currentProperty.isGarden() ) &&
				 (SloppyMath.haversinMeters(Double.parseDouble(property.getLatitude()), Double.parseDouble(property.getLongitude()), Double.parseDouble(currentProperty.getLatitude())	, Double.parseDouble(currentProperty.getLongitude())) <=5000)	)
				 .skip(1)
				 .collect(Collectors.toList());
		return relatedProperties;
	}

}
