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

@Table(name="domains")
public class Domains   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="domainId")
	private int domainId;
	
	@Column(name="name")
	private String name;
	
	@JoinColumn(name = "lineofServiceId")
	@ManyToOne(fetch = FetchType.LAZY)
	private LineofService lineofServiceId;
	
	
	public Domains(){}


	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public int getDomainId() {
		return domainId;
	}




	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}




	public LineofService getLineofServiceId() {
		return lineofServiceId;
	}




	public void setLineofServiceId(LineofService lineofServiceId) {
		this.lineofServiceId = lineofServiceId;
	}


	
}
