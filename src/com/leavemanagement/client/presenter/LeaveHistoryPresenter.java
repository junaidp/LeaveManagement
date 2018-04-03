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
import com.leavemanagement.client.event.MainEvent;
import com.leavemanagement.client.view.AdminRow;
import com.leavemanagement.client.view.AdminRowHeading;
import com.leavemanagement.client.view.LoadingPopup;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeavesDTOForAllUsers;
import com.leavemanagement.shared.User;

public class LeaveHistoryPresenter implements Presenter 

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
		
	}  

	public LeaveHistoryPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view, User loggedInUser) 
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

	private void bind() {
		fetchPendingRequestsOfLoggedInUser();
		
		
		display.getBtnBack().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(LoggedInUser!=null){
				eventBus.fireEvent(new MainEvent(LoggedInUser));
				}else{
					History.newItem("login");
				}
			}});
	}
	
	
	
	private void fetchPendingRequestsOfLoggedInUser() {
		display.getVpnlContainer().clear();
		int userId = 0;
		if(LoggedInUser!=null){
		userId = LoggedInUser.getUserId();
		}

		final LoadingPopup loadingPopup = new LoadingPopup();
		loadingPopup.display();
		rpcService.fetchPendingLeavesRecordOfLoggedInUser(userId, new AsyncCallback<ArrayList<LeaveRecord>>() {

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
					else if(result.get(i).getStatus().equalsIgnoreCase("Pending")){
						row.getLblStatus().setStyleName("redcolor");
					}

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

	
}


