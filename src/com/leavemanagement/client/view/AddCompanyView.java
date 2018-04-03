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
import com.leavemanagement.client.presenter.AddCompanyPresenter.Display;

public class AddCompanyView extends DecoratorPanel implements Display {
	
	private Button btnSend = new Button("Save");
	TextBox txtUser = new TextBox();
	PasswordTextBox txtPassword = new PasswordTextBox();
	PasswordTextBox txtConfrimPassword = new PasswordTextBox();
	TextBox txtEmail = new TextBox();
	TextBox txtCompanyName = new TextBox();
	
	
	private Button btnUpdate = new Button("update");
	
	
	public AddCompanyView(){
		VerticalPanel vpnl = new VerticalPanel();
		add(vpnl);
		btnSend.setStyleName("btnStyle");
		btnUpdate.setStyleName("btnStyle");
		Label lblHeading = new Label("Add a new Company");
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth()-100+"px");
		HorizontalPanel hp = new HorizontalPanel();
		hp.setWidth("100%");
		vpnl.add(hp);
		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hp.add(lblHeading);
		FlexTable flex = new FlexTable();
		Label lblUser = new Label("UserName");
		Label lblPassword = new Label("Password");
		Label lblConfirmPassword = new Label("Confirm Password");
		Label lblEmail = new Label("Email");
		Label lblCompanyName = new Label("Company Name");
		vpnl.setSpacing(4);
		flex.setWidget(0, 0, lblUser);
		flex.setWidget(0, 1, txtUser);
		flex.setWidget(1, 0, lblPassword);
		flex.setWidget(1, 1, txtPassword);
		flex.setWidget(2, 0, lblConfirmPassword);
		flex.setWidget(2, 1, txtConfrimPassword);
		flex.setWidget(3, 0, lblEmail);
		flex.setWidget(3, 1, txtEmail);
		flex.setWidget(4, 0, lblCompanyName);
		flex.setWidget(4, 1, txtCompanyName);
		
		
		vpnl.add(flex);
		HorizontalPanel hpnButton = new HorizontalPanel();
		HorizontalPanel hpnSpace = new HorizontalPanel();
		hpnSpace.setWidth("150px");
		hpnButton.add(hpnSpace);
		hpnButton.add(btnSend);
		hpnButton.setSpacing(5);
		vpnl.add(hpnButton);
		layout();
		
	}
	
	public void layout(){
		
			txtUser.setWidth("200px");
			 txtPassword.setWidth("200px");
			 txtConfrimPassword.setWidth("200px");
			 txtEmail.setWidth("200px");
			
			 btnUpdate.setWidth("80px");
			 btnSend.setWidth("80px");
			 txtCompanyName.setWidth("200px");
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

	public TextBox getTxtCompanyName() {
		return txtCompanyName;
	}

	public void setTxtCompanyName(TextBox txtCompanyName) {
		this.txtCompanyName = txtCompanyName;
	}

	

}
