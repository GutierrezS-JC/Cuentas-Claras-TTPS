package com.ttps.cuentasclaras.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.GroupCategory;
import com.ttps.cuentasclaras.repository.GroupCategoryRepository;

@Service
public class GroupCategoryService {

	@Autowired
	GroupCategoryRepository groupCategoryRepository;

	public List<GroupCategory> getAllGroupCategories() {
		return groupCategoryRepository.findAll();
	}

	public GroupCategory getGroupCategory(Integer id) {
		try {
			Optional<GroupCategory> searchedGroupCategory = groupCategoryRepository.findById(id);
			GroupCategory groupCategory = searchedGroupCategory
					.orElseThrow(() -> new ResourceNotFoundException("Group Category not found with ID: " + id));
			return groupCategory;
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean groupCategoryExists(GroupCategory groupCategoryRequest) {
		return groupCategoryRepository.existsByName(groupCategoryRequest.getName());
	}

	public void createGroupCategory(GroupCategory groupCategoryRequest) {
		GroupCategory newGroupCategory = new GroupCategory(groupCategoryRequest.getName());
		groupCategoryRepository.save(newGroupCategory);
	}
}
