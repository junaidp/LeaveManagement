package com.leavemanagement.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class JobAttributesDTO implements Serializable {
	
	private ArrayList<LineofService> lineofService = new ArrayList<LineofService>();
	private ArrayList<SubLineofService> subLineofService = new ArrayList<SubLineofService>();
	private ArrayList<Domains> domains = new ArrayList<Domains>();
	private ArrayList<Countries> countries = new ArrayList<Countries>();
	
	public JobAttributesDTO(){
		
	}

	public ArrayList<LineofService> getLineofService() {
		return lineofService;
	}

	public void setLineofService(ArrayList<LineofService> lineofService) {
		this.lineofService = lineofService;
	}

	public ArrayList<SubLineofService> getSubLineofService() {
		return subLineofService;
	}

	public void setSubLineofService(ArrayList<SubLineofService> subLineofService) {
		this.subLineofService = subLineofService;
	}

	public ArrayList<Domains> getDomains() {
		return domains;
	}

	public void setDomains(ArrayList<Domains> domains) {
		this.domains = domains;
	}

	public ArrayList<Countries> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Countries> countries) {
		this.countries = countries;
	}

	
}
