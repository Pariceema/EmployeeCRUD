package com.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Employeedto {
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employeedto(Long id) {
		super();
		this.id = id;
	}

	@NotBlank(message = "employee id is required")
	@Pattern(
		    regexp = "^EMP\\d+$",
		    message = "Employee ID must start with EMP followed by numbers"
		)
	@Size(min = 3, max = 6, message = "Employee ID must be exactly 6 characters")
	private String empId;
	
	@NotBlank(message="Employee name is required")
	@Pattern(regexp = "^[A-Za-z ]{3,50}$",message = "Employee name must contain only letters and spaces")
	@Size(min = 3,max = 50,message = "Employee name must be between 3 and 50 characters")
	private String name;
	
	@Email(message="invalid email address")
	@NotBlank(message="Email is required")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",message = "Enter a valid email address")
	@Size(min = 5,max = 50,message = "Email must be between 5 and 50 characters")
	private String email;
	
	@NotBlank(message="Designation is must")
	@Pattern(regexp = "^[A-Za-z ]+$",message = "Designation must contain only letters and spaces")
	@Size(min = 2,max = 30,message = "Designation must be between 2 and 30 characters")
	private String designation;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Employeedto(
			@NotBlank(message = "employee id is required") @Pattern(
				    regexp = "^EMP\\d+$", message = "Employee ID must start with EMP followed by numbers") @Size(min = 3, max = 6, message = "Employee ID must be exactly 6 characters") String empId,
			@NotBlank(message = "Employee name is required") @Pattern(regexp = "^[A-Za-z ]{3,50}$", message = "Employee name must contain only letters and spaces") @Size(min = 3, max = 50, message = "Employee name must be between 3 and 50 characters") String name,
			@Email(message = "invalid email address") @NotBlank(message = "Email is required") @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Enter a valid email address") @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters") String email,
			@NotBlank(message = "Designation is must") @Pattern(regexp = "^[A-Za-z ]+$", message = "Designation must contain only letters and spaces") @Size(min = 2, max = 30, message = "Designation must be between 2 and 30 characters") String designation) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.designation = designation;
	}

	public Employeedto() {
			}
	
	

}
