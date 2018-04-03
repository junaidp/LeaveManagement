package com.leavemanagement.shared;

import java.io.Serializable;

public class LeavesDTO implements Serializable{
	
	private LeaveTypes leaveType = new LeaveTypes();
	private long leavesAvaible ;
	private int allowed;
	private int leavesAvailed;
	
	public LeaveTypes getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveTypes leaveType) {
		this.leaveType = leaveType;
	}
	public long getLeavesAvaible() {
		return leavesAvaible;
	}
	public void setLeavesAvaible(long leaveAvailedforThisLeaveType) {
		this.leavesAvaible = leaveAvailedforThisLeaveType;
	}
	public int getAllowed() {
		return allowed;
	}
	public void setAllowed(int allowed) {
		this.allowed = allowed;
	}
	public int getLeavesAvailed() {
		return leavesAvailed;
	}
	public void setLeavesAvailed(int leavesAvailed) {
		this.leavesAvailed = leavesAvailed;
	}

}
