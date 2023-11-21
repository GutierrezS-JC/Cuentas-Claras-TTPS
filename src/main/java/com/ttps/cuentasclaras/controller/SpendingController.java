package com.ttps.cuentasclaras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.cuentasclaras.service.SpendingService;

@RestController
@RequestMapping("/spending")
@CrossOrigin
public class SpendingController {

	@Autowired
	SpendingService spendingService;
}
