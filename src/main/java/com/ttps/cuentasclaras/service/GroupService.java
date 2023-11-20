package com.ttps.cuentasclaras.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.GroupCreateDTO;
import com.ttps.cuentasclaras.dto.GroupDTO;
import com.ttps.cuentasclaras.dto.GroupEditDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.Group;
import com.ttps.cuentasclaras.model.GroupCategory;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	UserService userService;

	@Autowired
	GroupCategoryService groupCategoryService;

	@Autowired
	GroupRepository groupRepository;

	public List<GroupDTO> getAllGroups() {
		List<Group> groups = groupRepository.findAll();
		List<GroupDTO> groupsResponse = new ArrayList<>();

		for (Group group : groups) {
			groupsResponse.add(new GroupDTO(group.getId(), group.getName(), group.getTotalBalance(),
					group.getGroupCategory().getId(), group.getOwner().getId()));
		}
		return groupsResponse;
	}

	public Group getGroup(Integer id) {
		try {
			Optional<Group> searchedGroup = groupRepository.findById(id);
			Group user = searchedGroup
					.orElseThrow(() -> new ResourceNotFoundException("Group not found with ID: " + id));
			return user;
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean groupExists(GroupCreateDTO groupRequest) {
		User owner = userService.getUser(groupRequest.getUserOwnerId());
		if (owner == null) {
			return true;
		}
		// If there's a group with the same owner (who's trying to create) and has the
		// same exact group name we return false
		return groupRepository.existsByOwnerAndName(owner, groupRequest.getName());
	}

	public boolean createGroup(GroupCreateDTO groupRequest) {
		User owner = userService.getUser(groupRequest.getUserOwnerId());
		GroupCategory groupCategory = groupCategoryService.getGroupCategory(groupRequest.getGroupCategoryId());
		if (groupCategory != null && owner != null) {
			Group newGroup = new Group(groupRequest.getName(), groupRequest.getTotalBalance(), owner, groupCategory);
			groupRepository.save(newGroup);
			return true;
		}
		return false;
	}

	public GroupEditDTO updateGroup(Integer id, GroupEditDTO groupRequest) {
		try {
			Optional<Group> searchedGroup = groupRepository.findById(id);
			Group group = searchedGroup
					.orElseThrow(() -> new ResourceNotFoundException("Group not found with ID: " + id));

			// Edit user with data from userRequest object (sent form client)
			if (groupRequest.getName() != null) {
				group.setName(groupRequest.getName());
			}
			if (groupRequest.getTotalBalance() != null) {
				group.setTotalBalance(groupRequest.getTotalBalance());
			}
			if (groupRequest.getGroupCategoryId() != null) {
				GroupCategory groupCategory = groupCategoryService.getGroupCategory(groupRequest.getGroupCategoryId());
				if (groupCategory == null) {
					throw new ResourceNotFoundException("Group Category not found");
				}
				group.setGroupCategory(groupCategory);
			}

			groupRepository.save(group);
			return new GroupEditDTO(group.getId(), group.getName(), group.getTotalBalance(),
					group.getGroupCategory().getId());
		} catch (ResourceNotFoundException e) {
			return null;
		}
	}

	public GroupDTO deleteGroup(Integer id) {
		Group searchedGroup = groupRepository.findById(id).orElse(null);
		if (searchedGroup != null) {
			groupRepository.deleteById(id);
			return new GroupDTO(searchedGroup.getId(), searchedGroup.getName(), searchedGroup.getTotalBalance(),
					searchedGroup.getGroupCategory().getId(), searchedGroup.getOwner().getId());
		}
		return null;
	}

}
