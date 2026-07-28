package com.employee.dto;

import com.employee.roles.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Userlogindto {

private Long id;
	
@Email(message = "Enter a valid email")
@NotBlank(message = "Email is required")
@Size(max = 100, message = "Email cannot exceed 100 characters")
	private String email;
	

@NotBlank(message = "Password is required")
@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
@Pattern(
    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
    message = "Password must contain uppercase, lowercase, number and special character"
)
	private String password;
	
@NotNull(message = "Role is required")
@Enumerated(EnumType.STRING)
	private Role roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	
	
}
