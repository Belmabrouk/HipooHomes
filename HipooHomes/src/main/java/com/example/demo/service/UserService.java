package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Property;
import com.example.demo.entities.User;

public interface UserService {

	public User addUser (User u);
	public User updateUser(String id , User u  );
	public User findUserByEmail(String email);
	public User findUserByUsername(String username);
	public List<User> GetAll();
	public Long getUsersCount();
	public void deleteUser(String id);
	public User selectProperty(String id, Property property);
	
	
}
