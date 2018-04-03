package com.leavemanagement.shared;

import java.io.Serializable;

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

@Table(name="jobEmployee")
public class JobEmployees   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobEmployeeId")
	private int jobEmployeeId;
	
	@Column(name="jobId")
	private int jobId;
	
	@JoinColumn(name = "employeeId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User employeeId;
	
	@Column(name ="noOfDays")
	private int noOfDays;
	
	
	
	public JobEmployees(){}



	public int getJobId() {
		return jobId;
	}



	public void setJobId(int jobId) {
		this.jobId = jobId;
	}



	public int getNoOfDays() {
		return noOfDays;
	}



	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}



	public User getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(User employeeId) {
		this.employeeId = employeeId;
	}



	public int getJobEmployeeId() {
		return jobEmployeeId;
	}



	public void setJobEmployeeId(int jobEmployeeId) {
		this.jobEmployeeId = jobEmployeeId;
	}


	
}
