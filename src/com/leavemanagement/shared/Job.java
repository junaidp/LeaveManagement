package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name="job")
public class Job   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobId")
	private int jobId;
	
	@Column(name="jobName")
	private String jobName;
	
	@Column(name="client")
	private String client;
	
	@Column(name="clientFee")
	private int clientFee;
	
	@Column(name="supervisorHours")
	private int supervisorHours;
	
	@Column(name="principalConsultantHours")
	private int principalConsultantHours;
	
	@JoinColumn(name = "subLineofServiceId")
	@ManyToOne(fetch = FetchType.LAZY)
	private SubLineofService subLineofServiceId;
	
	@JoinColumn(name = "lineofServiceId")
	@ManyToOne(fetch = FetchType.LAZY)
	private LineofService lineofServiceId;
	
	@JoinColumn(name = "domainId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Domains domainId;
	
	@JoinColumn(name = "countryId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Countries countryId;
	
//	@JoinColumn(name = "employee1")
//	@ManyToOne(fetch = FetchType.LAZY)
//	private User employee1;
	
	@JoinColumn(name = "supervisorId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User supervisorId;
	
	@JoinColumn(name = "principalConsultantId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User principalConsultantId;
	
	@Transient
	private ArrayList<Phases> jobPhases;
	
	@Transient
	private ArrayList<JobEmployees> jobEmployeesList;
	
	@Transient ArrayList<JobAttributes> jobAttributes;
	
	@Transient ArrayList<TimeSheet> timeSheets;
	
	@Column(name="status")
	private String status;
	
	public Job(){}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public SubLineofService getSubLineofServiceId() {
		return subLineofServiceId;
	}

	public void setSubLineofServiceId(SubLineofService subLineofServiceId) {
		this.subLineofServiceId = subLineofServiceId;
	}

	public LineofService getLineofServiceId() {
		return lineofServiceId;
	}

	public void setLineofServiceId(LineofService lineofServiceId) {
		this.lineofServiceId = lineofServiceId;
	}

	public Domains getDomainId() {
		return domainId;
	}

	public void setDomainId(Domains domainId) {
		this.domainId = domainId;
	}

	public ArrayList<Phases> getJobPhases() {
		return jobPhases;
	}

	public void setJobPhases(ArrayList<Phases> jobPhases) {
		this.jobPhases = jobPhases;
	}

	public Countries getCountryId() {
		return countryId;
	}

	public void setCountryId(Countries countryId) {
		this.countryId = countryId;
	}

//	public User employee1() {
//		return employee1;
//	}
//
//	public void setUserId(User userId) {
//		this.employee1 = userId;
//	}

	public User getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(User supervisorId) {
		this.supervisorId = supervisorId;
	}

	public User getPrincipalConsultantId() {
		return principalConsultantId;
	}

	public void setPrincipalConsultantId(User principalConsultantId) {
		this.principalConsultantId = principalConsultantId;
	}

	public int getClientFee() {
		return clientFee;
	}

	public void setClientFee(int clientFee) {
		this.clientFee = clientFee;
	}

	public ArrayList<JobEmployees> getJobEmployeesList() {
		return jobEmployeesList;
	}

	public void setJobEmployeesList(ArrayList<JobEmployees> jobEmployeesList) {
		this.jobEmployeesList = jobEmployeesList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<JobAttributes> getJobAttributes() {
		return jobAttributes;
	}

	public void setJobAttributes(ArrayList<JobAttributes> jobAttributes) {
		this.jobAttributes = jobAttributes;
	}

	public ArrayList<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(ArrayList<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public int getSupervisorHours() {
		return supervisorHours;
	}

	public void setSupervisorHours(int supervisorHours) {
		this.supervisorHours = supervisorHours;
	}

	public int getPrincipalConsultantHours() {
		return principalConsultantHours;
	}

	public void setPrincipalConsultantHours(int principalConsultantHours) {
		this.principalConsultantHours = principalConsultantHours;
	}

	
	
}
