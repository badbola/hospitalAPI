package com.enkash.api.HospitalAPI.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enkash.api.HospitalAPI.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Optional<Doctor> findByUsername(String userName);
}
