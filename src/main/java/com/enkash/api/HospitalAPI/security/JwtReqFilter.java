package com.enkash.api.HospitalAPI.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.enkash.api.HospitalAPI.services.MyUserDetailsService;
@Component
public class JwtReqFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private MyUserDetailsService myUser;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		
		
		String username=null;
		String token=null;
		if (authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			username= jwtUtil.ExtractUsernames(token);
		}
		// TODO Auto-generated method stub
		if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = this.myUser.loadUserByUsername(username);
			if (jwtUtil.validToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			}
		}
		filterChain.doFilter(request,response);
		
	}

}
