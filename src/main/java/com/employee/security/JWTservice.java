package com.employee.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.entity.User;
import com.employee.service.impl.CustomUserdetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
	@Value("${jwtsecretkey}")
	private String SECRET_KEY;
	public String extractEmail(String jwttoken) {
		
		return extractClaim(jwttoken,Claims::getSubject);
		
	}

	private  <T> T extractClaim(String jwttoken,Function<Claims, T> claimResolver) {
		Claims claims=extractAllClaims(jwttoken);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String jwttoken) {
		Jwt<?, Claims> jwt=Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(jwttoken);
		return jwt.getPayload();
	}

	private SecretKey getSigningKey() {
		byte arr[]=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(arr);
	}

	public boolean isTokenValidate(String jwtToken, User user) {
		 final String email=extractEmail(jwtToken);
		 
		return (email.equals(user.getUsername()))&& !isTokenExpired(jwtToken);
	}

	private boolean isTokenExpired(String jwtToken) {
		return exractExpiration(jwtToken).before(new Date());
	}

	private Date exractExpiration(String jwtToken) {
		return extractClaim(jwtToken, Claims::getExpiration);
	}
	
	public String generateToken(User user) {
		
		return generateTokenn(user,new HashMap<>());
		
	}

	private String generateTokenn(User user, Map<String, Object>claims) {
		
		return Jwts.builder().claims(claims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+(24*60*60*100)))
				.signWith(getSigningKey(),Jwts.SIG.HS256)
				.compact()		
				;
	}

	
}
