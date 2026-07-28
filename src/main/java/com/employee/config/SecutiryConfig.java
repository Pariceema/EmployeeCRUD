package com.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecutiryConfig {
	
	@Autowired
	private AuthenticationProvider authenticationprovider;
	@Autowired
	private JWTAuthenticationFilter jwtauthenticationfilter;
@Bean
public SecurityFilterChain sfchain(HttpSecurity httpsecurity) throws Exception{
	
	httpsecurity.csrf(csrf->csrf.disable())
	.authorizeHttpRequests(auth->auth.requestMatchers("/api/v2/**").hasAnyRole("USER")
			.requestMatchers("/api/v1/**").hasAnyRole("ADMIN")
			.requestMatchers("/api/images/**").hasAnyRole("USER")
			.requestMatchers("/api/userlogin/**").permitAll()
			.anyRequest().authenticated())		
			//.formLogin(form->form.permitAll())
			//.httpBasic(Customizer.withDefaults())
	
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationprovider)
		.addFilterBefore(jwtauthenticationfilter, UsernamePasswordAuthenticationFilter.class)
		
		
	;
	
	
	return httpsecurity.build();
	
}
}
