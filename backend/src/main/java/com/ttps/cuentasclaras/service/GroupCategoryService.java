package com.ttps.cuentasclaras.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.GroupCategoryCreateDTO;
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

	public boolean groupCategoryExists(GroupCategoryCreateDTO groupCategoryRequest) {
		return groupCategoryRepository.existsByName(groupCategoryRequest.getName());
	}

	public void createGroupCategory(GroupCategoryCreateDTO groupCategoryRequest) {
		GroupCategory newGroupCategory = new GroupCategory(groupCategoryRequest.getName(),
				groupCategoryRequest.getBase64Image());
		groupCategoryRepository.save(newGroupCategory);
	}

	public GroupCategory updateGroupCategory(Integer id, GroupCategory groupCategoryRequest) {
		try {
			Optional<GroupCategory> searchedGroupCategory = groupCategoryRepository.findById(id);
			GroupCategory groupCategory = searchedGroupCategory
					.orElseThrow(() -> new ResourceNotFoundException("Group Category not found with ID: " + id));

			if (groupCategoryRequest.getName() != null) {
				groupCategory.setName(groupCategoryRequest.getName());
			}
			if (groupCategoryRequest.getBase64Image() != null) {
				groupCategory.setBase64Image(groupCategoryRequest.getBase64Image());
			}

			groupCategoryRepository.save(groupCategory);
			return groupCategory;
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public GroupCategory deleteGroupCategory(Integer id) {
		GroupCategory searchedGroupCategory = groupCategoryRepository.findById(id).orElse(null);
		if (searchedGroupCategory != null) {
			groupCategoryRepository.deleteById(id);
			return searchedGroupCategory;
		}
		return null;
	}
}
