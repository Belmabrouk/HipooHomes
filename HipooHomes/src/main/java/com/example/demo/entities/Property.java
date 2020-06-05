package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Property {
	
	@Id
	private String id; 
	
	private String title;
	private String description;
	
	/*
	 * Fields from API
	 */
	private String thumbnail;
	private int numPhotos;
	private String floor;
	private double price;
	private PropertyType propertyType;
	private float size;
	private int rooms;
	private int bathrooms;
	private String address;
	private Province province;
	private String municipality;
	private String district;
	private String neighborhood;
	private String latitude;
	private String longitude;
	private Boolean showAddress;
	private String url;
	private Status status;
	private double priceByArea;
	private Boolean parking;
	private boolean haslift;

	
	private boolean AC;
	private boolean fittedWardrobe;
	private boolean garden;
	private boolean pool;
	private boolean garage;
	
	private Collection<String> images= new ArrayList<>();
}
