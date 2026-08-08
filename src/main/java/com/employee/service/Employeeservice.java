package com.employee.service;

import java.util.List;

import com.employee.dto.Employeedto;
import com.employee.entity.Employee;
import com.employee.response.PaginationResponse;

import jakarta.validation.Valid;

public interface Employeeservice {
//create 
	Employeedto createEmployee(Employeedto employeedto, Long deptId);

	
//update
	Employeedto updateEmployee(Employeedto employeedto, Long empid, Long deptId);

//Delete
void deleteEmployee(Long empid);
	
//get
Employeedto getEmployee(Long empid);
	
//getall with pagination
PaginationResponse getallemployee(String sort_by,String sort_direction,int pagee_number,int pagee_size);


Employee saveEmployee(Employee employee);


 






}
