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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.cuentasclaras.dto.SpendingUserDTO;
import com.ttps.cuentasclaras.dto.SpendingUserExtendDTO;
import com.ttps.cuentasclaras.dto.UserAltDTO;
import com.ttps.cuentasclaras.dto.UserDTO;
import com.ttps.cuentasclaras.dto.UserLoginDTO;
import com.ttps.cuentasclaras.dto.UserWithGroupsDTO;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<UserWithGroupsDTO>> getAllUsers() {
		List<UserWithGroupsDTO> users = userService.getAllUsersWithGroups();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserWithGroupsDTO> getUser(@PathVariable(name = "id") Integer id) {
		UserWithGroupsDTO searchedUser = userService.getUserDTO(id);
		if (searchedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(searchedUser);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userRequest) {
		if (userService.userExist(userRequest)) {
			return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
		}
		userService.createUser(userRequest);
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDTO userRequest) {
		UserDTO searchedUser = userService.updateUser(id, userRequest);
		if (searchedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(searchedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable(name = "id") Integer id) {
		UserDTO searchedUser = userService.deleteUser(id);
		if (searchedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserAltDTO> loginUser(@RequestBody UserLoginDTO userRequest) {
		UserAltDTO user= userService.loginUser(userRequest);
		if (user != null) {
			return ResponseEntity.accepted().body(user);
		}
		return new ResponseEntity<UserAltDTO>(HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getMySpendings")
	public ResponseEntity<List<SpendingUserDTO>> getMySpendings(@RequestParam Integer id) {
		User searchedUser = userService.findUserById(id);
		List<SpendingUserDTO> listResponse = userService.mapSpendingUserDTO(searchedUser);
		if (searchedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listResponse);
	}
	
    @GetMapping("/getMySpendingsExtended")
    public ResponseEntity<List<SpendingUserExtendDTO>> getMySpendingsExtended(@RequestParam Integer id) {
        User searchedUser = userService.findUserById(id);
        List<SpendingUserExtendDTO> listResponse = userService.mapSpendingUserExtendDTO(searchedUser);
        if (searchedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listResponse);
    }

}
