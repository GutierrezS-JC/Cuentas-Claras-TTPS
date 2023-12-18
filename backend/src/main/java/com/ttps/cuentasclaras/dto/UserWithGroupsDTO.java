package com.ttps.cuentasclaras.dto;

import java.util.List;

public class UserWithGroupsDTO {
	private Integer id;
	private String email;
	private String username;
	private String name;
	private String lastName;
	private String profilepicBase64;
	private List<GroupDTO> groups;

	public UserWithGroupsDTO(Integer id, String email, String username, String name, String lastName,
			String profilepicBase64) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.profilepicBase64 = profilepicBase64;
	}

	public UserWithGroupsDTO(Integer id, String email, String username, String name, String lastName,
			String profilepicBase64, List<GroupDTO> groups) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.profilepicBase64 = profilepicBase64;
		this.groups = groups;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilepicBase64() {
		return profilepicBase64;
	}

	public void setProfilepicBase64(String profilepicBase64) {
		this.profilepicBase64 = profilepicBase64;
	}

	public List<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}

}
