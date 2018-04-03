package com.leavemanagement.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="leavetypes")
public class LeaveTypes   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="leaveTypeId")
	private int leaveTypeId;
	
	@Column(name="leaveTypeName")
	private String leaveTypeName;

	@Column(name ="allowed")
	private int allowed;
	
	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public int getAllowed() {
		return allowed;
	}

	public void setAllowed(int allowed) {
		this.allowed = allowed;
	}
	
	
}


	
