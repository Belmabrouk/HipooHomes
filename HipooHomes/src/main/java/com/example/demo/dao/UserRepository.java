package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);
	public User  findByUsername(String username);
	public long count();
	
}
