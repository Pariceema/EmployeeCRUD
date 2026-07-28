package com.employee.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.employee.service.impl.CustomUserdetailsService;

@Configuration

public class Appconfig {
	@Autowired
	private CustomUserdetailsService customuserdetailsservice;
	@Bean
	public ModelMapper getmodelmapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider provider()
	{
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider(customuserdetailsservice);
		dao.setPasswordEncoder(encoder());
		return dao;
	}

	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration authenticationconfiguration) throws Exception{
		return authenticationconfiguration.getAuthenticationManager();
		
	}
	
}
