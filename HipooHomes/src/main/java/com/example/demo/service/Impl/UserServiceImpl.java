package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Property;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User addUser(User userToBeAdded) {

		User u = userRepo.findByEmail(userToBeAdded.getEmail());
		if (u != null)
			throw new RuntimeException("User already exists");
		else {
			userToBeAdded.setPassword(bCryptPasswordEncoder.encode(userToBeAdded.getPassword()));
			userToBeAdded.setRole(Role.USER);
			String username = userToBeAdded.getFirstName() + "." + userToBeAdded.getLastName();
			userToBeAdded.setUsername(username);
			userToBeAdded.setSelectedProperty(false);
		}
		return userRepo.save(userToBeAdded);
	}

	@Override
	public User updateUser(String id, User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> GetAll() {

		return userRepo.findAll();
	}

	@Override
	public User findUserByEmail(String email) {

		return userRepo.findByEmail(email);
	}
 
	@Override
	public User findUserByUsername(String username) {

		return userRepo.findByUsername(username);
	}

	@Override
	public Long getUsersCount() {

		return userRepo.count();
	}

	@Override
	public void deleteUser(String id) {
		
		 userRepo.deleteById(id);
		
	}

	@Override
	public User selectProperty(String id, Property property) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
