package com.enkash.api.HospitalAPI.services;

import com.enkash.api.HospitalAPI.model.Report;

public interface ReportService {
	public Report addRep(Report rep, long pid);
}
