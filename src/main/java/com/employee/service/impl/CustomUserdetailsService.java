package com.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.entity.User;
import com.employee.exception.ResourceNotFoundWIthExceptionString;
import com.employee.repo.Employeerepo;
import com.employee.repo.Userrepo;

@Service
public class CustomUserdetailsService implements UserDetailsService{
	@Autowired
	private Userrepo userrepo;
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userrepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundWIthExceptionString("user", "email", username));
		
		return user;
	}	
}
