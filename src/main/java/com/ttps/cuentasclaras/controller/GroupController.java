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

import com.ttps.cuentasclaras.dto.GroupCreateDTO;
import com.ttps.cuentasclaras.dto.GroupDTO;
import com.ttps.cuentasclaras.dto.GroupEditDTO;
import com.ttps.cuentasclaras.dto.SpendingDTO;
import com.ttps.cuentasclaras.model.Group;
import com.ttps.cuentasclaras.service.GroupService;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {

	@Autowired
	GroupService groupService;

	@GetMapping
	public ResponseEntity<List<GroupDTO>> getAllGroups() {
		List<GroupDTO> groups = groupService.getAllGroups();

		if (groups.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(groups);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GroupDTO> getGroup(@PathVariable(name = "id") Integer id) {
		Group searchedGroup = groupService.getGroup(id);
		if (searchedGroup == null) {
			return ResponseEntity.notFound().build();
		}
		GroupDTO groupResponse = new GroupDTO(searchedGroup.getId(), searchedGroup.getName(),
				searchedGroup.getTotalBalance(), searchedGroup.getGroupCategory(), searchedGroup.getOwner().getId());
		return ResponseEntity.ok(groupResponse);
	}

	@PostMapping
	public ResponseEntity<Group> createGroup(@RequestBody GroupCreateDTO groupRequest) {
		if (groupService.groupExists(groupRequest)) {
			return new ResponseEntity<Group>(HttpStatus.CONFLICT);
		}
		if (groupService.createGroup(groupRequest)) {
			return new ResponseEntity<Group>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GroupEditDTO> updateGroup(@PathVariable(name = "id") Integer id,
			@RequestBody GroupEditDTO groupRequest) {
		GroupEditDTO groupUpdateResult = groupService.updateGroup(id, groupRequest);
		if (groupUpdateResult == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(groupUpdateResult);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GroupDTO> deleteGroup(@PathVariable(name = "id") Integer id) {
		GroupDTO searchedGroup = groupService.deleteGroup(id);
		if (searchedGroup == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
