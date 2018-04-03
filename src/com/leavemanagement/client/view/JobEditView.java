package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.User;

public class JobEditView extends VerticalPanel{
	private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	  private Button btnClose = new Button("Close");
	  JobCreationView jobCreationView;
	 private VerticalPanel vpnlMain = new VerticalPanel();
	 User loggedInUser;
	 private ArrayList<User> employeesList;
	
	public JobEditView(Job selectedJob, User loggedInUser){
		this.loggedInUser = loggedInUser;
		ScrollPanel scroll = new ScrollPanel();
		scroll.setHeight("800px");
		scroll.setWidth("800px");;
		add(scroll);
		scroll.setWidget(vpnlMain);
		
		jobCreationView = new JobCreationView(selectedJob, loggedInUser);
		jobCreationView.getTxtJobName().setText(selectedJob.getJobName());
		jobCreationView.getTxtClient().setText(selectedJob.getClient());
		jobCreationView.getTxtClientFee().setText(selectedJob.getClientFee()+"");
		jobCreationView.getJobsListView().setVisible(false);
		vpnlMain.add(jobCreationView);
		fetchEmployees(selectedJob);
		
		
		

		
		for(int i=0; i< selectedJob.getJobPhases().size(); i++){
			final Phases phase = selectedJob.getJobPhases().get(i);
			final JobPhaseView jobPhaseView = new JobPhaseView();
			vpnlMain.add(jobPhaseView);
			jobPhaseView.getBtnDelete().setVisible(true);
			jobPhaseView.getBtnCancel().setVisible(false);
			
			jobPhaseView.getTxtPhase().setText(phase.getPhaseName());
			jobPhaseView.getStartDate().setValue(phase.getStartDate());
			jobPhaseView.getSubmissionDate().setValue(phase.getSubmissionDate());
			jobPhaseView.getDeliveryDate().setValue(phase.getDeliveryDate());
			
			jobPhaseView.getBtnSubmit().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					phase.setDeliveryDate(jobPhaseView.getDeliveryDate().getValue());
					phase.setPhaseName(jobPhaseView.getTxtPhase().getText());
					phase.setStartDate(jobPhaseView.getStartDate().getValue());
					phase.setSubmissionDate(jobPhaseView.getSubmissionDate().getValue());
					updatePhase(phase);
				}

			});
			
			jobPhaseView.getBtnDelete().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					deletePhase(phase, jobPhaseView);
				}

				});
			
		}
		vpnlMain.add(btnClose);
	}

	
	private void fetchEmployees(final Job selectedJob) {
		rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail fetch users");
			}

			@Override
			public void onSuccess(ArrayList<User> result) {
				employeesList = result;
				fetchJobAttributes(selectedJob);
			}});
	}
	
	private void selectFields(Job selectedJob) {
		for(int i=0; i<jobCreationView.getListCountry().getItemCount(); i++ ){
			if(jobCreationView.getListCountry().getValue(i).equals(selectedJob.getCountryId().getCountryId()+"")){
				jobCreationView.getListCountry().setSelectedIndex(i);
				break;
			}
		}
		for(int i=0; i<jobCreationView.getListDomain().getItemCount(); i++ ){
			if(jobCreationView.getListDomain().getValue(i).equals(selectedJob.getDomainId().getDomainId()+"")){
				jobCreationView.getListDomain().setSelectedIndex(i);
				break;
			}
		}
		
		for(int i=0; i<jobCreationView.getListLineOfService().getItemCount(); i++ ){
			if(jobCreationView.getListLineOfService().getValue(i).equals(selectedJob.getLineofServiceId().getLineofServiceId()+"")){
				jobCreationView.getListLineOfService().setSelectedIndex(i);
				break;
			}
		}
		for(int i=0; i<jobCreationView.getListSubLineofService().getItemCount(); i++ ){
			if(jobCreationView.getListSubLineofService().getValue(i).equals(selectedJob.getSubLineofServiceId().getSubLineofServiceId()+"")){
				jobCreationView.getListSubLineofService().setSelectedIndex(i);
				break;
			}
		}
		
//		for(int i=0; i<jobCreationView.getListEmployees().getItemCount(); i++ ){
//			if(jobCreationView.getListEmployees().getValue(i).equals(selectedJob.employee1().getUserId()+"")){
//				jobCreationView.getListEmployees().setSelectedIndex(i);
//				break;
//			}
//		}
		
		setAssignedToList(selectedJob);
		
		
		for(int i=0; i<jobCreationView.getListSupervisor().getItemCount(); i++ ){
			if(jobCreationView.getListSupervisor().getValue(i).equals(selectedJob.getSupervisorId().getUserId()+"")){
				jobCreationView.getListSupervisor().setSelectedIndex(i);
				break;
			}
		}
		
		for(int i=0; i<jobCreationView.getListPrincipalConsultant().getItemCount(); i++ ){
			if(jobCreationView.getListPrincipalConsultant().getValue(i).equals(selectedJob.getPrincipalConsultantId().getUserId()+"")){
				jobCreationView.getListPrincipalConsultant().setSelectedIndex(i);
				break;
			}
		}
	}


	private void setAssignedToList(Job selectedJob) {
		jobCreationView.getVpnlEmployees().clear();
		for(int i=0; i<selectedJob.getJobEmployeesList().size(); i++){
			
			final AssignedToWidget assignToWidget = new AssignedToWidget(employeesList);
			for(int j=0; j<employeesList.size(); j++){
				assignToWidget.getListAssign().addItem(employeesList.get(j).getName(), employeesList.get(j).getUserId()+"");
			}
			
			for(int k=0; k<assignToWidget.getListAssign().getItemCount(); k++ ){
				if(assignToWidget.getListAssign().getValue(k).equals(selectedJob.getJobEmployeesList().get(i).getEmployeeId().getUserId()+"")){
					assignToWidget.getListAssign().setSelectedIndex(k);
					break;
				}
			}
			
			assignToWidget.getTxtDays().setText(selectedJob.getJobEmployeesList().get(i).getNoOfDays()+"");
			assignToWidget.setJobEmployeeId(selectedJob.getJobEmployeesList().get(i).getJobEmployeeId());
			jobCreationView.getVpnlEmployees().add(assignToWidget);
			
			assignToWidget.getBtnRemove().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					boolean confirmed = Window.confirm("Selected Employee will be removed from this Job !");
					if(confirmed){
					jobCreationView.getVpnlEmployees().remove(assignToWidget);
					removeJobEmployee(assignToWidget.getJobEmployeeId());
					}
				}

				});
		}
	}
	
	private void removeJobEmployee(int jobEmployeeId) {
		rpcService.deleteJobEmployee(jobEmployeeId, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fil deleting jobEmployee");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("employee removed from the job");
			}
		});
	}
	
	private void deletePhase(final Phases phase,
			final JobPhaseView jobPhaseView) {
		rpcService.deletePhase(phase, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("delete phase Failed");
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("phase deleted");
				remove(jobPhaseView);
			}
			
		});
	}
	
	private void updatePhase(final Phases phase) {
		rpcService.updatePhase(phase, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				Window.alert("phase updated");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("phase updation failed");
			}
		});
	}
	
	private void fetchJobAttributes(final Job selectedJob) {
		rpcService.fetchJobAttributes(new AsyncCallback<JobAttributesDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fetchJobAttributes fail:" + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(JobAttributesDTO result) {
				jobCreationView.setJobAttributes(result);
				selectFields(selectedJob);
				
			}
		});
		
	}

	public Button getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}

}
