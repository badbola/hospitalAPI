package com.enkash.api.HospitalAPI.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Report")
@EntityListeners(AuditingEntityListener.class)
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String disease;
	private Date CreatedAt= new Date();
	private String status;
	public Report() {
		
	}
	public Report(String disease, String status) {
		super();
		this.disease = disease;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date CreatedAt) {
		this.CreatedAt = CreatedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
