package com.leavemanagement.client.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.presenter.AddUserPresenter.Display;

public class AddUserView extends DecoratorPanel implements Display {
	
	private Button btnSend = new Button("Save");
	TextBox txtUser = new TextBox();
	PasswordTextBox txtPassword = new PasswordTextBox();
	PasswordTextBox txtConfrimPassword = new PasswordTextBox();
	TextBox txtEmail = new TextBox();
	TextBox txtExamLeaves = new TextBox();
	ListBox listUser = new ListBox();
	
	ListBox listDesignation = new ListBox();
	ListBox listReportingTo = new ListBox();
	DateBox joiningDate = new DateBox();
	TextBox contactNumber = new TextBox();
	TextBox chargeRate = new TextBox();
	TextBox bankAccountNumber = new TextBox();
	private Button btnRemove = new Button("delete user");
	private Button btnUpdate = new Button("update");
	
	
	public AddUserView(){
		VerticalPanel vpnl = new VerticalPanel();
		add(vpnl);
		chargeRate.setEnabled(false);
		btnSend.setStyleName("btnStyle");
		btnUpdate.setStyleName("btnStyle");
		btnRemove.setStyleName("btnStyle");
		Label lblHeading = new Label("Add a new user");
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth()-100+"px");
		listUser.addItem("Select User");
		HorizontalPanel hp = new HorizontalPanel();
		vpnl.add(hp);
		hp.setWidth("100%");
		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hp.add(lblHeading);
		
		FlexTable flex = new FlexTable();
		Label lblJoiningDate = new Label("Joingin Date");
		Label lblcontactNumber = new Label("Emergency Contact Number");
		Label lblChargeRate = new Label("Charge Rate");
		Label lblBankAccountNumber = new Label("Bank Account Number");
		Label lblDesignation = new Label("Designation");
		Label lblUser = new Label("UserName");
		Label lblPassword = new Label("Password");
		Label lblConfirmPassword = new Label("Confirm Password");
		Label lblEmail = new Label("Email");
		Label lblExamLeaves = new Label("Exam Leaves");
		Label lblEditUser = new Label("Edit User");
		Label lblReportingTo = new Label("Reporting To");
		
		flex.setWidget(0, 0, lblUser);
		flex.setWidget(0, 1, txtUser);
		flex.setWidget(1, 0, lblPassword);
		flex.setWidget(1, 1, txtPassword);
		flex.setWidget(2, 0, lblConfirmPassword);
		flex.setWidget(2, 1, txtConfrimPassword);
		flex.setWidget(3, 0, lblEmail);
		flex.setWidget(3, 1, txtEmail);
		flex.setWidget(4, 0, lblExamLeaves);
		flex.setWidget(4, 1, txtExamLeaves);
		flex.setWidget(5, 0, lblJoiningDate);
		flex.setWidget(5, 1, joiningDate);
		flex.setWidget(6, 0, lblcontactNumber);
		flex.setWidget(6, 1, contactNumber);
		flex.setWidget(7, 0, lblBankAccountNumber);
		flex.setWidget(7, 1, bankAccountNumber);
		flex.setWidget(8, 0, lblDesignation);
		flex.setWidget(8, 1, listDesignation);
		flex.setWidget(9, 0, lblReportingTo);
		flex.setWidget(9, 1, listReportingTo);
		flex.setWidget(10, 0, lblChargeRate);
		flex.setWidget(10, 1, chargeRate);
//		flex.setWidget(10, 2, new Label("Numeric value only"));
		flex.setWidget(0, 2, lblEditUser);
		flex.setWidget(0, 3, listUser);
		
		
		vpnl.add(flex);
		HorizontalPanel hpnButton = new HorizontalPanel();
		HorizontalPanel hpnSpace = new HorizontalPanel();
		hpnSpace.setWidth("160px");
		hpnButton.add(hpnSpace);
		hpnButton.add(btnSend);
		hpnButton.add(btnUpdate);
		hpnButton.add(btnRemove);
		hpnButton.setSpacing(5);
		vpnl.add(hpnButton);
		layout();
		vpnl.setSpacing(4);
		
	}
	
	public void layout(){
		
			txtUser.setWidth("200px");
			 txtPassword.setWidth("200px");
			 txtConfrimPassword.setWidth("200px");
			 txtEmail.setWidth("200px");
			 listUser.setWidth("200px");
			 listDesignation.setWidth("200px");
			 listReportingTo.setWidth("200px");
			 joiningDate.setWidth("200px");
			 contactNumber.setWidth("200px");
			 chargeRate.setWidth("200px");
			 bankAccountNumber.setWidth("200px");
			 btnUpdate.setWidth("80px");
			 btnRemove.setWidth("80px");
			 btnSend.setWidth("80px");
			 txtExamLeaves.setWidth("200px");
	}

	public Button getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(Button btnSend) {
		this.btnSend = btnSend;
	}

	public TextBox getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(TextBox txtUser) {
		this.txtUser = txtUser;
	}

	public TextBox getTxtPassword() {
		return txtPassword;
	}

	

	public TextBox getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextBox txtEmail) {
		this.txtEmail = txtEmail;
	}

	public PasswordTextBox getTxtConfrimPassword() {
		return txtConfrimPassword;
	}

	public void setTxtConfrimPassword(PasswordTextBox txtConfrimPassword) {
		this.txtConfrimPassword = txtConfrimPassword;
	}

	public void setTxtPassword(PasswordTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}

	public TextBox getTxtExamLeaves() {
		return txtExamLeaves;
	}

	public void setTxtExamLeaves(TextBox txtExamLeaves) {
		this.txtExamLeaves = txtExamLeaves;
	}

	public ListBox getListUser() {
		return listUser;
	}

	public void setListUser(ListBox listUser) {
		this.listUser = listUser;
	}

	public Button getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(Button btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public ListBox getListDesignation() {
		return listDesignation;
	}

	public void setListDesignation(ListBox listDesignation) {
		this.listDesignation = listDesignation;
	}

	public DateBox getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(DateBox joiningDate) {
		this.joiningDate = joiningDate;
	}

	public TextBox getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(TextBox contactNumber) {
		this.contactNumber = contactNumber;
	}

	public TextBox getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(TextBox bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public ListBox getListReportingTo() {
		return listReportingTo;
	}

	public void setListReportingTo(ListBox listReportingTo) {
		this.listReportingTo = listReportingTo;
	}

	public Button getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(Button btnRemove) {
		this.btnRemove = btnRemove;
	}

	public TextBox getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(TextBox chargeRate) {
		this.chargeRate = chargeRate;
	}

}
