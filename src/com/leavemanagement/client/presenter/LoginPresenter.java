package com.leavemanagement.client.presenter;

import java.util.logging.Logger;

import com.google.gwt.user.client.History;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.event.AdminEvent;
import com.leavemanagement.client.event.MainEvent;
import com.leavemanagement.client.view.LoadingPopup;
import com.leavemanagement.shared.User;

public class LoginPresenter implements Presenter 

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	
	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		
		PasswordTextBox getTxtPassword();
		TextBox getTxtUserName();
		HasClickHandlers getBtnSubmit();
//		ListBox getListYears();
		Label getLblError();
		
	}  

	public LoginPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		
		
		
		RootPanel.get("loadingMessage").setVisible(false);
		
		display.getBtnSubmit().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				if(display.getTxtUserName().getText().equals("")|| display.getTxtPassword().getText().equals("")){
					display.getLblError().setVisible(true);
					display.getLblError().setText("username / password cannot be empty");
				}else{
					
					signIn(display.getTxtUserName().getText(),display.getTxtPassword().getText());
					
				}
			}

			
		});

	}

	

	public void signIn(String userName,String password)
	{
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.signIn(userName,password, new AsyncCallback<User>()
				

				{
			public void onFailure(Throwable ex) {
				Window.alert(ex.getStackTrace().toString());
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			public void onSuccess(User user) {
				if(user != null) 
				{
					
					display.getLblError().setVisible(false);
					if(user.getRoleId().getRoleId()==5){
//						History.newItem("admin");	
						eventBus.fireEvent(new AdminEvent(user));
					}else{
						eventBus.fireEvent(new MainEvent(user));
					}
				}
				else 
				{
					display.getLblError().setVisible(true);
					display.getLblError().setText("username / password invalid");
				}
				
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}
				});

	}
	}


