package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.User;

public class RatingView extends VerticalPanel {

    VerticalPanel container = new VerticalPanel();
    private User loggedInUser;
    private GreetingServiceAsync rpcService = GWT.create(GreetingService.class);

    public RatingView(User loggedInUser) {
	this.loggedInUser = loggedInUser;
	Label lblAttributes = new Label("Ratings");
	container.add(lblAttributes);
	Image refresh = new Image("refresh.png");
	add(refresh);
	refresh.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		fetchJobs();
	    }
	});
	add(container);
	setWidth("100%");
	// Image addImage = new Image("add.png");
	// add(addImage);
	fetchJobs();

	// addImage.addClickHandler(new ClickHandler(){});
    }

    public void fetchJobs() {
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

    private void populateJobsList(ArrayList<Job> jobs) {
	container.clear();
	Tree tree = new Tree();
	container.add(tree);
	for (int i = 0; i < jobs.size(); i++) {
	    final Job job = jobs.get(i);
	    final TreeItem jobTree = new TreeItem();
	    jobTree.setText(job.getJobName());
	    tree.addItem(jobTree);
	    // Image btnAddAttribute = new Image("add.png");
	    final VerticalPanel vpnl = new VerticalPanel();
	    jobTree.addItem(vpnl);

	    if (loggedInUser.getRoleId().getRoleId() == 5) {
		// vpnl.add(btnAddAttribute);
	    }

	    for (int j = 0; j < job.getJobAttributes().size(); j++) {
		final AddRatingWidget addRatingWidget = new AddRatingWidget();
		addRatingWidget.getBtnSave().setEnabled(false);
		if (loggedInUser.getRoleId().getRoleId() != 5) {
		    addRatingWidget.readOnlyView();

		}
		addRatingWidget.setJobId(job.getJobId());
		vpnl.add(addRatingWidget);
		addRatingWidget.getTxtAttribute().setText(job.getJobAttributes().get(j).getName());
		for (int k = 0; k < addRatingWidget.getLstAttribute().getItemCount(); k++) {
		    if (addRatingWidget.getLstAttribute().getValue(k)
			    .equals(job.getJobAttributes().get(j).getLevel() + "")) {
			addRatingWidget.getLstAttribute().setSelectedIndex(k);
			break;
		    }

		}
		for (int k = 0; k < addRatingWidget.getListScore().getItemCount(); k++) {
		    if (addRatingWidget.getListScore().getValue(k)
			    .equals(job.getJobAttributes().get(j).getScore() + "")) {
			addRatingWidget.getListScore().setSelectedIndex(k);
			break;
		    }

		}

		addRatingWidget.getLblRating().setText(job.getJobAttributes().get(j).getRating());

		final DataSetter data = new DataSetter();
		data.setId(j);
	    }
	}
	// addRatingWidget.getBtnDelete().addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// boolean confirm = Window.confirm("Attribute will be removed !");
	// if(confirm){
	// deleteJobAttribute(job.getJobAttributes().get(data.getId()).getJobAttributeId());
	// vpnl.remove(addRatingWidget);
	// }
	// }
	//
	// });
	// }

	// btnAddAttribute.addClickHandler(new ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// final AddAttributeWidget addAttributeNewWidget = new
	// AddAttributeWidget();
	// addAttributeNewWidget.setJobId(job.getJobId());
	//
	// vpnl.add(addAttributeNewWidget);
	// addAttributeNewWidget.getBtnSave().addClickHandler(new
	// ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// saveJobAttribute(job.getJobId(),
	// addAttributeNewWidget.getTxtAttribute().getText(),
	// addAttributeNewWidget.getLstAttribute().getValue(addAttributeNewWidget.getLstAttribute().getSelectedIndex())
	// );
	//
	// }
	//
	// });

	// addAttributeNewWidget.getBtnDelete().addClickHandler(new
	// ClickHandler(){
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// vpnl.remove(addAttributeNewWidget);
	// }});
	// }});
	// }

    }

    private void deleteJobAttribute(int jobAttributeId) {
	rpcService.deleteJobAttribute(jobAttributeId, new AsyncCallback<String>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail delete job Attribute");
	    }

	    @Override
	    public void onSuccess(String result) {
		Window.alert("job attribute deleted");
	    }
	});
    }

    private void saveJobAttribute(int jobId, String txtAttribute, String value) {

	JobAttributes jobAttributes = new JobAttributes();
	jobAttributes.setJobId(jobId);
	jobAttributes.setName(txtAttribute);
	jobAttributes.setLevel(Integer.parseInt(value));

	rpcService.saveJobAttribute(jobAttributes, new AsyncCallback<String>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail save job attributes");
	    }

	    @Override
	    public void onSuccess(String result) {
		Window.alert("job Attribute saved");
	    }
	});

    }

}
