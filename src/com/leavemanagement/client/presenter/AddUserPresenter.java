package com.leavemanagement.client.presenter;

import java.util.ArrayList;
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
import com.leavemanagement.shared.Roles;
import com.leavemanagement.shared.User;

public class AddUserPresenter implements Presenter 

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("AddUserPresenter");
	private User LoggedInUser = new User();
	private ArrayList<User> users = new ArrayList<User>();
	private int currentUserId=0;

	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		Button getBtnSend();
		Button getBtnRemove();
		TextBox getTxtUser();
		TextBox getTxtPassword();
		TextBox getTxtConfrimPassword();
		TextBox getTxtEmail();
		TextBox getTxtExamLeaves();
		ListBox getListUser();
		Button getBtnUpdate();
		TextBox getContactNumber();
		TextBox getChargeRate();
		TextBox getBankAccountNumber();
		DateBox getJoiningDate();
		ListBox getListDesignation();
		ListBox getListReportingTo();
	}  

	public AddUserPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) 
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
		fetchAllUsers();
		fetchAllRoles();
	
	}
	
	public void fetchAllRoles(){
		rpcService.fetchAllRoles(new AsyncCallback<ArrayList<Roles>>(){

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("fetch roles failes");
			}

			@Override
			public void onSuccess(ArrayList<Roles> result) {
				display.getListDesignation().clear();
				for(int i=0; i< result.size(); i++)
				{
					display.getListDesignation().addItem(result.get(i).getRoleName(), result.get(i).getRoleId()+"");
				}	
				}});
	}

	private void fetchAllUsers(){

final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>(){

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("fail fetchAllUsers");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(ArrayList<User> result) {
				display.getListUser().clear();
				display.getListReportingTo().clear();
				display.getListUser().addItem("Select User");
				users.clear();
				
				for(int i=0; i< result.size(); i++){
				display.getListUser().addItem(result.get(i).getName(), (result.get(i).getUserId()+""));
				display.getListReportingTo().addItem(result.get(i).getName(), (result.get(i).getUserId()+""));
				users.add(result.get(i));
				}
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}});
	}

	private void bind() {
		display.getListUser().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				for(int i=0; i< users.size(); i++){
					if(users.get(i).getUserId() == Integer.parseInt(display.getListUser().getValue(display.getListUser().getSelectedIndex()))){
						display.getTxtUser().setText(users.get(i).getName());
						display.getTxtEmail().setText(users.get(i).getEmail());
						display.getTxtExamLeaves().setText(users.get(i).getExamLeaves()+"");
						display.getTxtPassword().setText(users.get(i).getPassword());
						display.getTxtConfrimPassword().setText(users.get(i).getPassword());
						display.getBankAccountNumber().setText(users.get(i).getBacnkAcNo());
						display.getContactNumber().setText(users.get(i).getContactNo());
						display.getChargeRate().setText(users.get(i).getRoleId().getChargeRate()+"");
						
						display.getJoiningDate().setValue(users.get(i).getJoiningDate());
						for(int j=0; j< display.getListDesignation().getItemCount(); j++){
							if(display.getListDesignation().getValue(j).equals(users.get(i).getRoleId().getRoleId()+"")){
								display.getListDesignation().setSelectedIndex(j);
								break;
							}
						}
						
						for(int j=0; j< display.getListReportingTo().getItemCount(); j++){
							if(display.getListReportingTo().getValue(j).equals(users.get(i).getReportingTo()+"")){
								display.getListReportingTo().setSelectedIndex(j);
								break;
							}
						}
						currentUserId = users.get(i).getUserId();
						
					}
				}
			}
		});
		
		display.getBtnUpdate().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.getBtnUpdate().setEnabled(false);
				try {
					if(currentUserId!=0){
					addUser(currentUserId);
					}else{
						Window.alert("Please select User");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		display.getBtnRemove().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.getBtnUpdate().setEnabled(false);
				try {
					if(currentUserId!=0){
					boolean confirm = 	Window.confirm("Are you sure you want to delete this user?");
					if(confirm){
						removeUser(currentUserId);
					}
					}else{
						Window.alert("Please select User");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		
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
	
	private void removeUser(int currentUserId) {
		rpcService.deleteUser(currentUserId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: user delete");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				fetchAllUsers();
			}
		});
	}

	private void addUser(int currentUserId) throws Exception {
		User user = new User();
		user.setEmail(display.getTxtEmail().getText());
		user.setName(display.getTxtUser().getText());
		user.setPassword(display.getTxtPassword().getText());
		user.setExamLeaves(Integer.parseInt(display.getTxtExamLeaves().getText()));
		user.setBacnkAcNo(display.getBankAccountNumber().getText());
		user.setContactNo(display.getContactNumber().getText());
//		user.setChargeRate(Integer.parseInt(display.getChargeRate().getText()));
		user.setJoiningDate(display.getJoiningDate().getValue());
		user.setReportingTo(Integer.parseInt(display.getListReportingTo().getValue(display.getListReportingTo().getSelectedIndex())));
		user.setCompanyId(LoggedInUser.getCompanyId());
		Roles role = new Roles();
		role.setRoleId(Integer.parseInt(display.getListDesignation().getValue(display.getListDesignation().getSelectedIndex())));
		user.setRoleId(role);
		if(currentUserId!=0){
			user.setUserId(currentUserId);
		}
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.addUser(user, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				fetchAllUsers();
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				display.getBtnSend().setEnabled(true);
				display.getBtnUpdate().setEnabled(true);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : add user");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				display.getBtnSend().setEnabled(true);
				display.getBtnUpdate().setEnabled(true);
			}
		});
	}

}
