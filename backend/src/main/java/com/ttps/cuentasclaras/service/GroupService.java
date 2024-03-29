package com.ttps.cuentasclaras.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.GroupCreateDTO;
import com.ttps.cuentasclaras.dto.GroupDTO;
import com.ttps.cuentasclaras.dto.GroupDetailsDTO;
import com.ttps.cuentasclaras.dto.GroupEditDTO;
import com.ttps.cuentasclaras.dto.IdDTO;
import com.ttps.cuentasclaras.dto.InvitationDTO;
import com.ttps.cuentasclaras.dto.UserAltDTO;
import com.ttps.cuentasclaras.dto.UserGroupsDTO;
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
//			Set<User> members = group.getUsers();
//			Set<Invitation> invitations = group.getInvitations();
//
//			List<UserAltDTO> membersDTO = new ArrayList<>();
//			for (User member : members) {
//				membersDTO.add(userService.mapUserAlt(member));
//			}
//
//			List<InvitationDTO> invitationsDTO = new ArrayList<>();
//			for (Invitation invitation : invitations) {
//				UserAltDTO sender = userService.mapUserAlt(invitation.getSenderUser());
//				UserAltDTO receiver = userService.mapUserAlt(invitation.getReceiverUser());
//				invitationsDTO.add(new InvitationDTO(invitation.getId(), sender, receiver, invitation.getStatus()));
//			}
//
//			UserAltDTO owner = userService.mapUserAlt(group.getOwner());

			GroupDetailsDTO groupRes = this.mapGroupDetailsDTO(group);
			groupsResponse.add(groupRes);
		}
		return groupsResponse;
	}

	public GroupDetailsDTO mapGroupDetailsDTO(Group group) {
		Set<User> members = group.getUsers();
		Set<Invitation> invitations = group.getInvitations();

		List<UserAltDTO> membersDTO = new ArrayList<>();
		for (User member : members) {
			membersDTO.add(userService.mapUserAlt(member));
		}

		List<Invitation> invitationsList = new ArrayList<>(invitations);
		invitationsList.sort(Comparator.comparing(Invitation::getId));

		List<InvitationDTO> invitationsDTO = new ArrayList<>();
		for (Invitation invitation : invitationsList) {
			UserAltDTO sender = userService.mapUserAlt(invitation.getSenderUser());
			UserAltDTO receiver = userService.mapUserAlt(invitation.getReceiverUser());
			invitationsDTO.add(new InvitationDTO(invitation.getId(), sender, receiver, invitation.getStatus()));
		}

		UserAltDTO owner = userService.mapUserAlt(group.getOwner());
		return new GroupDetailsDTO(group.getId(), group.getName(), group.getTotalBalance(), group.getGroupCategory(),
				group.getDescription(), owner, membersDTO, invitationsDTO);
	}

	public List<GroupDetailsDTO> mapListGroupDetailsDTO(Set<Group> groups) {
		List<GroupDetailsDTO> responseList = new ArrayList<>();
		for (Group group : groups) {
			responseList.add(this.mapGroupDetailsDTO(group));
		}
		return responseList;
	}

	public Group getGroup(Integer id) {
		try {
			Optional<Group> searchedGroup = groupRepository.findById(id);
			Group group = searchedGroup
					.orElseThrow(() -> new ResourceNotFoundException("Group not found with ID: " + id));
			return group;
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
			Group newGroup = new Group(groupRequest.getName(), groupRequest.getTotalBalance(),
					groupRequest.getDescription(), owner, groupCategory);
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

	public GroupDetailsDTO updateGroup(Integer groupId, Integer userId, GroupEditDTO groupRequest) {
		try {
			Optional<Group> searchedGroup = groupRepository.findById(groupId);
			Group group = searchedGroup
					.orElseThrow(() -> new ResourceNotFoundException("Group not found with ID: " + groupId));

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
			if (groupRequest.getDescription() != null) {
				group.setDescription(groupRequest.getDescription());
			}

			groupRepository.save(group);
			return this.mapGroupDetailsDTO(group);
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

	public UserGroupsDTO getGroupsByUser(User searchedUser) {
		Set<Group> groupsUser = new TreeSet<>(Comparator.comparingInt(Group::getId));
		Set<Group> groupsOwner = new TreeSet<>(Comparator.comparingInt(Group::getId));
		groupsUser.addAll(searchedUser.getGroups());
		groupsOwner.addAll(searchedUser.getOwnedGroups());
		
		// Grupos en los que el usuario es miembro
		List<GroupDetailsDTO> listGroups = this.mapListGroupDetailsDTO(groupsUser);

		// Grupos creados por el usuario
		List<GroupDetailsDTO> listOwnedGroups = this.mapListGroupDetailsDTO(groupsOwner);

		return new UserGroupsDTO(listGroups, listOwnedGroups);
	}
}
