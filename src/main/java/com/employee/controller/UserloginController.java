package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.Userlogindto;
import com.employee.entity.User;
import com.employee.exception.ResourceNotFoundException;
import com.employee.exception.ResourceNotFoundWIthExceptionString;
import com.employee.repo.Userrepo;
import com.employee.roles.Role;
import com.employee.security.JWTservice;

@RestController
@RequestMapping("api/userlogin")
public class UserloginController {

	@Autowired
	private JWTservice jwtservice;
	@Autowired
private Userrepo userrepo;
	@Autowired
	private AuthenticationManager authenticationmanager;
	@Autowired
	private PasswordEncoder passwordencoder;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Userlogindto userlogindto) {
		 
		User user=new User();
		
		user.setEmail(userlogindto.getEmail());
		user.setPassword(passwordencoder.encode(userlogindto.getPassword()));
		user.setRoles(Role.USER);
		
		userrepo.save(user);
		
		return new ResponseEntity<String>("User register successfully",HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Userlogindto userlogindto){
		
		authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(userlogindto.getEmail(),userlogindto.getPassword()));
		
		User user=this.userrepo.findByEmail(userlogindto.getEmail()).orElseThrow(()->new ResourceNotFoundWIthExceptionString("User", "UserEmail", userlogindto.getEmail()));
		
		String token=this.jwtservice.generateToken(user);
		
		return new ResponseEntity<String> (token,HttpStatus.OK);
		
	}
	
	
	
}
