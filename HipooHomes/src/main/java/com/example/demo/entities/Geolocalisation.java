package com.example.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geolocalisation {
	@Id
	private String id;
	private Province province;
	private Double  latitude;
	private Double  longitude;
	public Geolocalisation(Province province, Double latitude, Double longitude) {
		super();
		this.province = province;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	
}
