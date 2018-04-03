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

@Table(name="countries")
public class Countries   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="countryId")
	private int countryId;
	
	@Column(name="name")
	private String name;
	
	
	public Countries(){}


	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public int getCountryId() {
		return countryId;
	}




	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}


}
