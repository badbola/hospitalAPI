package com.enkash.api.HospitalAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enkash.api.HospitalAPI.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepo patRepo;
	
	
	public PatientServiceImpl() {

	}

	@Override
	public Patient addPat(Patient pat) {
		// TODO Auto-generated method stub
		try {
			patRepo.save(pat);
			return pat;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cant add Patient due to "+e);
			return null;
		}
		
	}

}
