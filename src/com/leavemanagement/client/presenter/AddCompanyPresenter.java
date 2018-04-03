package com.leavemanagement.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.view.LoadingPopup;
import com.leavemanagement.shared.Company;
import com.leavemanagement.shared.Roles;
import com.leavemanagement.shared.User;

public class AddCompanyPresenter implements Presenter 

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("AddUserPresenter");
	private User LoggedInUser = new User();
	

	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		Button getBtnSend();
		TextBox getTxtUser();
		TextBox getTxtPassword();
		TextBox getTxtConfrimPassword();
		TextBox getTxtEmail();
		TextBox getTxtCompanyName();
		
	}  

	public AddCompanyPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.LoggedInUser = loggedInUser;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
		
	}
	
	
	public void bind(){
	
		
		display.getBtnSend().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				display.getBtnSend().setEnabled(false);
				if(display.getTxtPassword().getText().equals(display.getTxtConfrimPassword().getText())){
					try {
						addUser(0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					Window.alert("Password and confirm password do not match");
				}
			}

		});

	}

	private void addUser(int currentUserId) throws Exception {
		User user = new User();
		user.setEmail(display.getTxtEmail().getText());
		user.setName(display.getTxtUser().getText());
		user.setPassword(display.getTxtPassword().getText());
		user.setExamLeaves(0);
		user.setBacnkAcNo("0");
		user.setContactNo("0");
		user.setJoiningDate(new Date());
		Roles role = new Roles();
		role.setRoleId(5);
		user.setRoleId(role);
			user.setUserId(currentUserId);
	
		
		Company company = new Company();
		company.setCompanyName(display.getTxtCompanyName().getText());

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.addCompany(company,user, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Window.alert("Company Added");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				display.getBtnSend().setEnabled(true);
				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : add user");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				display.getBtnSend().setEnabled(true);
			}
		});
	}

}
