package com.employee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.User;
@Repository
public interface Userrepo extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String username);

	
}
