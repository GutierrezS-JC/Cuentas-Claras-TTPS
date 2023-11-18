package com.ttps.cuentasclaras.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PaymentController {
	
	@GetMapping("/hola")
	public String ping(){
		System.out.println("Hola");
		return ":)";
	}
}
