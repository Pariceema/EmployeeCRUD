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

import com.employee.dto.Departmentdto;
import com.employee.dto.Employeedto;
import com.employee.entity.Department;
import com.employee.exception.EmptyException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repo.Departmentrepo;
import com.employee.response.PaginationResponse;
import com.employee.service.Departmentservice;
import com.employee.service.Employeeservice;

@Service
public class DpeartmentServiceImpl implements Departmentservice{

	@Autowired
	private Departmentrepo departmentrepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public Departmentdto createDepartment(Departmentdto departmentdto) {
		validate(departmentdto);
		Department depart=new Department();
		
		depart=this.departmentdtotodepartment(departmentdto);
		try {
			this.departmentrepo.save(depart);
		}catch(DataIntegrityViolationException e) {
			throw new EmptyException("Department id already exists");
		}
		Departmentdto departmentdto1=this.departmenttodepartmentdto(depart);
		return departmentdto1;
	}

	@Override
	public Departmentdto updateDepartment(Departmentdto departmentdto, Long deptid) {
		Department department=this.departmentrepo.findById(deptid).orElseThrow(()->new ResourceNotFoundException("Department","Id",deptid));
		
		department.setDeptId(departmentdto.getDeptId());
		department.setDeptHead(departmentdto.getDeptHead());
		department.setName(departmentdto.getName());
		try {
			this.departmentrepo.save(department);
		}catch(DataIntegrityViolationException e) {
			throw new EmptyException("Department id already exists");
		}
		Departmentdto departmentdto1=this.departmenttodepartmentdto(department);
		return departmentdto1;
		
		
	}

	@Override
	public void deleteDepartment(Long deptid) {
		Department department=this.departmentrepo.findById(deptid).orElseThrow(()->new ResourceNotFoundException("Department","Id",deptid));
		this.departmentrepo.delete(department);
	}

	@Override
	public Departmentdto getDepartment(Long deptid) {
		Department department=this.departmentrepo.findById(deptid).orElseThrow(()->new ResourceNotFoundException("Department","Id",deptid));
		
		Departmentdto departmentdto2=this.departmenttodepartmentdto(department);
		return departmentdto2;
	}

	
	public Department departmentdtotodepartment(Departmentdto departmentdto) {
		//return this.modelmapper.map(departmentdto, Department.class) ;
		Department department11=new Department();
		department11.setId(departmentdto.getId());		department11.setDeptId(departmentdto.getDeptId());
		department11.setName(departmentdto.getName());
		department11.setDeptHead(departmentdto.getDeptHead());
		
		return department11;
	}
	
	public Departmentdto departmenttodepartmentdto(Department department) {
		//return this.modelmapper.map(department, Departmentdto.class) ;
		Departmentdto deptdtoo=new Departmentdto();
		deptdtoo.setId(department.getId());
		deptdtoo.setDeptId(department.getDeptId());
		deptdtoo.setName(department.getName());
		deptdtoo.setDeptHead(department.getDeptHead());
		
		if(department.getEmployee() != null) {
	        List<Employeedto> employees = department.getEmployee()
	                .stream()
	                .map(emp -> modelmapper.map(emp, Employeedto.class))
	                .collect(Collectors.toList());

	        deptdtoo.setEmployee(employees);
	    }
		return deptdtoo;
	}
	
	private void validate(Departmentdto departmentdto) {
		if(departmentdto==null) {
			throw new EmptyException("department details are required");
		}if(blank(departmentdto.getName())) {
			throw new EmptyException("Department name is required");
		}if(departmentdto.getDeptHead()==null) {
			throw new EmptyException("department head is required");
		}	}
	
	private boolean blank(String value)
	{
	    return value == null || value.trim().isEmpty();
	}
	@Override
	public List<Departmentdto> getalldepartments() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PaginationResponse getalldepartment(String sort_by, String sort_direction, int pagee_size, int pagee_number) {	
			Sort sort=sort_direction.equalsIgnoreCase("ASC")?Sort.by(sort_by).ascending():Sort.by(sort_by).descending();
			Pageable pageable=PageRequest.of(pagee_number, pagee_size, sort);
			Page<Department> page=this.departmentrepo.findAll(pageable);
			List<Department> list=page.getContent();
			if(list.isEmpty()) {
				throw new EmptyException("No Department");
			}
			List<Departmentdto> list1=list.stream().map(this::departmenttodepartmentdto).collect(Collectors.toList());
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
}
	
