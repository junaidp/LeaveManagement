package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class LeavesDTOForAllUsers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<LeavesDTO> leavesDTO = new ArrayList<LeavesDTO>();
	private User user= new User();
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<LeavesDTO> getLeavesDTO() {
		return leavesDTO;
	}
	public void setLeavesDTO(ArrayList<LeavesDTO> leavesDTO) {
		this.leavesDTO = leavesDTO;
	}

}
