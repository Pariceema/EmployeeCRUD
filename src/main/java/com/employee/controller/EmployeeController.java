package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.constant.Constantvalue;
import com.employee.dto.Employeedto;
import com.employee.entity.Employee;
import com.employee.response.APIresponse;
import com.employee.response.PaginationResponse;
import com.employee.service.Employeeservice;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(
	    name = "Employee Management",
	    description = "Employee CRUD Operations"
	)
public class EmployeeController {
	@Autowired
	private Employeeservice employeeservice;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add/{deptId}")
	@Operation(
		    summary = "Create Employee",
		    description = "Creates a new employee in the database"
		)
		public ResponseEntity<Employeedto> addemployee(@Valid @RequestBody Employeedto employeedto,@PathVariable Long deptId){
			
		Employeedto employeedto1=this.employeeservice.createEmployee(employeedto,deptId);
		return new ResponseEntity<Employeedto>(employeedto1,HttpStatus.CREATED);
			
		}
	
	
	@PutMapping("/update/{empid}/{deptId}")
	@Operation(
		    summary = "Update Employee",
		    description = "Updates an existing employee"
		)
		public ResponseEntity<Employeedto> updateemployee(@Valid @RequestBody Employeedto employeedto,@PathVariable Long empid,@PathVariable Long deptId){
		
		Employeedto employeedto2=this.employeeservice.updateEmployee(employeedto,empid, deptId);
		return new ResponseEntity<Employeedto>(employeedto2,HttpStatus.OK);
	}
	
	@GetMapping("/get/{empid}")
	@Operation(
		    summary = "Get Employee By ID",
		    description = "Returns employee details using employee id"
		)
		public ResponseEntity<Employeedto> getemployee(@PathVariable Long empid){
		Employeedto employeedto3=this.employeeservice.getEmployee(empid);
		return new ResponseEntity<Employeedto>(employeedto3,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empid}")
	@Operation(
		    summary = "Delete Employee",
		    description = "Deletes an employee"
		)
	public ResponseEntity<APIresponse> deleteemployee(@PathVariable Long empid){
		this.employeeservice.deleteEmployee(empid);
		APIresponse apiresponse=new APIresponse();
		apiresponse.setMessage("Employee deleted succesfully");
		apiresponse.setStatus(true);
		return new ResponseEntity<APIresponse>(apiresponse,HttpStatus.OK);
		
	
	}
	
	
	@GetMapping("/getall/")
	@Operation(
		    summary = "Get All Employees",
		    description = "Returns all employees"
		)
		public ResponseEntity<PaginationResponse> getallempployee(@RequestParam(value = "sortBy",defaultValue = Constantvalue.SORT_BY,required = false)String sortBy,
				@RequestParam(value = "sortDirection",defaultValue = Constantvalue.SORT_DIRECTION,required = false)String sortDirection,
				@RequestParam(value = "pageNumber",defaultValue = Constantvalue.PAGE_NUMBER,required = false)Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = Constantvalue.PAGE_SIZE,required = false)Integer pageSize)
	{
		
		PaginationResponse paginationresponse=this.employeeservice.getallemployee(sortBy, sortDirection, pageNumber,pageSize);
		return new ResponseEntity<PaginationResponse>(paginationresponse,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
		return ResponseEntity.ok(employeeservice.saveEmployee(employee));
	}
}
