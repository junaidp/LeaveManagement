package com.leavemanagement.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name="jobAttributes")
public class JobAttributes   implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jobAttributeId")
	private int jobAttributeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="level")
	private int level;
	
	@Column(name="jobId")
	private int jobId;
	
	@Column(name="rating")
	private String rating;
	
	@Column(name="score")
	private int score;
	
	
	public JobAttributes(){}


	public int getJobAttributeId() {
		return jobAttributeId;
	}


	public void setJobAttributeId(int jobAttributeId) {
		this.jobAttributeId = jobAttributeId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


}

