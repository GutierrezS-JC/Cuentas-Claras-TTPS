package com.ttps.cuentasclaras.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.GroupDTO;
import com.ttps.cuentasclaras.dto.IdDTO;
import com.ttps.cuentasclaras.dto.SpendingCategoryDTO;
import com.ttps.cuentasclaras.dto.SpendingCreateDTO;
import com.ttps.cuentasclaras.dto.SpendingDTO;
import com.ttps.cuentasclaras.dto.SpendingUpdateDTO;
import com.ttps.cuentasclaras.dto.SpendingUserDTO;
import com.ttps.cuentasclaras.dto.UserAmountDTO;
import com.ttps.cuentasclaras.dto.UserDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.Group;
import com.ttps.cuentasclaras.model.Spending;
import com.ttps.cuentasclaras.model.SpendingCategory;
import com.ttps.cuentasclaras.model.SpendingUser;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.repository.GroupRepository;
import com.ttps.cuentasclaras.repository.SpendingCategoryRepository;
import com.ttps.cuentasclaras.repository.SpendingRepository;

@Service
public class SpendingService {

	@Autowired
	SpendingRepository spendingRepository;

	@Autowired
	SpendingCategoryRepository spendingCategoryRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	UserService userService;

	@Autowired
	SpendingCategoryService spendingCategoryService;

	@Autowired
	GroupService groupService;

	public List<SpendingDTO> getAllSpendings() {
		List<Spending> spendings = spendingRepository.findAll();
		List<SpendingDTO> listResult = new ArrayList<>();
		for (Spending spending : spendings) {
			listResult.add(mapSpendingDTO(spending));
		}
		return listResult;
	}

	public SpendingDTO mapSpendingDTO(Spending spending) {
		UserDTO owner = userService.mapUserDto(spending.getOwner());
		SpendingCategoryDTO category = spendingCategoryService.mapSpendingCategory(spending.getSpendingCategory());
		GroupDTO group = groupService.mapGroupDTO(spending.getGroup());

		List<SpendingUserDTO> users = this.mapSpendingUserDTO(spending.getUsers());
		return new SpendingDTO(spending.getId(), spending.getName(), spending.getDescription(),
				spending.getTotalAmount(), spending.getCreatedAt(), spending.getEndingDate(),
				spending.getProofOfPayment(), spending.getRecurrence(), spending.getDivision(), owner, category, group,
				users);
	}

	private List<SpendingUserDTO> mapSpendingUserDTO(Set<SpendingUser> users) {
		List<SpendingUserDTO> listResult = new ArrayList<>();
		for (SpendingUser spendingUser : users) {
			UserDTO user = userService.mapUserDto(spendingUser.getUser());
			listResult.add(new SpendingUserDTO(spendingUser.getId(), user, spendingUser.getAmount(),
					spendingUser.getCreated_at(), spendingUser.getUpdated_at()));
		}
		return listResult;
	}

	public SpendingDTO getSpending(Integer id) {
		try {
			Optional<Spending> searchedSpending = spendingRepository.findById(id);
			Spending spending = searchedSpending
					.orElseThrow(() -> new ResourceNotFoundException("Spending not found with ID: " + id));
			return mapSpendingDTO(spending);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SpendingDTO updateSpending(Integer id, SpendingUpdateDTO spendingRequest) {
		try {
			Optional<Spending> searchedSpending = spendingRepository.findById(id);
			Spending spending = searchedSpending
					.orElseThrow(() -> new ResourceNotFoundException("Spending not found with ID: " + id));

			if (spendingRequest.getName() != null) {
				spending.setName(spendingRequest.getName());
			}
			if (spendingRequest.getDescription() != null) {
				spending.setDescription(spendingRequest.getDescription());
			}
			if (spendingRequest.getTotalAmount() != null) {
				spending.setTotalAmount(spendingRequest.getTotalAmount());
			}
			if (spendingRequest.getEndingDate() != null) {
				spending.setEndingDate(spendingRequest.getEndingDate());
			}
			if (spendingRequest.getProofOfPayment() != null) {
				spending.setProofOfPayment(spendingRequest.getProofOfPayment());
			}
			if (spendingRequest.getSpendingCategoryId() != null) {
				SpendingCategory category = spendingCategoryRepository.findById(spendingRequest.getSpendingCategoryId())
						.orElse(null);

				// Elimino gasto de la <lista de categorias de gastos> en SpendingCategory
				spending.getSpendingCategory().getSpendings().remove(spending);

				// Reemplazo la categoria de gasto en entidad Spending (se pisa el anterior y se
				// inserta este)
				spending.setSpendingCategory(category);

				// Agrego gasto en la lista de categorias en SpendingCategory --> No se si es
				// necesario, probar.
				category.getSpendings().add(spending);
				spendingCategoryRepository.save(category);
			}

			// TODO: Agregar verificacion para agregar los ENUMS

			spendingRepository.save(spending);
			return this.mapSpendingDTO(spending);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Spending findById(Integer id) {
		return spendingRepository.findById(id).orElse(null);
	}

	public void deleteSpending(Integer id) {
		spendingRepository.deleteById(id);
	}

	public boolean spendingExists(SpendingCreateDTO spendingRequest) {
		// TODO: Modificar la verificacion
		boolean result = false;
		if (spendingRequest.getGroupId() != null) {
			if (spendingRepository.searchWithOwnerAndGroupAndCategory(spendingRequest.getOwnerId(),
					spendingRequest.getGroupId(), spendingRequest.getSpendingCategoryId()) != null) {
				result = true;

			}
		} else {
			if (spendingRepository.searchWithOwnerAndCategory(spendingRequest.getOwnerId(),
					spendingRequest.getSpendingCategoryId()) != null) {
				result = true;
			}
		}
		return result;
	}

	private Set<SpendingUser> getSpendingsUsers(List<UserAmountDTO> usersWithAmountList, Spending spending) {
		Set<SpendingUser> users = new HashSet<>();

		for (UserAmountDTO userAmount : usersWithAmountList) {
			User searchedUser = userService.findUserById(userAmount.getUserId());

			if (searchedUser != null) {
				users.add(new SpendingUser(searchedUser, spending, userAmount.getAmount()));
			}
		}

		return users;
	}

	public void createSpending(SpendingCreateDTO spendingReq) {
		User owner = userService.getUser(spendingReq.getOwnerId());
		SpendingCategory category = spendingCategoryService.existsById(spendingReq.getSpendingCategoryId());

		Spending newSpending;
		if (owner != null && category != null) {

			if (spendingReq.getGroupId() != null) {
				Group group = groupService.findById(spendingReq.getGroupId());
				newSpending = new Spending(spendingReq.getName(), spendingReq.getDescription(),
						spendingReq.getTotalAmount(), LocalDate.now(), spendingReq.getEndingDate(),
						spendingReq.getProofOfPayment(), spendingReq.getRecurrence(), spendingReq.getDivision(), owner,
						category, group);
			} else {
				newSpending = new Spending(spendingReq.getName(), spendingReq.getDescription(),
						spendingReq.getTotalAmount(), LocalDate.now(), spendingReq.getEndingDate(),
						spendingReq.getProofOfPayment(), spendingReq.getRecurrence(), spendingReq.getDivision(), owner,
						category);

			}

			Spending spendingCreated = spendingRepository.saveAndFlush(newSpending);
			Set<SpendingUser> users = this.getSpendingsUsers(spendingReq.getUsersWithAmount(), spendingCreated);
			spendingCreated.setUsers(users);
			spendingRepository.save(spendingCreated);
		}

	}

	// Usado en /getSpendings --> Group
	public List<SpendingDTO> getSpendings(Integer id) {
		Group searchedGroup = groupRepository.findById(id).orElse(null);
		if (searchedGroup != null) {
			Set<Spending> spendings = searchedGroup.getSpendings();
			List<SpendingDTO> listResponse = new ArrayList<>();

			for (Spending spending : spendings) {
				SpendingDTO sp = this.mapSpendingDTO(spending);
				listResponse.add(sp);
			}
			return listResponse;
		}
		return null;
	}

}
