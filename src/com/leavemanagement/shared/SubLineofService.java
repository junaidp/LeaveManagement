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

@Table(name="sublineofservice")
public class SubLineofService   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subLineofServiceId")
	private int subLineofServiceId;
	
	@Column(name="name")
	private String name;
	
	@JoinColumn(name = "domainId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Domains domainId;
	
	
	public SubLineofService(){}


	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public int getSubLineofServiceId() {
		return subLineofServiceId;
	}




	public void setSubLineofServiceId(int subLineofServiceId) {
		this.subLineofServiceId = subLineofServiceId;
	}




	public Domains getDomainId() {
		return domainId;
	}




	public void setDomainId(Domains domainId) {
		this.domainId = domainId;
	}


	
	
}
