package com.ttps.cuentasclaras.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.UserDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// DTO
	public UserDTO getUserDTO(Integer id) {
		try {
			Optional<User> searchedUser = userRepository.findById(id);
			User user = searchedUser.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
			return mapUserDto(user);
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
	private UserDTO mapUserDto(User user) {
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

}
