package com.ttps.cuentasclaras.auth;

public class RegisterRequest {
    private String email;
    private String username;
    private String name;
    private String lastName;
    private String password;

    public RegisterRequest() {

    }

    public RegisterRequest(String email, String username, String name, String lastName, String password) {
    	super();
    	this.email = email;
    	this.username = username;
    	this.name = name;
    	this.lastName = lastName;
    	this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
