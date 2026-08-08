package com.employee.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.employee.roles.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="emp")
@EntityListeners(AuditingEntityListener.class)

public class Employee{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Employee ID is required")
	@Size(min = 3, max = 20, message = "Employee ID must be between 3 and 20 characters")
	@Column(name="emp_id",nullable = false,unique = true)
	private String empId;
	
	@NotBlank(message = "Employee name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	@Column(name="emp_name",nullable=false)	
	private String name;
	
	@NotBlank(message = "Designation is required")
	@Column(name="emp_email",nullable=false,unique=true)
	private String email;
	
	@Column(name="emp_design",nullable=false)
	private String designation;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must contain at least 6 characters")
	@Column(name="Password",nullable=false)
	private String password;
	
	@Column(name="created_at")
	@CreatedDate
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	private LocalDateTime lastlogin;

	@NotNull(message = "Role is required")
	@Enumerated(EnumType.STRING)
	private Role roles;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department; //variable ka nam
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Employee(Department department, Long id, String empId, String name, String email, String designation,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastlogin) {
		super();
		this.department = department;
		this.id = id;
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.designation = designation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastlogin = lastlogin;
	}

	public Employee() {
		
	}

	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
	 * TODO Auto-generated method stub return null; }
	 */
	
	
	
}
