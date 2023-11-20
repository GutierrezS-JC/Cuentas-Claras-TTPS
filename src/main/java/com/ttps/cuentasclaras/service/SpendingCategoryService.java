package com.ttps.cuentasclaras.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.SpendingCategoryCreateDTO;
import com.ttps.cuentasclaras.dto.SpendingCategoryDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.SpendingCategory;
import com.ttps.cuentasclaras.repository.SpendingCategoryRepository;

@Service
public class SpendingCategoryService {

	@Autowired
	SpendingCategoryRepository spendingCategoryRepository;

	public List<SpendingCategoryDTO> getAllSpendingCategories() {
		List<SpendingCategory> listCategories = spendingCategoryRepository.findAll();
		return mapSpendingCategoryDto(listCategories);
	}

	private List<SpendingCategoryDTO> mapSpendingCategoryDto(List<SpendingCategory> listCategories) {
		List<SpendingCategoryDTO> listResult = new ArrayList<>();
		for (SpendingCategory category : listCategories) {
			listResult.add(new SpendingCategoryDTO(category.getId(), category.getName(), category.getBase64Image()));
		}
		return listResult;
	}

	public SpendingCategoryDTO getSpendingCategory(Integer id) {
		try {
			Optional<SpendingCategory> searchedSpendingCategory = spendingCategoryRepository.findById(id);
			SpendingCategory spendingCategory = searchedSpendingCategory
					.orElseThrow(() -> new ResourceNotFoundException("Spending Category not found with ID: " + id));
			return new SpendingCategoryDTO(spendingCategory.getId(), spendingCategory.getName(),
					spendingCategory.getBase64Image());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean spendingCategoryExists(SpendingCategoryCreateDTO spendingCategoryRequest) {
		return spendingCategoryRepository.existsByName(spendingCategoryRequest.getName());
	}

	public SpendingCategory existsById(Integer id) {
		return spendingCategoryRepository.findById(id).orElse(null);
	}

	public void createSpendingCategory(SpendingCategoryCreateDTO spendingCategoryRequest) {
		SpendingCategory newSpendingCategory = new SpendingCategory(spendingCategoryRequest.getName(),
				spendingCategoryRequest.getBase64Image());
		spendingCategoryRepository.save(newSpendingCategory);
	}

	public SpendingCategoryDTO updateSpendingCategory(Integer id, SpendingCategory spendingCategoryRequest) {
		try {
			Optional<SpendingCategory> searchedSpendingCategory = spendingCategoryRepository.findById(id);
			SpendingCategory spendingCategory = searchedSpendingCategory
					.orElseThrow(() -> new ResourceNotFoundException("Spending Category not found with ID: " + id));

			if (spendingCategoryRequest.getName() != null) {
				spendingCategory.setName(spendingCategoryRequest.getName());
			}
			if (spendingCategoryRequest.getBase64Image() != null) {
				spendingCategory.setBase64Image(spendingCategoryRequest.getBase64Image());
			}

			spendingCategoryRepository.save(spendingCategory);
			return new SpendingCategoryDTO(spendingCategory.getId(), spendingCategory.getName(),
					spendingCategory.getBase64Image());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteSpendingCategory(Integer id) {
		spendingCategoryRepository.deleteById(id);
	}

}
