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
import com.employee.response.APIresponse;
import com.employee.response.PaginationResponse;
import com.employee.service.Employeeservice;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")

public class EmployeeController {
	@Autowired
	private Employeeservice employeeservice;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add/{deptId}")
		public ResponseEntity<Employeedto> addemployee(@Valid @RequestBody Employeedto employeedto,@PathVariable Long deptId){
			
		Employeedto employeedto1=this.employeeservice.createEmployee(employeedto,deptId);
		return new ResponseEntity<Employeedto>(employeedto1,HttpStatus.CREATED);
			
		}
	
	@PutMapping("/update/{empid}/{deptId}")
		public ResponseEntity<Employeedto> updateemployee(@Valid @RequestBody Employeedto employeedto,@PathVariable Long empid,@PathVariable Long deptId){
		
		Employeedto employeedto2=this.employeeservice.updateEmployee(employeedto,empid, deptId);
		return new ResponseEntity<Employeedto>(employeedto2,HttpStatus.OK);
	}
	
	@GetMapping("/get/{empid}")
		public ResponseEntity<Employeedto> getemployee(@PathVariable Long empid){
		Employeedto employeedto3=this.employeeservice.getEmployee(empid);
		return new ResponseEntity<Employeedto>(employeedto3,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<APIresponse> deleteemployee(@PathVariable Long empid){
		this.employeeservice.deleteEmployee(empid);
		APIresponse apiresponse=new APIresponse();
		apiresponse.setMessage("Employee deleted succesfully");
		apiresponse.setStatus(true);
		return new ResponseEntity<APIresponse>(apiresponse,HttpStatus.OK);
		
	
	}
	
	
	@GetMapping("/getall/")
		public ResponseEntity<PaginationResponse> getallempployee(@RequestParam(value = "sortBy",defaultValue = Constantvalue.SORT_BY,required = false)String sortBy,
				@RequestParam(value = "sortDirection",defaultValue = Constantvalue.SORT_DIRECTION,required = false)String sortDirection,
				@RequestParam(value = "pageNumber",defaultValue = Constantvalue.PAGE_NUMBER,required = false)Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = Constantvalue.PAGE_SIZE,required = false)Integer pageSize)
	{
		
		PaginationResponse paginationresponse=this.employeeservice.getallemployee(sortBy, sortDirection, pageNumber,pageSize);
		return new ResponseEntity<PaginationResponse>(paginationresponse,HttpStatus.OK);
	}
}
