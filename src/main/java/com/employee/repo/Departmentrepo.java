package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.dto.Departmentdto;
import com.employee.entity.Department;

@Repository
public interface Departmentrepo extends JpaRepository<Department, Long>{

	Departmentdto save(Departmentdto departmentdto);
	
	
}
