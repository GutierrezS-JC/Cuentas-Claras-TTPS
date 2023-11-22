package com.ttps.cuentasclaras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.cuentasclaras.dto.SpendingCreateDTO;
import com.ttps.cuentasclaras.dto.SpendingDTO;
import com.ttps.cuentasclaras.dto.SpendingUpdateDTO;
import com.ttps.cuentasclaras.model.Spending;
import com.ttps.cuentasclaras.service.SpendingService;

@RestController
@RequestMapping("/spending")
@CrossOrigin
public class SpendingController {

	@Autowired
	SpendingService spendingService;

	@GetMapping
	public ResponseEntity<List<SpendingDTO>> getAllSpendings() {
		List<SpendingDTO> spendings = spendingService.getAllSpendings();

		if (spendings.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(spendings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SpendingDTO> getSpending(@PathVariable(name = "id") Integer id) {
		SpendingDTO searchedSpending = spendingService.getSpending(id);
		if (searchedSpending == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(searchedSpending);
	}

	@PostMapping
	public ResponseEntity<SpendingDTO> createSpending(@RequestBody SpendingCreateDTO spendingRequest) {
		if (spendingService.spendingExists(spendingRequest)) {
			return new ResponseEntity<SpendingDTO>(HttpStatus.CONFLICT);
		}
		spendingService.createSpending(spendingRequest);
		return new ResponseEntity<SpendingDTO>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SpendingDTO> updateSpending(@PathVariable(name = "id") Integer id,
			@RequestBody SpendingUpdateDTO spendingRequest) {
		SpendingDTO spendingResult = spendingService.updateSpending(id, spendingRequest);
		if (spendingResult == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(spendingResult);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SpendingDTO> deleteSpending(@PathVariable(name = "id") Integer id) {
		Spending searchedSpending = spendingService.findById(id);
		if (searchedSpending == null) {
			return ResponseEntity.notFound().build();
		}
		spendingService.deleteSpending(id);
		return ResponseEntity.noContent().build();
	}
}
