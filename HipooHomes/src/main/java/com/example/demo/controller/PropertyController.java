package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Property;
import com.example.demo.entities.Province;
import com.example.demo.entities.User;
import com.example.demo.service.PropertyService;

@RestController
public class PropertyController {

	
	@Autowired
	PropertyService propertyService ;
	
	@Autowired 
	UserRepository userRepo;
	
	@GetMapping("/properties/{province}")
	List<Property> getPropertiesByProvince(@PathVariable("province") Province province)
	{
		
		
		return propertyService.getPropertiesByProvince(province);
		
	}
	
	@GetMapping("/properties/property/{id}")
	Property getPropertyById(@PathVariable("id") String	id)
	{
		
		
		return propertyService.getProperty(id);
		
	}
	
	@PutMapping("/bookProperty/{username}/{propertyId}")
	 ResponseEntity bookProperty(@PathVariable("username") String	username, @PathVariable("propertyId") String propertyId)
	{
		if(userRepo.findByUsername(username).getSelectedProperty()==true) 
			{return new ResponseEntity<>( HttpStatus.BAD_REQUEST);}
		else {
		 propertyService.bookProperty(username, propertyId);
		 return new ResponseEntity<>( HttpStatus.OK);
		}
	}
	
	@PutMapping("/addFavourite/{username}/{propertyId}")
	 ResponseEntity addFavourite(@PathVariable("username") String	username, @PathVariable("propertyId") String propertyId)

	{
		User currentUser = userRepo.findByUsername(username);
		boolean favouriteExist = currentUser.getFavourites().stream().anyMatch(item -> propertyId.equals(item.getId()));
		System.out.println(favouriteExist);
		if (favouriteExist) 
			{return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
		
		else {
		 propertyService.addFavourite(username, propertyId);
		 return new ResponseEntity<>( HttpStatus.OK); 
		}
	}
	
	
	@GetMapping("/propertyCount")
	public long getAllPropertyCount(){

		
		return propertyService.getPropertiesCount();
	}
	
	
	@GetMapping("/properties/relatedProperties/{propertyId}")
	List<Property> getRelatedProducts(@PathVariable("propertyId") String propertyId)
	{
		
		
		return propertyService.getRelatedProperties(propertyId);
		
	}
	
}
