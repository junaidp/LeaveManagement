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

@Table(name="users")
public class User   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userId")
	private int userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="examLeaves")
	private int examLeaves;
	
	@Column(name="reportingTo")
	private int reportingTo;
	
	
	
	@Column(name="contactNo")
	private String contactNo;
	
	@Column(name="bacnkAcNo")
	private String bacnkAcNo;
	
	@Column(name="status")
	private String status;
	
	@JoinColumn(name = "roleId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Roles roleId;
	
	@JoinColumn(name = "companyId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Company companyId;
	
	@Column(name="joiningDate")
	private Date joiningDate;
	
	
	
	public User(){}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int id) {
		this.userId = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getExamLeaves() {
		return examLeaves;
	}


	public void setExamLeaves(int examLeaves) {
		this.examLeaves = examLeaves;
	}


	public int getReportingTo() {
		return reportingTo;
	}


	public void setReportingTo(int reportingTo) {
		this.reportingTo = reportingTo;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getBacnkAcNo() {
		return bacnkAcNo;
	}


	public void setBacnkAcNo(String bacnkAcNo) {
		this.bacnkAcNo = bacnkAcNo;
	}


	public Roles getRoleId() {
		return roleId;
	}


	public void setRoleId(Roles roleId) {
		this.roleId = roleId;
	}


	public Date getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}


	public Company getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


}
