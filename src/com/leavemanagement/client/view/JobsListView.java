package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.User;

public class JobsListView extends VerticalPanel {
    Column<Job, String> jobName;
    Column<Job, String> jobDomain;
    Column<Job, String> jobPhase;
    Column<Job, String> jobLineOfService;
    Column<Job, String> jobSubLineofService;
    Column<Job, String> deleteJob;
    private CellTable<Job> table = new CellTable<Job>();
    private User loggedInUser = null;

    Image imgRefresh = new Image("refresh.png");

    public JobsListView(User loggedInUser) {
	this.loggedInUser = loggedInUser;
	table.setWidth("100%");
	setTable();
	fetchJobs();
	add(imgRefresh);
	add(table);
	table.getColumn(0).setCellStyleNames("editCell");
	table.addColumnStyleName(0, "editCell");

	imgRefresh.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		fetchJobs();
	    }
	});
    }

    public void fetchJobs() {
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	rpcService.fetchJobs(loggedInUser, new AsyncCallback<ArrayList<Job>>() {

	    @Override
	    public void onSuccess(ArrayList<Job> jobs) {
		populateJobsList(jobs);
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("fetch jobs list failed");
	    }
	});
    }

    private void setTable() {
	table.setEmptyTableWidget(new HTML("No Record found"));
	table.setRowCount(0);

	jobName = new Column<Job, String>(new ClickableTextCell()) {
	    @Override
	    public String getValue(Job object) {
		return object.getJobName();
	    }
	};
	jobName.setCellStyleNames("hover");
	table.addColumn(jobName, "Name");

	jobDomain = new Column<Job, String>(new TextCell()) {
	    @Override
	    public String getValue(Job object) {
		return object.getDomainId().getName();
	    }
	};
	table.addColumn(jobDomain, "Domain");

	jobPhase = new Column<Job, String>(new TextCell()) {
	    @Override
	    public String getValue(Job object) {
		String phases = "";
		for (int i = 0; i < object.getJobPhases().size(); i++) {
		    if (object.getJobPhases().size() > 1) {
			if (object.getJobPhases().get(i).getPhaseName().length() > 10) {
			    phases = phases + " " + object.getJobPhases().get(i).getPhaseName().substring(0, 10) + " ,";
			} else {
			    phases = phases + " " + object.getJobPhases().get(i).getPhaseName() + " ,";

			}
		    } else {
			return object.getJobPhases().get(i).getPhaseName();
		    }
		}
		return phases;
	    }
	};
	table.addColumn(jobPhase, "Phases");

	jobLineOfService = new Column<Job, String>(new TextCell()) {
	    @Override
	    public String getValue(Job object) {
		return object.getLineofServiceId().getName();
	    }
	};
	table.addColumn(jobLineOfService, "Line of Service");

	jobSubLineofService = new Column<Job, String>(new TextCell()) {
	    @Override
	    public String getValue(Job object) {
		return object.getSubLineofServiceId().getName();
	    }
	};
	table.addColumn(jobSubLineofService, "Subline of Service");

	deleteJob = new Column<Job, String>(new ButtonCell()) {
	    @Override
	    public String getValue(Job object) {
		return "Delete";
	    }
	};
	if (loggedInUser.getRoleId().getRoleId() == 5) {
	    table.addColumn(deleteJob, "");
	}

	jobName.setFieldUpdater(new FieldUpdater<Job, String>() {

	    @Override
	    public void update(int index, Job object, String value) {
		JobEditView jobEditView = new JobEditView(object, loggedInUser);
		final PopupsView popup = new PopupsView(jobEditView);
		jobEditView.getBtnClose().addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			popup.getPopup().removeFromParent();

		    }
		});
	    }

	});

	deleteJob.setFieldUpdater(new FieldUpdater<Job, String>() {

	    @Override
	    public void update(int index, Job job, String value) {
		boolean confirmed = Window.confirm("Selected Job will be Deleted !");
		if (confirmed) {
		    deleteJob(job.getJobId());
		}
	    }

	});
    }

    private void deleteJob(int jobId) {
	GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	rpcService.deleteJob(jobId, new AsyncCallback<String>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("job delete Failed");
	    }

	    @Override
	    public void onSuccess(String result) {
		fetchJobs();

	    }
	});
    }

    public void populateJobsList(ArrayList<Job> jobs) {
	table.setRowData(0, jobs);
	table.setRowCount(jobs.size());

    }

}
