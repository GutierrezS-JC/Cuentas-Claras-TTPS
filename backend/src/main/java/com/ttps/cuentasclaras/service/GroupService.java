package com.ttps.cuentasclaras.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.GroupCreateDTO;
import com.ttps.cuentasclaras.dto.GroupDTO;
import com.ttps.cuentasclaras.dto.GroupDetailsDTO;
import com.ttps.cuentasclaras.dto.GroupEditDTO;
import com.ttps.cuentasclaras.dto.IdDTO;
import com.ttps.cuentasclaras.dto.InvitationDTO;
import com.ttps.cuentasclaras.dto.UserAltDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.Group;
import com.ttps.cuentasclaras.model.GroupCategory;
import com.ttps.cuentasclaras.model.Invitation;
import com.ttps.cuentasclaras.model.InvitationStatus;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.repository.GroupRepository;
import com.ttps.cuentasclaras.repository.InvitationRepository;

@Service
public class GroupService {

	@Autowired
	UserService userService;

	@Autowired
	GroupCategoryService groupCategoryService;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	InvitationRepository invitationRepository;

	public List<GroupDetailsDTO> getAllGroups() {
		List<Group> groups = groupRepository.findAll();
		List<GroupDetailsDTO> groupsResponse = new ArrayList<>();

		for (Group group : groups) {
			Set<User> members = group.getUsers();
			Set<Invitation> invitations = group.getInvitations();

			List<UserAltDTO> membersDTO = new ArrayList<>();
			for (User member : members) {
				membersDTO.add(userService.mapUserAlt(member));
			}

			List<InvitationDTO> invitationsDTO = new ArrayList<>();
			for (Invitation invitation : invitations) {
				UserAltDTO sender = userService.mapUserAlt(invitation.getSenderUser());
				UserAltDTO receiver = userService.mapUserAlt(invitation.getReceiverUser());
				invitationsDTO.add(new InvitationDTO(invitation.getId(), sender, receiver, invitation.getStatus()));
			}

			UserAltDTO owner = userService.mapUserAlt(group.getOwner());

			groupsResponse.add(new GroupDetailsDTO(group.getId(), group.getName(), group.getTotalBalance(),
					group.getGroupCategory(), owner, membersDTO, invitationsDTO));
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

			// List with members (users) IDs to be added on group creation is either empty
			// or NULL (not sent in JSON)
			if (groupRequest.getUsersIds() == null || groupRequest.getUsersIds().isEmpty()) {
				return false;
			}

			// Grupo creado y forzado a guardar para poder usar el objeto en las
			// invitaciones
			Group newGroup = new Group(groupRequest.getName(), groupRequest.getTotalBalance(), owner, groupCategory);
			Group groupCreated = groupRepository.saveAndFlush(newGroup);

			Set<Invitation> invitations = new HashSet<>();
			for (IdDTO idObject : groupRequest.getUsersIds()) {
				User userResult = userService.findUserById(idObject.getId());

				// If there's an ID in the list that returns NULL on search from DB (Doesn't
				// exist) then block below should not add it to the list.
				if (userResult != null) {
					Invitation invitation = new Invitation();
					invitation.setSenderUser(owner);
					invitation.setReceiverUser(userResult);
					invitation.setGroup(groupCreated);
					invitation.setStatus(InvitationStatus.PENDING);

					invitations.add(invitation);
				}
			}

			// Save the invitations
			invitationRepository.saveAll(invitations);

			return true;
		} else
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
			e.printStackTrace();
			return null;
		}
	}

	public GroupDTO deleteGroup(Integer id) {
		Group searchedGroup = groupRepository.findById(id).orElse(null);
		if (searchedGroup != null) {
			groupRepository.deleteById(id);
			return new GroupDTO(searchedGroup.getId(), searchedGroup.getName(), searchedGroup.getTotalBalance(),
					searchedGroup.getGroupCategory(), searchedGroup.getOwner().getId());
		}
		return null;
	}

	public GroupDTO mapGroupDTO(Group group) {
		if (group != null) {
			return new GroupDTO(group.getId(), group.getName(), group.getTotalBalance(), group.getGroupCategory(),
					group.getOwner().getId());
		}
		return null;
	}

	public Group findById(Integer id) {
		return groupRepository.findById(id).orElse(null);
	}

	public Boolean addMember(Integer userId, Group group) {
		User user = userService.findUserById(userId);
		if (user != null) {
			group.getUsers().add(user);
			groupRepository.save(group);
			return true;
		}
		return false;
	}
}
