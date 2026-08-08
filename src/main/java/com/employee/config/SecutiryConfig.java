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
	.authorizeHttpRequests(auth -> auth

	        // Swagger
	        .requestMatchers(
	                "/swagger-ui/**",
	                "/swagger-ui.html",
	                "/v3/api-docs/**"
	        ).permitAll()

	        // Public APIs
	        .requestMatchers("/api/userlogin/**").permitAll()

	        // User APIs
	        .requestMatchers("/api/v2/**").hasRole("USER")

	        // Admin APIs
	        .requestMatchers("/api/v1/**").hasRole("ADMIN")

	        // Images
	        .requestMatchers("/api/images/**").hasRole("USER")

	        .anyRequest().authenticated()
	        )
	        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationprovider)
        .addFilterBefore(jwtauthenticationfilter,
                UsernamePasswordAuthenticationFilter.class);
	
	return httpsecurity.build();
	
}
}
