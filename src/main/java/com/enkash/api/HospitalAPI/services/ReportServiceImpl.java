package com.enkash.api.HospitalAPI.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enkash.api.HospitalAPI.model.Patient;
import com.enkash.api.HospitalAPI.model.Report;
@Service
public class ReportServiceImpl implements ReportService {
	
	
	@Autowired
	private ReportRepo repRepo;
	@Autowired
	private PatientRepo patRepo;
	
	public ReportServiceImpl() {

	}
	@Override
	public Report addRep(Report rep,long pid) {
		// TODO Auto-generated method stub
		try {
			Patient patient = patRepo.getOne(pid);
			
			Report repo = new Report(rep.getDisease(),rep.getStatus());
			patient.getReport().add(repo);
//			this.patRepo.save(patient);
			this.repRepo.save(repo);
			return rep;
		} catch (Exception e) {
			System.out.println("Cant add Report due to "+e);
			return null;
		}
		
	}

}
