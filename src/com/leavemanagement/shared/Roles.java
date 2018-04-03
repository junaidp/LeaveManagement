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

@Table(name="roles")
public class Roles   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="roleId")
	private int roleId;
	
	@Column(name="roleName")
	private String roleName;
	
	@Column(name="chargeRate")
	private int chargeRate;
	
	
	public Roles(){}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public int getChargeRate() {
		return chargeRate;
	}


	public void setChargeRate(int chargeRate) {
		this.chargeRate = chargeRate;
	}


	
	
}
