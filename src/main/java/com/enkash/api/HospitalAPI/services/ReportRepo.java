package com.enkash.api.HospitalAPI.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enkash.api.HospitalAPI.model.Report;
@Repository
public interface ReportRepo extends JpaRepository<Report,Long> {

}
