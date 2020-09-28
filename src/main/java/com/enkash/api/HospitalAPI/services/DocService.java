package com.enkash.api.HospitalAPI.services;


import java.util.List;

import com.enkash.api.HospitalAPI.model.Doctor;

public interface DocService {
	public Doctor addDoc(Doctor doc);

	List<Object> showAll();

}
