package com.employee.service;

import java.util.List;

import com.employee.dto.Employeedto;
import com.employee.response.PaginationResponse;

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






}
