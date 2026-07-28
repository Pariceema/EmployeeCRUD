package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.employee.dto.Departmentdto;
import com.employee.response.APIresponse;
import com.employee.response.PaginationResponse;
import com.employee.service.Departmentservice;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v2")
public class DepartmentController {
	@Autowired
	private Departmentservice departmentservice;
	@PostMapping("/adddept/")
		public ResponseEntity<Departmentdto> adddepartment(@Valid @RequestBody Departmentdto departmentdto){
			
		Departmentdto departmentdto1=this.departmentservice.createDepartment(departmentdto);
		return new ResponseEntity<Departmentdto>(departmentdto1,HttpStatus.CREATED);
			
		}
	
	@PutMapping("/updatedept/{deptid}")
		public ResponseEntity<Departmentdto> updatedepartment(@Valid @RequestBody Departmentdto departmentdto,@PathVariable Long deptid){
			
		Departmentdto departmentdto1=this.departmentservice.updateDepartment(departmentdto,deptid);
		return new ResponseEntity<Departmentdto>(departmentdto1,HttpStatus.OK);
			
		}
	
	@GetMapping("/getdept/{deptid}")
	public ResponseEntity<Departmentdto> getdepartment(@PathVariable Long deptid){
		
	Departmentdto departmentdto1=this.departmentservice.getDepartment(deptid);
	return new ResponseEntity<Departmentdto>(departmentdto1,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deletedept/{deptid}")
	public ResponseEntity<APIresponse> deleteemployee(@PathVariable Long deptid){
		this.departmentservice.deleteDepartment(deptid);
		APIresponse apiresponse=new APIresponse();
		apiresponse.setMessage("Department deleted succesfully");
		apiresponse.setStatus(true);
		return new ResponseEntity<APIresponse>(apiresponse,HttpStatus.OK);
	}
	
	@GetMapping("/getalldept/")
	public ResponseEntity<PaginationResponse> getalldepartment(@RequestParam(value = "sortBy",defaultValue = Constantvalue.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = Constantvalue.SORT_DIRECTION,required = false)String sortDirection,
			@RequestParam(value = "pageSize",defaultValue = Constantvalue.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "pageNumber",defaultValue = Constantvalue.PAGE_NUMBER,required = false)Integer pageNumber){
	
	PaginationResponse paginationresponse=this.departmentservice.getalldepartment(sortBy, sortDirection, pageSize, pageNumber);
	return new ResponseEntity<PaginationResponse>(paginationresponse,HttpStatus.OK);
}
	
}
