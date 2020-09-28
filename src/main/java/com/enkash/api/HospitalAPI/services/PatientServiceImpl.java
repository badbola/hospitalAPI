package com.enkash.api.HospitalAPI.services;

import java.util.List;

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
			Patient patient = new Patient(pat.getName(),pat.getDisease());
			this.patRepo.save(patient);
			return pat;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cant add Patient due to "+e);
			return null;
		}
		
	}

	@Override
	public List<Patient> showAll() {
		// TODO Auto-generated method stub
		return patRepo.findAll();
	}

	@Override
	public Patient showOne(long pid) {
		// TODO Auto-generated method stub
		try {
			return patRepo.findById(pid).get();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot fetch patient "+e);
			return null;
		}
		
	}

}
