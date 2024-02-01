package com.ttps.cuentasclaras.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import com.ttps.cuentasclaras.dto.GroupCategoryCreateDTO;
import com.ttps.cuentasclaras.model.GroupCategory;
import com.ttps.cuentasclaras.service.GroupCategoryService;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/groupCategories")
@CrossOrigin
public class GroupCategoryController {

	@Autowired
	GroupCategoryService groupCategoryService;

	@GetMapping
	public ResponseEntity<List<GroupCategory>> getAllGroups() {
		List<GroupCategory> groups = groupCategoryService.getAllGroupCategories();

		if (groups.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(groups);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GroupCategory> getGroupCategory(@PathVariable(name = "id") Integer id) {
		GroupCategory searchedGroup = groupCategoryService.getGroupCategory(id);
		if (searchedGroup == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(searchedGroup);
	}

	@PostMapping
	public ResponseEntity<GroupCategory> createGroupCategory(@RequestBody GroupCategoryCreateDTO groupCategoryRequest) {
		if (groupCategoryService.groupCategoryExists(groupCategoryRequest)) {
			return new ResponseEntity<GroupCategory>(HttpStatus.CONFLICT);
		}
		groupCategoryService.createGroupCategory(groupCategoryRequest);
		return new ResponseEntity<GroupCategory>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GroupCategory> updateGroupCategory(@PathVariable(name = "id") Integer id,
			@RequestBody GroupCategory groupCategoryRequest) {
		GroupCategory groupCategoryUpdateResult = groupCategoryService.updateGroupCategory(id, groupCategoryRequest);
		if (groupCategoryUpdateResult == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(groupCategoryUpdateResult);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GroupCategory> deleteGroup(@PathVariable(name = "id") Integer id) {
		GroupCategory searchedGroupCategory = groupCategoryService.deleteGroupCategory(id);
		if (searchedGroupCategory == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
