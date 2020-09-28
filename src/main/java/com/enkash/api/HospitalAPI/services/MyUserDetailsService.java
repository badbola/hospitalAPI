package com.enkash.api.HospitalAPI.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enkash.api.HospitalAPI.model.Doctor;
import com.enkash.api.HospitalAPI.model.MyUserDetails;
@Service
public class MyUserDetailsService implements UserDetailsService,DocService{
	@Autowired
	DoctorRepository docRepo;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Doctor> doc=docRepo.findByUsername(userName);
		doc.orElseThrow(()->new UsernameNotFoundException("Not found "+userName));
		
		return doc.map(MyUserDetails::new).get();
	}
	@Override
	public Doctor addDoc(Doctor doc) {
		try {
			docRepo.save(doc);
			return doc;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	@Override
	public List<Object> showAll() {
		List<Object>list = new ArrayList<>();
		
		docRepo.findAll().forEach(e->{
			String ID = String.valueOf(e.getId());
			String Name = e.getName();
			String Email = e.getEmail();
			list.add("id "+ID);
			list.add("name "+Name);
			list.add("Email "+Email);
			
		});
		
		// TODO Auto-generated method stub
		return list;
	}

}
