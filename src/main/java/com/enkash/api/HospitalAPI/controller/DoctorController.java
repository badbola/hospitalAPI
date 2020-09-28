package com.enkash.api.HospitalAPI.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enkash.api.HospitalAPI.model.AuthenticationRequest;
import com.enkash.api.HospitalAPI.model.AuthenticationResponse;
import com.enkash.api.HospitalAPI.model.Doctor;
import com.enkash.api.HospitalAPI.model.Patient;
import com.enkash.api.HospitalAPI.model.Report;
import com.enkash.api.HospitalAPI.security.JwtUtil;
import com.enkash.api.HospitalAPI.services.MyUserDetailsService;
import com.enkash.api.HospitalAPI.services.PatientService;
import com.enkash.api.HospitalAPI.services.ReportService;

@RestController
public class DoctorController {
	@Autowired
	private MyUserDetailsService docService;
	
	@Autowired
	private PatientService patient;
	
	@Autowired
	private ReportService repService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService userDetails;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/")
	public String hello() {
		return "Hello There";
	}
	@GetMapping("/doctors")
	public List<Object> showAll() {
		return this.docService.showAll();
	}
	@PostMapping("/doctors")
	public Doctor addDoc(@RequestBody Doctor doc) {
		return this.docService.addDoc(doc);
	}
	@GetMapping("/patients")
	public List<Patient> patients() {
		return this.patient.showAll();
	}
	@PostMapping("/patients")
	public Patient addPat(@RequestBody Patient pat) {
		return this.patient.addPat(pat);
	}
	@GetMapping("/patients/{pid}")
	public Patient showOne(@PathVariable String pid) {
		return this.patient.showOne(Long.parseLong(pid));
	}
	@PostMapping("/reports/{pid}")
	public Report addRep(@RequestBody Report rep,@PathVariable String pid) {
		return this.repService.addRep(rep,Long.parseLong(pid));
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authReq) throws Exception{
		try {
			authManager.authenticate
			(new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword()));
			
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("USER_DISABLED",e);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("Username Password Not Valid",e);
		}
		final 
		UserDetails userDel = userDetails.loadUserByUsername(authReq.getUsername());
		String token = jwtUtil.generateToken(userDel);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

}
	

