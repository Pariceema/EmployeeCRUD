package com.employee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.dto.Employeedto;
import com.employee.entity.Employee;

@Repository
public interface Employeerepo extends JpaRepository<Employee, Long>{

	//public Optional<Employee> findbyEmail(String email);
	Optional<Employee> findByEmail(String email);
	

}
