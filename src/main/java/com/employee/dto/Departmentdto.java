package com.employee.dto;

import java.util.ArrayList;
import java.util.List;

import com.employee.entity.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Departmentdto {
	
	private Long id;
	
	@NotBlank(message = "Department id is required")
	@Pattern(regexp = "^DEP\\d{3}$",message = "Department ID must be in format DEP101")
	private String deptId;
	
	@NotBlank(message="Name is required..!!")
	@Pattern(regexp = "^[A-Za-z ]+$",message = "Department name must contain only letters and spaces")
	@Size(min = 2,max = 40,message = "Department name must be between 2 and 40 characters")
	private String name;
	
	@NotBlank(message="Head of the department field is required")
	@Pattern(regexp = "^[A-Za-z ]+$",message = "Department head name must contain only letters and spaces")
	@Size(min = 3,max = 50,message = "Department head name must be between 3 and 50 characters")
	private String deptHead;

	private List<Employeedto> employee = new ArrayList<>();	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptHead() {
		return deptHead;
	}

	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Departmentdto(Long id) {
		super();
		this.id = id;
	}

	public Departmentdto(
			@NotBlank(message = "Department id is required") @Pattern(regexp = "^DEP\\d{3}$", message = "Department ID must be in format DEP101") String deptId,
			@NotBlank(message = "Name is required..!!") @Pattern(regexp = "^[A-Za-z ]+$", message = "Department name must contain only letters and spaces") @Size(min = 2, max = 40, message = "Department name must be between 2 and 40 characters") String name,
			@NotBlank(message = "Head of the department field is required") @Pattern(regexp = "^[A-Za-z ]+$", message = "Department head name must contain only letters and spaces") @Size(min = 3, max = 50, message = "Department head name must be between 3 and 50 characters") String deptHead) {
		super();
		this.deptId = deptId;
		this.name = name;
		this.deptHead = deptHead;
	}

	public Departmentdto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Employeedto> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employeedto> employee) {
		this.employee = employee;
	}

	public Departmentdto(List<Employeedto> employee) {
		super();
		this.employee = employee;
	}

	

		
	
}
