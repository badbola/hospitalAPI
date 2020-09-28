package com.enkash.api.HospitalAPI.services;

import java.util.List;

import com.enkash.api.HospitalAPI.model.Patient;

public interface PatientService {
	
	public Patient addPat(Patient pat);

	public List<Patient> showAll();

	public Patient showOne(long parseLong);

}
