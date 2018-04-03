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

@Table(name="lineofservice")
public class LineofService   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="lineofServiceId")
	private int lineofServiceId;
	
	@Column(name="name")
	private String name;
	
	
	public LineofService(){}


	public int getLineofServiceId() {
		return lineofServiceId;
	}


	public void setLineofServiceId(int lineofServiceId) {
		this.lineofServiceId = lineofServiceId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	
}
