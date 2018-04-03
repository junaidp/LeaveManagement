package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="phases")
public class Phases   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="phaseId")
	private int phaseId;
	
	@Column(name="phaseName")
	private String phaseName;
		
	@JoinColumn(name = "jobId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Job jobId;
	
	@Column(name="startDate")
	private Date startDate;
	
	@Column(name="deliveryDate")
	private Date deliveryDate;
	
	@Column(name="submissionDate")
	private Date submissionDate;
	
	
	
	
	

	public Phases(){}

	public int getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public Job getJobId() {
		return jobId;
	}

	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	
}
