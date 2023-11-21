package com.ttps.cuentasclaras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.repository.SpendingRepository;

@Service
public class SpendingService {

	@Autowired
	SpendingRepository spendingRepository;
}
