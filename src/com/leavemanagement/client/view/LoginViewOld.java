package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.presenter.LoginPresenter.Display;


public class LoginViewOld extends Composite implements Display {
	public LoginViewOld() {
		if (vp == null) {
			vp = new VerticalPanel();
			vp.setSpacing(10);
			initWidget(vp);
			createForm2();
		}
	}
	
	private VerticalPanel vp;
	private TextBox txtUserName = new TextBox();
	private PasswordTextBox txtPassword = new PasswordTextBox();
	private Button btnSubmit = new Button("Submit");
	private Label lblError = new Label();

	private void createForm2() {
		lblError.setVisible(false);
		lblError.setStyleName("serverResponseLabelError");
		vp.add(lblError);
		Label lbl = new Label("Login");
		lbl.setStyleName("blue");
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, new Label("Name"));
		flex.setWidget(0, 1, txtUserName);
		txtUserName.setWidth("200px");
		txtPassword.setWidth("200px");
		flex.setWidget(1, 0, new Label("Password"));
		flex.setWidget(1, 1, txtPassword);
		flex.setWidget(2, 1, btnSubmit);
		btnSubmit.setWidth("100px");
		vp.add(flex);
	}
	
//	public Widget asWidget() {
//		if (vp == null) {
//			vp = new VerticalPanel();
//			vp.setSpacing(10);
//			initWidget(vp);
//			createForm2();
//		}
//		return vp;
//	}
	public PasswordTextBox getTxtPassword() {
		return txtPassword;
	}
	public void setTxtPassword(PasswordTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}
	public Label getLblError() {
		return lblError;
	}
	public void setLblError(Label lblError) {
		this.lblError = lblError;
	}
	public TextBox getTxtUserName() {
		return txtUserName;
	}
	public void setTxtUserName(TextBox txtUserName) {
		this.txtUserName = txtUserName;
	}

	public Button getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
	

}
