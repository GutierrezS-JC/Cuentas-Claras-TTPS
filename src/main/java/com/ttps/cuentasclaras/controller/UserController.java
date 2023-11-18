package com.ttps.cuentasclaras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.repository.UserRepository;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
}
