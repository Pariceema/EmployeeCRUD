package com.employee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.employee.dto.Employeedto;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.exception.EmptyException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repo.Departmentrepo;
import com.employee.repo.Employeerepo;
import com.employee.response.PaginationResponse;
import com.employee.service.Employeeservice;

@Service
public class EmployeeServiceImpl implements Employeeservice{

	@Autowired
	private Employeerepo employeerepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private Departmentrepo departmentrepo;
	@Override	
	@PreAuthorize("hasRole('ADMIN')")
	public Employeedto createEmployee(Employeedto employeedto,Long deptId) {
		validate(employeedto);
		Employee empl=new Employee();
		Department department=this.departmentrepo.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Dpeartment", "departmentId", deptId));
		empl=this.employeedtotoemployee(employeedto);
		empl.setDepartment(department);
		try {
		this.employeerepo.save(empl);
		}catch(DataIntegrityViolationException e) {
			throw new EmptyException("Employee id already exists"); 
		}
		Employeedto empdto= this.employeetoemployeedto(empl);
		return empdto;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN)")
	public Employeedto updateEmployee(Employeedto employeedto, Long empid,Long deptId) {
		Employee employee=this.employeerepo.findById(empid).orElseThrow(()->new ResourceNotFoundException("Employee","Id",empid));
		Department department=this.departmentrepo.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Dpeartment", "departmentId", deptId));
		employee.setDepartment(department);
		
		employee.setEmpId(employeedto.getEmpId());
		employee.setEmail(employeedto.getEmail());
		employee.setDesignation(employeedto.getDesignation());
		employee.setName(employeedto.getName());
		try {
			this.employeerepo.save(employee);
		}catch(DataIntegrityViolationException e) {
			throw new EmptyException("Employee id already exists");
		}
		Employeedto employeedto1=this.employeetoemployeedto(employee);
		return employeedto1;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN)")
	public void deleteEmployee(Long empid) {
		Employee employee=this.employeerepo.findById(empid).orElseThrow(()->new ResourceNotFoundException("Employee","Id",empid));
		
		this.employeerepo.delete(employee);
		
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN,USER')")
	public Employeedto getEmployee(Long empid) {
		Employee employee=this.employeerepo.findById(empid).orElseThrow(()->new ResourceNotFoundException("Employee","Id",empid));
		
		Employeedto employeedto2=this.employeetoemployeedto(employee);
		return employeedto2;
	}
	
	@Override
	public PaginationResponse getallemployee(String sort_by, String sort_direction, int pagee_number, int pagee_size) {
		Sort sort=sort_direction.equalsIgnoreCase("ASC")?Sort.by(sort_by).ascending():Sort.by(sort_by).descending();
		Pageable pageable=PageRequest.of(pagee_number, pagee_size, sort);
		Page<Employee> page=this.employeerepo.findAll(pageable);
		List<Employee> list=page.getContent();
		if(list.isEmpty()) {
			throw new EmptyException("No employeee");
		}
		List<Employeedto> list1=list.stream().map(this::employeetoemployeedto).collect(Collectors.toList());
		PaginationResponse paginationresponse=new PaginationResponse();
		paginationresponse.setCurrentpagedata(page.getNumberOfElements());
		paginationresponse.setLastpage(page.isLast());
		paginationresponse.setList(list1);
		paginationresponse.setPagenumber(page.getNumber());
		paginationresponse.setPagesize(page.getSize());
		paginationresponse.setTotaldata(page.getTotalElements());
		paginationresponse.setTotalpage(page.getTotalPages());
		return paginationresponse;
	}
	@Override
	public Employee saveEmployee(Employee employee) {
	    return employeerepo.save(employee);
	}
	
	private Employee employeedtotoemployee(Employeedto employeedto) {
		return this.modelmapper.map(employeedto, Employee.class);
	}
	
	private Employeedto employeetoemployeedto(Employee employee) {
		return this.modelmapper.map(employee, Employeedto.class);
	}
	
	private void validate(Employeedto employeedto) {
		if(employeedto==null) {
			throw new EmptyException("Employee details are required");
		}if(blank(employeedto.getName())) {
			throw new EmptyException("Employee name is required");
		}if(employeedto.getEmail()==null) {
			throw new EmptyException("Employee email is required");
		}if(employeedto.getDesignation()==null) {
			throw new EmptyException("Employee designation is required");
		}
	}
	
	private boolean blank(String value)
	{
	    return value == null || value.trim().isEmpty();
	}
	
	
	
}
