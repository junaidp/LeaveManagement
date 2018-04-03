package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.presenter.MainPresenter.Display;


public class MainView extends VerticalPanel implements IsWidget,Display {
	
	
	private VerticalPanel vp;
	private VerticalPanel vpnlAvailableLeaves = new VerticalPanel();
	private ListBox listLeaves= new ListBox();
	private DateBox from = new DateBox();
	private DateBox to = new DateBox();
	private Label lblNoOfDays = new Label("");
	private TextArea reason = new TextArea();
	private Button btnSubmit = new Button("Submit");
	private Anchor logOff = new Anchor("Log off");
	private Label loggedInUserName = new Label();
	private Anchor  leaveHistory = new Anchor("Leave History");
	private Anchor changePassword = new Anchor("Change Password");
	private Anchor adminView = new Anchor("Admin");
	
	public MainView(){
		btnSubmit.setStyleName("btnStyle");
		setWidth("100%");
		HorizontalPanel hpnlWelcome = new HorizontalPanel();
		hpnlWelcome.setWidth("100%");
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);
		loggedInUserName.setStyleName("blue");
		hpnlWelcome.add(loggedInUserName);
//		hpnlWelcome.add(logOff);
		hpnlWelcome.setSpacing(5);
		
		setSpacing(10);
		HorizontalPanel hpnlHeader = new HorizontalPanel();
		hpnlHeader.setWidth("100%");
		hpnlHeader.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setWidth("100%");
		Label lblHeader = new Label("Leave Management System");
		lblHeader.setStyleName("headerSignin");
		hpnlWelcome.setStyleName("headerSignin");
		hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		Label lblAvailableLeaves = new Label("Available Leaves");
		VerticalPanel vpnlLeave  = new VerticalPanel();
		vpnlLeave.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		vpnlLeave.setWidth("100%");
//		HorizontalPanel hpnlSpaceLeave = new HorizontalPanel();
//		hpnlSpaceLeave.setWidth("800px");
//		hpnlLeave.add(hpnlSpaceLeave);
		vpnlLeave.add(changePassword);
		vpnlLeave.add(leaveHistory);
		vpnlLeave.add(adminView);
		vpnlLeave.add(logOff);
		add(hpnlHeader);
		hpnlHeader.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(vpnlLeave);
		add(lblAvailableLeaves);
		add(vpnlAvailableLeaves);
		vpnlAvailableLeaves.setSpacing(5);
		vpnlAvailableLeaves.setBorderWidth(1);
		
		Label lblRequest = new Label("Leave Request");
		lblRequest.setStyleName("blue");
		lblAvailableLeaves.setStyleName("blue");
		add(lblRequest);
		add(listLeaves);
		HorizontalPanel hpnlDates = new HorizontalPanel();
		VerticalPanel vpnlFrom = new VerticalPanel();
		VerticalPanel vpnlTo = new VerticalPanel();
		vpnlFrom.add(new Label("From"));
		vpnlFrom.add(from);
		vpnlTo.add(new Label("To"));
		vpnlTo.add(to);
		hpnlDates.add(vpnlFrom);
		hpnlDates.add(vpnlTo);
		hpnlDates.setSpacing(5);
		add(hpnlDates);
		add(lblNoOfDays);
		Label lblReason = new Label("Reason");
		lblReason.setStyleName("blue");
		add(lblReason);
		add(reason);
		add(btnSubmit);
		reason.setSize("400px", "150px");
		
		
	}
	
//	public Widget asWidget() {
//		return btnSend;
//
//	}
	

	public VerticalPanel getVpnlAvailableLeaves() {
		return vpnlAvailableLeaves;
	}

	public void setVpnlAvailableLeaves(VerticalPanel vpnlAvailableLeaves) {
		this.vpnlAvailableLeaves = vpnlAvailableLeaves;
	}

	public ListBox getListLeaves() {
		return listLeaves;
	}

	public void setListLeaves(ListBox listLeaves) {
		this.listLeaves = listLeaves;
	}

	public DateBox getFrom() {
		return from;
	}

	public void setFrom(DateBox from) {
		this.from = from;
	}

	public DateBox getTo() {
		return to;
	}

	public void setTo(DateBox to) {
		this.to = to;
	}

	public Label getLblNoOfDays() {
		return lblNoOfDays;
	}

	public void setLblNoOfDays(Label lblNoOfDays) {
		this.lblNoOfDays = lblNoOfDays;
	}

	public TextArea getReason() {
		return reason;
	}

	public void setReason(TextArea reason) {
		this.reason = reason;
	}

	public Button getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public Anchor getLogOff() {
		return logOff;
	}

	public void setLogOff(Anchor logOff) {
		this.logOff = logOff;
	}

	public Label getLoggedInUserName() {
		return loggedInUserName;
	}

	public void setLoggedInUserName(Label loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	public Anchor getLeaveHistory() {
		return leaveHistory;
	}

	public void setLeaveHistory(Anchor leaveHistory) {
		this.leaveHistory = leaveHistory;
	}

	public Anchor getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Anchor changePassword) {
		this.changePassword = changePassword;
	}

	public Anchor getAdminView() {
		return adminView;
	}

	public void setAdminView(Anchor adminView) {
		this.adminView = adminView;
	}

}
