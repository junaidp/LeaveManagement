package com.leavemanagement.client.presenter;

import java.util.ArrayList;
import java.util.logging.Logger;


import com.google.gwt.user.client.History;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.client.event.ChangePasswordEvent;
import com.leavemanagement.client.event.MainEvent;
import com.leavemanagement.client.view.AdminRow;
import com.leavemanagement.client.view.AdminRowHeading;
import com.leavemanagement.client.view.JobCreationView;
import com.leavemanagement.client.view.LoadingPopup;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeavesDTOForAllUsers;
import com.leavemanagement.shared.User;

public class AdminPresenter implements Presenter 

{
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private Logger logger = Logger.getLogger("DashBoardPresenter");
	private User LoggedInUser = new User();

	public interface Display 
	{
		Widget asWidget();
		Object getHtmlErrorMessage = null;
		VerticalPanel getVpnlContainer();
		Button getBtnBack();
		Anchor getAddUser();
		VerticalPanel getVpnlEMployeeLeavesRecord();
		VerticalPanel getVpnlLeaveRecords();
		Anchor getChangePassword();
		Anchor getAddCompany();
		JobCreationView getJobCreationView();
	}  

	public AdminPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) 
	{
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.LoggedInUser = loggedInUser;
		if(loggedInUser.getRoleId().getRoleId()!=5){
			display.getAddUser().setVisible(false);
		}
		if(loggedInUser.getUserId()==1 && loggedInUser.getName().equals("faheem")){
			display.getAddCompany().setVisible(true);
		}
	}

	public void go(HasWidgets container) 
	{
		container.clear();
		container.add(display.asWidget());
		bind();
	}

	private void bind() {
		fetchPendingRequestsOfAllUsers();
		fetchLeaveRecords();
		
		fetchEmployeesLeaveRecord();
		fetchJobAttributes();
		
		
		
		display.getAddUser().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("addUser");	
				
				
			}});
		
		display.getBtnBack().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(LoggedInUser!=null){
				eventBus.fireEvent(new MainEvent(LoggedInUser));
				}else{
					History.newItem("login");
				}
			}});
		
		display.getChangePassword().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ChangePasswordEvent(LoggedInUser));
			}});
		
		display.getAddCompany().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("addCompany");
			}});

	}
	
	private void fetchJobAttributes() {
		rpcService.fetchJobAttributes(new AsyncCallback<JobAttributesDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchJobAttributes fail:" + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(JobAttributesDTO result) {
				display.getJobCreationView().setJobAttributes(result);
			}
		});
		
	}
	
	

	private void fetchEmployeesLeaveRecord() {

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchLeavesRemainingForAllUsers(new AsyncCallback<ArrayList<LeavesDTOForAllUsers>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
				Window.alert("fail : fetchLeavesRemainingForAllUsers");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(ArrayList<LeavesDTOForAllUsers> result) {
				HorizontalPanel hpnlEmployeeHeading = EmployeeLeavesHeading();
				display.getVpnlEMployeeLeavesRecord().add(hpnlEmployeeHeading);
				
				for(int j=0; j< result.size(); j++){
					LeavesDTOForAllUsers leavesDTOForAllUsers =result.get(j);
					Label lblEmployeeName = new Label();
					lblEmployeeName.setText(leavesDTOForAllUsers.getUser().getName());
					lblEmployeeName.setWidth("100%");
					lblEmployeeName.setStyleName("headerSigninSmall");
					display.getVpnlEMployeeLeavesRecord().add(lblEmployeeName);
					for(int i=0; i< leavesDTOForAllUsers.getLeavesDTO().size(); i++){
					HorizontalPanel hpnl = new HorizontalPanel();
					Label lblAllowed = new Label();
					Label lblAvailable = new Label(leavesDTOForAllUsers.getLeavesDTO().get(i).getLeavesAvaible()+"");
					Label lblAvailed = new Label(leavesDTOForAllUsers.getLeavesDTO().get(i).getLeavesAvailed()+"");
					
					Label lblName = new Label();
					
					lblName.setWidth("200px");
					lblAvailable.setWidth("200px");
					lblAvailed.setWidth("200px");
					lblName.setText(leavesDTOForAllUsers.getLeavesDTO().get(i).getLeaveType().getLeaveTypeName());
					if(leavesDTOForAllUsers.getLeavesDTO().get(i).getLeaveType().getLeaveTypeId()==5){
						lblAllowed.setText("");
						lblAvailable.setText("");
					}else{
					lblAllowed.setText(leavesDTOForAllUsers.getLeavesDTO().get(i).getAllowed()+"");
					}
					hpnl.setStyleName("form-row");
					hpnl.add(lblName);
					hpnl.add(new Label(""));
					hpnl.add(lblAvailed);
					hpnl.add(lblAvailable);
					hpnl.add(lblAllowed);
					hpnl.setSpacing(5);
					display.getVpnlEMployeeLeavesRecord().add(hpnl);
					
					}
					
				}
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				
			}

			
		});
		
	}
	
	private HorizontalPanel EmployeeLeavesHeading() {
		HorizontalPanel hpnlHeading = new HorizontalPanel();
		Label lblEmployeeNameHeading = new Label("Employee");
		Label lblLeaveNameHeading = new Label("Leave Type");
		Label lblLeavesAvailable = new Label("Available");
		Label lblLeavesAllowed = new Label("Allowed");
		Label lblLeavesAvailed = new Label("Availed");
		lblEmployeeNameHeading.setStyleName("blueBold");
		lblLeaveNameHeading.setStyleName("blueBold");
		lblLeavesAvailable.setStyleName("blueBold");
		lblLeavesAllowed.setStyleName("blueBold");
		lblLeavesAvailed.setStyleName("blueBold");
		hpnlHeading.add(lblEmployeeNameHeading);
//		hpnlHeading.add(lblLeaveNameHeading);
		hpnlHeading.add(lblLeavesAvailed);
		hpnlHeading.add(lblLeavesAvailable);
		hpnlHeading.add(lblLeavesAllowed);
		lblEmployeeNameHeading.setWidth("200px");
		lblLeaveNameHeading.setWidth("200px");
		lblLeavesAvailable.setWidth("200px");
		lblLeavesAvailed.setWidth("200px");
//		lblLeavesAllowed.setWidth("200px");
		return hpnlHeading;
	}

	private void fetchLeaveRecords(){
		display.getVpnlContainer().clear();
		int userId = 0;
		if(LoggedInUser!=null){
		userId = LoggedInUser.getUserId();
		}
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchLeavesRecord(userId, new AsyncCallback<ArrayList<LeaveRecord>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchLeavesRecord");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(final ArrayList<LeaveRecord> result) {
				AdminRowHeading heading = new AdminRowHeading();
				display.getVpnlContainer().add(heading);
				for(int i=0; i<result.size(); i++){
					final DataStorage dataStorage = new DataStorage();
					dataStorage.setId(i);
					final AdminRow row = new AdminRow();
					display.getVpnlContainer().add(row);
					row.getLblName().setText((result.get(i).getUserId().getName()));
					row.getLblDays().setText(result.get(i).getDays());
					if(result.get(i).getRemarks()!=null && !result.get(i).getRemarks().equals("")){
						row.getLblRemarks().setText(" ("+ result.get(i).getRemarks()+")");
					}
					row.getLblFrom().setText(result.get(i).getStartDate().toLocaleString());
					row.getLblTo().setText(result.get(i).getEndDate().toLocaleString());
					row.getLblType().setText(result.get(i).getLeaveType().getLeaveTypeName());
					row.getLblStatus().setText(result.get(i).getStatus());
					if(result.get(i).getStatus().equalsIgnoreCase("Approved")){
						row.getLblStatus().setStyleName("greencolor");
					}
					if(result.get(i).getStatus().equalsIgnoreCase("Declined")){
						row.getLblStatus().setStyleName("redcolor");
					}
					
				}
				if(loadingPopup!=null){
					loadingPopup.remove();
				}	
			}});
	}
	
//	public void fetchPendingRequests(){
//		if(LoggedInUser!=null && LoggedInUser.getRoleId().getRoleId()==5){
//			fetchPendingRequestsOfAllUsers();
//		}else{
//			fetchPendingRequestsOfLoggedInUser();
//		}
//	}

	private void fetchPendingRequestsOfAllUsers() {
		display.getVpnlLeaveRecords().clear();
//		int userId = 0;
//		if(LoggedInUser!=null){
//		userId = LoggedInUser.getUserId();
//		}

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchPendingLeavesRecord(new AsyncCallback<ArrayList<LeaveRecord>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: fetchPendingLeavesRecord");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(final ArrayList<LeaveRecord> result) {
				AdminRowHeading heading = new AdminRowHeading();
				display.getVpnlLeaveRecords().add(heading);
				for(int i=0; i<result.size(); i++){
					final DataStorage dataStorage = new DataStorage();
					dataStorage.setId(i);
					final AdminRow row = new AdminRow();
					display.getVpnlLeaveRecords().add(row);
					row.getLblName().setText((result.get(i).getUserId().getName()));
					row.getLblDays().setText(result.get(i).getDays());
					if(result.get(i).getRemarks()!=null && !result.get(i).getRemarks().equals("")){
						row.getLblRemarks().setText(" ("+ result.get(i).getRemarks()+")");
					}
					row.getLblFrom().setText(result.get(i).getStartDate().toLocaleString());
					row.getLblTo().setText(result.get(i).getEndDate().toLocaleString());
					row.getLblType().setText(result.get(i).getLeaveType().getLeaveTypeName());
					row.getLblStatus().setText(result.get(i).getStatus());
					if(result.get(i).getStatus().equalsIgnoreCase("Approved")){
						row.getLblStatus().setStyleName("greencolor");
					}
					else if(result.get(i).getStatus().equalsIgnoreCase("Pending")){
						row.getLblStatus().setStyleName("redcolor");
					}

					if(result.get(i).getStatus().equalsIgnoreCase("pending")){
						showAdminPanel(row);
					}

					if(loadingPopup!=null){
						loadingPopup.remove();
					}
					row.getBtnApprove().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							row.getBtnApprove().setEnabled(false);
							result.get(dataStorage.getId()).setRemarks(row.getTxtRemarks().getText());
							result.get(dataStorage.getId()).setStatus("Approved");
							try {
								approveRequest(result.get(dataStorage.getId()), row.getBtnApprove());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					});

					row.getBtnDecline().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							row.getBtnDecline().setEnabled(false);
							result.get(dataStorage.getId()).setRemarks(row.getTxtRemarks().getText());
							result.get(dataStorage.getId()).setStatus("Declined");
							try {
								declineRequest(result.get(dataStorage.getId()), row.getBtnDecline());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					});

					
				}
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			
		});
		
		
		
		
		
		
	}
	
	
	
	private void showAdminPanel(final AdminRow row) {
		row.getHpnlButton().setVisible(true);
		row.getTxtRemarks().setVisible(true);
		row.getLblRemarks().setVisible(false);
		row.getLblStatus().setVisible(false);
	}
	
	private void hideAdminPanel(final AdminRow row) {
		row.getHpnlButton().setVisible(false);
		row.getTxtRemarks().setVisible(false);
		row.getLblRemarks().setVisible(true);
		row.getLblStatus().setVisible(true);
	}

	private void approveRequest(LeaveRecord leaveRecord, final Button btnApprove) throws Exception {
		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.approveDeclineRequest(leaveRecord, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : approve Request");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				btnApprove.setEnabled(true);
			}

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				fetchPendingRequestsOfAllUsers();
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				btnApprove.setEnabled(true);
			}});
	}

	private void declineRequest(LeaveRecord leaveRecord, final Button btnDecline) throws Exception {

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.approveDeclineRequest(leaveRecord, new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail : decline Request");
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
			}

			@Override
			public void onSuccess(String result) {
				Window.alert(result);
				fetchPendingRequestsOfAllUsers();
				if(loadingPopup!=null){
					loadingPopup.remove();
				}
				btnDecline.setEnabled(true);
			}});
	}
}


