package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.User;

public class TimeSheetReportView extends VerticalPanel{
	
	private 	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	private ListBox listUsers = new ListBox();
	private ListBox listJobs = new ListBox();
	private ListBox listMonth = new ListBox();
	private ListBox listJobType = new ListBox();
	private User loggedInUser = null;
	private Button btnSearch = new Button("Search");
	private VerticalPanel vpnlResult = new VerticalPanel();
	
	public TimeSheetReportView(User loggedInUser){
		this.loggedInUser = loggedInUser;
		listMonth.addItem("Select Month", "0");
		listMonth.addItem("Jan", "1");
		listMonth.addItem("Feb", "2");
		listMonth.addItem("Mar", "3");
		listMonth.addItem("Apr", "4");
		listMonth.addItem("May", "5");
		listMonth.addItem("Jun", "6");
		listMonth.addItem("Jul", "7");
		listMonth.addItem("Aug", "8");
		listMonth.addItem("Sep", "9");
		listMonth.addItem("Oct", "10");
		listMonth.addItem("Nev", "11");
		listMonth.addItem("Dec", "12");
		
		fetchJobs();
		fetchUsers();
		fetchJobType();
		
		HorizontalPanel hpnlSearch = new HorizontalPanel();
		add(hpnlSearch);
		hpnlSearch.add(listJobs);
		hpnlSearch.add(listMonth);
		hpnlSearch.add(listUsers);
		hpnlSearch.add(listJobType);
		hpnlSearch.add(btnSearch);
		add(vpnlResult);
		hpnlSearch.setSpacing(5);
		
		btnSearch.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				fetchTimeReport();
			}});
		
		
	}
	
	private void fetchTimeReport() {
		int selectedMonth = Integer.parseInt(listMonth.getValue(listMonth.getSelectedIndex()));
		int selecteduser = Integer.parseInt(listUsers.getValue(listUsers.getSelectedIndex()));
		int selectedJob = Integer.parseInt(listJobs.getValue(listJobs.getSelectedIndex()));
		int selectedJobType = Integer.parseInt(listJobType.getValue(listJobType.getSelectedIndex()));
		
		rpcService.fetchTimeReport(selectedJob, selectedMonth, selecteduser, selectedJobType,  new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchTimeReport");
			}

			@Override
			public void onSuccess(String result) {
				vpnlResult.clear();
				Label lbl = new Label(result);
				lbl.setStyleName("blue");
				vpnlResult.add(lbl);
			}
		});
	}
	
	private void fetchJobs() {
		rpcService.fetchJobs(loggedInUser, new AsyncCallback<ArrayList<Job>>() {
			
			@Override
			public void onSuccess(ArrayList<Job> result) {
				listJobs.clear();
				listJobs.addItem("All Jobs", "0");
				for(int i=0; i< result.size(); i++){
					listJobs.addItem(result.get(i).getJobName(), result.get(i).getJobId()+"");
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch jobs");
			}
		});
	}
	
	public void fetchUsers(){
		rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<User> result) {
				listUsers.clear();
				listUsers.addItem("All users", "0");
				for(int i=0; i< result.size(); i++){
					listUsers.addItem(result.get(i).getName(), result.get(i).getUserId()+"");
				}
			}
		});
	}
	
	public void fetchJobType(){
		rpcService.getLineOfServices(new AsyncCallback<ArrayList<LineofService>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<LineofService> result) {
				listJobType.clear();
				listJobType.addItem("All Job Types", "0");
				for(int i=0; i< result.size(); i++){
					listJobType.addItem(result.get(i).getName(), result.get(i).getLineofServiceId()+"");
				}
			}
		});
	}

}
