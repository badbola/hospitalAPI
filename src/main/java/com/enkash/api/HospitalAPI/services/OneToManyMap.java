//package com.enkash.api.HospitalAPI.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import com.enkash.api.HospitalAPI.model.Patient;
//import com.enkash.api.HospitalAPI.model.Report;
//@SpringBootApplication
//public class OneToManyMap implements PatientService, ReportService {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		SpringApplication.run(OneToManyMap.class, args);
//		
//	}
//	@Autowired
//	private ReportRepo repRepo;
//	@Autowired
//	private PatientRepo patRepo;
//	@Override
//	public Patient addPat(Patient pat) {
//		// TODO Auto-generated method stub
//		Patient patient = new Patient(pat.getName(),pat.getDisease());
//		this.patRepo.save(patient);
//		return null;
//	}
//	@Override
//	public Report addRep(Report rep) {
//		// TODO Auto-generated method stub
//		Patient patient = patRepo.getOne((long) 4);
//		
//		Report repo = new Report(rep.getDisease(),rep.getStatus());
//		patient.getReport().add(repo);
//		this.patRepo.save(patient);
//		this.repRepo.save(repo);
//		return null;
//	}
//
//}
