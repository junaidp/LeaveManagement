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
import javax.persistence.Transient;


@Entity

@Table(name="leaverecord")
public class LeaveRecord   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="leaveRecordId")
	private int leaveRecordId;
	
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.LAZY)
	private User userId;
	
	@JoinColumn(name = "leaveTypeId")
	@ManyToOne(fetch = FetchType.LAZY)
	private LeaveTypes leaveTypeId;
	
	@Column(name="startDate")
	private Date startDate;
	
	@Column(name="endDate")
	private Date endDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="reason")
	private String reason;

	@Transient
	String days;

	public int getLeaveRecordId() {
		return leaveRecordId;
	}


	public void setLeaveRecordId(int leaveRecordId) {
		this.leaveRecordId = leaveRecordId;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}


	public LeaveTypes getLeaveType() {
		return leaveTypeId;
	}


	public void setLeaveType(LeaveTypes leaveType) {
		this.leaveTypeId = leaveType;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getDays() {
		return days;
	}


	public void setDays(String days) {
		this.days = days;
	}
	
	
	
	
	
	
}
