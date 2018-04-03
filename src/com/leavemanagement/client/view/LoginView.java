package com.leavemanagement.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite implements com.leavemanagement.client.presenter.LoginPresenter.Display {

	private static LoginUiBinder uiBinder = GWT
			.create(LoginUiBinder.class);

	interface LoginUiBinder extends UiBinder<Widget, LoginView> {
	}
	@UiField TextBox txtUserName;
	@UiField PasswordTextBox txtPassword;
	@UiField Button btnSubmit;
	@UiField Label lblError;
	
	public LoginView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Button getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}


	public TextBox getTxtUserName() {
		return txtUserName;
	}


	public void setTxtUserName(TextBox txtUserName) {
		this.txtUserName = txtUserName;
	}


	public PasswordTextBox getTxtPassword() {
		return txtPassword;
	}


	public void setTxtPassword(PasswordTextBox txtPassword) {
		this.txtPassword = txtPassword;
	}



	@Override
	public Label getLblError() {
		// TODO Auto-generated method stub
		return lblError;
	}


}
