package com.enkash.api.HospitalAPI.security;


import java.util.Collection;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.enkash.api.HospitalAPI.model.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtUtil {
	private String secret;
	private int jwtExpirationInMs;
	
	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}
	@Value("${jwt.jwtExpirationInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	public String ExtractUsernames(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	public Date ExtractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		// TODO Auto-generated method stub
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	private boolean isTokenExpired(String token) {
		return ExtractExpiration(token).before(new Date());
	}
	public String generateToken(UserDetails userDel) {
		Map<String,Object> claims= new HashMap<>();
		
//		Collection<? extends GrantedAuthority> claims = userDel.getAuthorities();
		
//		if (roles.contains(new SimpleGrantedAuthority("ADMIN"))) {
//			claims.put("isAdmin", true);
//		}
//		if (roles.contains(new SimpleGrantedAuthority("DOCTOR"))) {
//			claims.put("isDoctor", true);
//		}
//		
		return doGenarateToken(claims,userDel.getUsername());
	}
	private String doGenarateToken(Map<String, Object> claims, String subject) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	public Boolean validToken(String token, UserDetails userDetails) {
		final String username = ExtractUsernames(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}

}
