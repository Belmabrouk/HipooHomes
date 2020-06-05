package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Property;
import com.example.demo.entities.Province;
import com.example.demo.service.PropertyService;

@RestController
public class PropertyController {

	
	@Autowired
	PropertyService propertyService ;
	
	
	@GetMapping("/properties/{province}")
	List<Property> getPropertiesByProvince(@PathVariable("province") Province province)
	{
		
		
		return propertyService.getPropertiesByProvince(province);
		
	}
}
