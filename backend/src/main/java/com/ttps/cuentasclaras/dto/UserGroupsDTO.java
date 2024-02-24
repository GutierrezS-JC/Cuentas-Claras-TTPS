package com.ttps.cuentasclaras.dto;

import java.util.List;

public class UserGroupsDTO {
	private List<GroupDetailsDTO> listGroups;
	private List<GroupDetailsDTO> listOwnedGroups;

	public UserGroupsDTO(List<GroupDetailsDTO> listGroups, List<GroupDetailsDTO> listOwnedGroups) {
		super();
		this.listGroups = listGroups;
		this.listOwnedGroups = listOwnedGroups;
	}

	public List<GroupDetailsDTO> getListGroups() {
		return listGroups;
	}

	public void setListGroups(List<GroupDetailsDTO> listGroups) {
		this.listGroups = listGroups;
	}

	public List<GroupDetailsDTO> getListOwnedGroups() {
		return listOwnedGroups;
	}

	public void setListOwnedGroups(List<GroupDetailsDTO> listOwnedGroups) {
		this.listOwnedGroups = listOwnedGroups;
	}

}
