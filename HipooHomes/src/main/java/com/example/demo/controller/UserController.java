package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) throws Exception
	{
		
		
		
		return userService.addUser(user);
	}
	
	@GetMapping("/usersList")
	public List<User> getAllusers(){
		
		return userService.GetAll();
	}
	
	@GetMapping("/usersCount")
	public long getAllusersCount(){
		
		return userService.getUsersCount();
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
	  try {
	    userService.deleteUser(id);
	    return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	  }
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User userData) {
	  User user = userRepo.findById(id).get();
	  
	  if (user != null) {
		  user.setEmail(userData.getEmail());
		  user.setFirstName(userData.getFirstName());
		  user.setLastName(userData.getLastName());		  
		  
	    return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
}
