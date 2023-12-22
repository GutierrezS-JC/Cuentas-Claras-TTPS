package com.ttps.cuentasclaras.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.GroupDTO;
import com.ttps.cuentasclaras.dto.GroupDetailsDTO;
import com.ttps.cuentasclaras.dto.SpendingUserDTO;
import com.ttps.cuentasclaras.dto.SpendingUserExtendDTO;
import com.ttps.cuentasclaras.dto.UserAltDTO;
import com.ttps.cuentasclaras.dto.UserDTO;
import com.ttps.cuentasclaras.dto.UserGroupsDTO;
import com.ttps.cuentasclaras.dto.UserLoginDTO;
import com.ttps.cuentasclaras.dto.UserWithGroupsDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.Group;
import com.ttps.cuentasclaras.model.SpendingUser;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<UserWithGroupsDTO> getAllUsersWithGroups() {
		List<User> users = this.getAllUsers();
		List<UserWithGroupsDTO> usersResponse = new ArrayList<>();

		for (User user : users) {
			List<GroupDTO> userGroups = new ArrayList<>();
			for (Group group : user.getGroups()) {
				userGroups.add(new GroupDTO(group.getId(), group.getName(), group.getTotalBalance(),
						group.getGroupCategory(), group.getOwner().getId()));
			}
			usersResponse.add(new UserWithGroupsDTO(user.getId(), user.getEmail(), user.getUsername(), user.getName(),
					user.getLastName(), user.getProfilepicBase64(), userGroups));
		}
		return usersResponse;
	}

	// DTO
	public UserWithGroupsDTO getUserDTO(Integer id) {
		try {
			Optional<User> searchedUser = userRepository.findById(id);
			User user = searchedUser.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

			List<GroupDTO> userGroups = new ArrayList<>();
			for (Group group : user.getGroups()) {
				userGroups.add(new GroupDTO(group.getId(), group.getName(), group.getTotalBalance(),
						group.getGroupCategory(), group.getOwner().getId()));
			}

			UserWithGroupsDTO userRes = new UserWithGroupsDTO(user.getId(), user.getEmail(), user.getUsername(),
					user.getName(), user.getLastName(), user.getProfilepicBase64(), userGroups);
			return userRes;
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	// User as defined by Model
	public User getUser(Integer id) {
		try {
			Optional<User> searchedUser = userRepository.findById(id);
			User user = searchedUser.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
			return user;
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Convert user to DTO in order to get just the necessary info
	public UserDTO mapUserDto(User user) {
		if (user != null) {
			return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getName(), user.getLastName(),
					user.getProfilepicBase64());
		}
		return null;
	}

	// Check if user is already on database - Used when creating a user
	public boolean userExist(UserDTO userRequest) {
		return userRepository.existsByUsernameOrEmail(userRequest.getUsername(), userRequest.getEmail());
	}

	// Check if user exists by ID
	public User findUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public void createUser(UserDTO userRequest) {
		User newUser = new User(userRequest.getEmail(), userRequest.getUsername(), userRequest.getPassword(),
				userRequest.getName(), userRequest.getLastName(), userRequest.getProfilepicBase64());

		userRepository.save(newUser);
	}

	public UserDTO updateUser(Integer id, UserDTO userRequest) {
		try {
			Optional<User> searchedUser = userRepository.findById(id);
			User user = searchedUser.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

			// Edit user with data from userRequest object (sent form client)
			if (userRequest.getEmail() != null) {
				user.setEmail(userRequest.getEmail());
			}
			if (userRequest.getName() != null) {
				user.setName(userRequest.getName());
			}
			if (userRequest.getLastName() != null) {
				user.setLastName(userRequest.getLastName());
			}
			if (userRequest.getUsername() != null) {
				user.setUsername(userRequest.getUsername());
			}
			if (userRequest.getProfilepicBase64() != null) {
				user.setProfilepicBase64(userRequest.getProfilepicBase64());
			}

			userRepository.save(user);
			return mapUserDto(user);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserDTO deleteUser(Integer id) {
		User searchedUser = userRepository.findById(id).orElse(null);
		if (searchedUser != null) {
			userRepository.deleteById(id);
			return mapUserDto(searchedUser);
		}
		return null;
	}

	public UserAltDTO loginUser(UserLoginDTO userRequest) {
		User searchedUser = userRepository.findByUsernameAndPassword(userRequest.getUsername(),
				userRequest.getPassword());
		if (searchedUser != null) {
			UserAltDTO userResponse = new UserAltDTO(searchedUser.getId(), searchedUser.getEmail(),
					searchedUser.getUsername(), searchedUser.getName(), searchedUser.getLastName(),
					searchedUser.getProfilepicBase64());

			return userResponse;
		}
		return null;
	}

	public List<SpendingUserDTO> mapSpendingUserDTO(User searchedUser) {
		Set<SpendingUser> spendings = searchedUser.getSpendings();
		List<SpendingUserDTO> spendingResponse = new ArrayList<>();
		for (SpendingUser spendingUser : spendings) {
			spendingResponse.add(new SpendingUserDTO(spendingUser.getId(), this.mapUserDto(searchedUser),
					spendingUser.getAmount(), spendingUser.getCreated_at(), spendingUser.getUpdated_at()));
		}
		return spendingResponse;
	}

	public UserAltDTO mapUserAlt(User user) {
		return new UserAltDTO(user.getId(), user.getEmail(), user.getUsername(), user.getName(), user.getLastName(),
				user.getProfilepicBase64());
	}

	public List<UserAltDTO> searchUser(Integer userId, String username) {
		List<UserAltDTO> response = new ArrayList<>();

		if (!username.isBlank() && username != null) {
			List<User> searched = userRepository.findTop3ByUsernameContainingAndIdNot(username, userId);
			if (!searched.isEmpty()) {
				for (User user : searched) {
					response.add(this.mapUserAlt(user));
				}
			}
		}
		return response;
	}

	public List<SpendingUserExtendDTO> mapSpendingUserExtendDTO(User searchedUser) {
		Set<SpendingUser> spendings = searchedUser.getSpendings();
		List<SpendingUserExtendDTO> spendingResponse = new ArrayList<>();
		for (SpendingUser spendingUser : spendings) {
			spendingResponse.add(new SpendingUserExtendDTO(spendingUser.getId(), this.mapUserDto(searchedUser),
					spendingUser.getAmount(), spendingUser.getCreated_at(), spendingUser.getUpdated_at(),
					spendingUser.getSpending().getId(), spendingUser.getSpending().getName(),
					spendingUser.getSpending().getDescription()));
		}
		return spendingResponse;
	}

}
