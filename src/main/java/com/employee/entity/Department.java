package com.employee.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="dept")
@EntityListeners(AuditingEntityListener.class)
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="dept_id",nullable = false,unique = true)
	private String deptId;
	
	@Column(name="dept_name",nullable=false)	
	private String name;
	
	@Column(name="dept_head")
	private String deptHead;
	
	@Column(name="created_at",updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private List<Employee> employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public Department(Long id, String deptId, String name, String deptHead, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<Employee> employee) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.name = name;
		this.deptHead = deptHead;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.employee = employee;
	}

	public Department() {
		
	}
	
	
}
