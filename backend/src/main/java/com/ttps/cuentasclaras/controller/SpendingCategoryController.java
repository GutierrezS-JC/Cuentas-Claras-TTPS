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

import com.ttps.cuentasclaras.dto.SpendingCategoryCreateDTO;
import com.ttps.cuentasclaras.dto.SpendingCategoryDTO;
import com.ttps.cuentasclaras.model.SpendingCategory;
import com.ttps.cuentasclaras.service.SpendingCategoryService;

@RestController
@RequestMapping("/spendingCategories")
@CrossOrigin
public class SpendingCategoryController {

	@Autowired
	SpendingCategoryService spendingCategoryService;

	@GetMapping
	public ResponseEntity<List<SpendingCategoryDTO>> getAllSpendingCategories() {
		List<SpendingCategoryDTO> categories = spendingCategoryService.getAllSpendingCategories();

		if (categories.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SpendingCategoryDTO> getSpendingCategory(@PathVariable(name = "id") Integer id) {
		SpendingCategoryDTO searchedCategory = spendingCategoryService.getSpendingCategory(id);
		if (searchedCategory == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(searchedCategory);
	}

	@PostMapping
	public ResponseEntity<SpendingCategory> createSpendingCategory(
			@RequestBody SpendingCategoryCreateDTO spendingCategoryRequest) {
		if (spendingCategoryService.spendingCategoryExists(spendingCategoryRequest)) {
			return new ResponseEntity<SpendingCategory>(HttpStatus.CONFLICT);
		}
		spendingCategoryService.createSpendingCategory(spendingCategoryRequest);
		return new ResponseEntity<SpendingCategory>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SpendingCategoryDTO> updateSpendingCategory(@PathVariable(name = "id") Integer id,
			@RequestBody SpendingCategory spendingCategoryRequest) {
		SpendingCategoryDTO spendingCategoryResult = spendingCategoryService.updateSpendingCategory(id, spendingCategoryRequest);
		if (spendingCategoryResult == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(spendingCategoryResult);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SpendingCategory> deleteSpendingCategory(@PathVariable(name = "id") Integer id) {
		SpendingCategory searchedSpendingCategory = spendingCategoryService.existsById(id);
		if (searchedSpendingCategory == null) {
			return ResponseEntity.notFound().build();
		}
		spendingCategoryService.deleteSpendingCategory(id);
		return ResponseEntity.noContent().build();
	}
}
