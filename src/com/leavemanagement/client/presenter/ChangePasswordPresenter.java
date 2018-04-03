package com.leavemanagement.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.presenter.LoginPresenter.Display;
import com.leavemanagement.client.view.LoadingPopup;
import com.leavemanagement.shared.User;

public class ChangePasswordPresenter implements Presenter{
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private User loggedInUser = new User();
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		Button getBtnSubmit() ;
		TextBox getConfrimPassword();
		TextBox getNewPassword();
		TextBox getOldPassword() ;
		
		
	}  

	public ChangePasswordPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.loggedInUser = loggedInUser;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		
		display.getBtnSubmit().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				try {
					fetchOldPassword();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		
	}

	private void fetchOldPassword() throws Exception {
		rpcService.fetchOldPassword(loggedInUser.getUserId(), new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchOldPassword");
			}

			@Override
			public void onSuccess(String oldPassword) {
				if(oldPassword.equals(display.getOldPassword().getText())){
					if(display.getNewPassword().getText().equals(display.getConfrimPassword().getText())){
						try {
							updatePassword();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						Window.alert("New Password and confirm new Password Do not match");
					}
				}else{
					Window.alert("Old Password not correct");
				}
			}

			});
	}
	
	private void updatePassword() throws Exception {
		loggedInUser.setPassword(display.getNewPassword().getText());

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.updatePassword(loggedInUser, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : updatePassword");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				History.newItem("login");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}});
	}
}