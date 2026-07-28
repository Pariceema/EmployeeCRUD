package com.employee.service;

import java.util.List;

import com.employee.dto.Departmentdto;
import com.employee.response.PaginationResponse;

public interface Departmentservice {

	//create 
	Departmentdto createDepartment(Departmentdto departmentdto);
		
	//update
	Departmentdto updateDepartment(Departmentdto departmentdto,Long deptid);

	//Delete
	void deleteDepartment(Long deptid);
		
	//get
	Departmentdto getDepartment(Long deptid);
	
	//getall with pagination
	List<Departmentdto> getalldepartments();

	PaginationResponse getalldepartment(String sort_by, String sort_direction, int pagee_size, int pagee_number);

}
