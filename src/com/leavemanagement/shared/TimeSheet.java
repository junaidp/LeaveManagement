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

@Table(name="timesheet")
public class TimeSheet   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="timeSheetId")
	private int timeSheetId;
	
	@JoinColumn(name = "jobId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Job jobId;
	
	@Column(name="month")
	private int month;
	
	@Column(name="day")
	private int day;
	
	@Column(name="hours")
	private int hours;
	
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User userId;
	
	
	public TimeSheet(){}


	public int getTimeSheetId() {
		return timeSheetId;
	}


	public void setTimeSheetId(int timeSheetId) {
		this.timeSheetId = timeSheetId;
	}



	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getHours() {
		return hours;
	}


	public void setHours(int hours) {
		this.hours = hours;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public Job getJobId() {
		return jobId;
	}


	public void setJobId(Job jobId) {
		this.jobId = jobId;
	}



	
}
