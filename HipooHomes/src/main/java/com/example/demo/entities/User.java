package com.example.demo.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
	
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Boolean selectedProperty;
	@DBRef
	private Property property;
	private Role role;
	

}
