package com.ttps.cuentasclaras.dto;

public class UserDTO {
	private Integer id;
	private String email;
	private String username;
	private String name;
	private String lastName;
	private String profilepicBase64;
	private String password;

	public UserDTO() {

	}

	public UserDTO(Integer id, String email, String username, String name, String lastName, String profilepicBase64) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.profilepicBase64 = profilepicBase64;
	}

	// When password is required, e.g when creating a user
	public UserDTO(Integer id, String email, String username, String name, String lastName, String profilepicBase64,
			String password) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.profilepicBase64 = profilepicBase64;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
