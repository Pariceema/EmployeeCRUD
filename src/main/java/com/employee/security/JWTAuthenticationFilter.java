package com.employee.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.entity.Employee;
import com.employee.entity.User;
import com.employee.service.impl.CustomUserdetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private CustomUserdetailsService customuserdetailsservice;
	@Autowired
	private JWTservice jwtservice;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader=request.getHeader("Authorization");
		final String jwtToken;
		final String email;
		
		if(authHeader==null||!authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken=authHeader.substring(7);
		 
		email=this.jwtservice.extractEmail(jwtToken);
		
		if(email!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			User user=(User) this.customuserdetailsservice.loadUserByUsername(email);
			if(this.jwtservice.isTokenValidate(jwtToken,user)) {
				UsernamePasswordAuthenticationToken  userpasstoken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				userpasstoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));	
				SecurityContextHolder.getContext().setAuthentication(userpasstoken);
				
			}	
		}
		filterChain.doFilter(request, response);
	}
	
	
}
