package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.presenter.AdminPresenter.Display;
import com.leavemanagement.shared.User;


public class AdminView extends VerticalPanel implements IsWidget,Display {
	
	private VerticalPanel vpnlContainer = new VerticalPanel();
	private Anchor logOff = new Anchor("Log off");
	private Button btnBack = new Button("Back");
	private Anchor addUser = new Anchor("Add User");
	private Anchor changePassword = new Anchor("Change Password");
	private Anchor addCompany = new Anchor("Add Company");
	
	private VerticalPanel vpnlLeaveRecords = new VerticalPanel();
	private VerticalPanel vpnlEMployeeLeavesRecord = new VerticalPanel();
	private VerticalPanel vpnlJobCreation = new VerticalPanel();
	private VerticalPanel vpnlJobPlanning = new VerticalPanel();
	private JobCreationView jobCreationView;
	
	
	public AdminView(User loggedInUser){
		jobCreationView =new JobCreationView(null, loggedInUser);
		addCompany.setVisible(false);
		btnBack.addStyleName("btnStyle");
		HorizontalPanel hpnlWelcome = new HorizontalPanel();
		hpnlWelcome.setWidth("100%");
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);
		hpnlWelcome.add(logOff);
		hpnlWelcome.setSpacing(5);
		setSpacing(10);
		HorizontalPanel hpnlHeader = new HorizontalPanel();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
		Label lblHeader = new Label("Leave Management System");
		lblHeader.setStyleName("headerSignin");
		hpnlHeader.setStyleName("headerSignin");
		hpnlHeader.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		
		add(hpnlHeader);
		VerticalPanel vpnlLeave  = new VerticalPanel();
		vpnlLeave.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		vpnlLeave.setWidth("100%");
		add(vpnlLeave);
		vpnlLeave.add(addUser);
		vpnlLeave.add(changePassword);
		vpnlLeave.add(addCompany);
		DecoratedTabPanel tabPanel = new DecoratedTabPanel();
		tabPanel.add(vpnlLeaveRecords ,    "Pending Leave Requests");
		tabPanel.add(vpnlContainer, "Leaves History");
		tabPanel.add(vpnlEMployeeLeavesRecord, "Employees Leave Record");
		tabPanel.add(jobCreationView, "Job Creation");
		tabPanel.add(new AttributesView(loggedInUser), "Attributes");
		tabPanel.add(new TimeSheetView(loggedInUser), "Time Sheet");
		if(loggedInUser.getReportingTo()==5){
		tabPanel.add(new TimeSheetReportView(loggedInUser), "Time Report");
		}
		
		add(tabPanel);
		tabPanel.selectTab(0);
		
		logOff.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}});
	}


	public VerticalPanel getVpnlContainer() {
		return vpnlContainer;
	}


	public void setVpnlContainer(VerticalPanel vpnlContainer) {
		this.vpnlContainer = vpnlContainer;
	}


	public Button getBtnBack() {
		return btnBack;
	}


	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}


	public Anchor getAddUser() {
		return addUser;
	}


	public void setAddUser(Anchor addUser) {
		this.addUser = addUser;
	}


	public VerticalPanel getVpnlLeaveRecords() {
		return vpnlLeaveRecords;
	}


	public void setVpnlLeaveRecords(VerticalPanel vpnlLeaveRecords) {
		this.vpnlLeaveRecords = vpnlLeaveRecords;
	}


	public VerticalPanel getVpnlEMployeeLeavesRecord() {
		return vpnlEMployeeLeavesRecord;
	}


	public void setVpnlEMployeeLeavesRecord(VerticalPanel vpnlEMployeeLeavesRecord) {
		this.vpnlEMployeeLeavesRecord = vpnlEMployeeLeavesRecord;
	}


	public Anchor getChangePassword() {
		return changePassword;
	}


	public void setChangePassword(Anchor changePassword) {
		this.changePassword = changePassword;
	}


	public Anchor getAddCompany() {
		return addCompany;
	}


	public void setAddCompany(Anchor addCompany) {
		this.addCompany = addCompany;
	}


	public VerticalPanel getVpnlJobCreation() {
		return vpnlJobCreation;
	}


	public void setVpnlJobCreation(VerticalPanel vpnlJobCreation) {
		this.vpnlJobCreation = vpnlJobCreation;
	}


	public JobCreationView getJobCreationView() {
		return jobCreationView;
	}


	public void setJobCreationView(JobCreationView jobCreationView) {
		this.jobCreationView = jobCreationView;
	}
	

	
}
