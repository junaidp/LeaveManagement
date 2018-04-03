package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class AdminRowHeading extends HorizontalPanel{
	
	public AdminRowHeading(){
	Label lblName = new Label("Name");
	Label lblType = new Label("Type");
	Label lblFrom = new Label("From");
	Label lblTo = new Label("To");
	Label lblDays = new Label("Days");
	Label lblDecision = new Label("Decision");
	
	
	lblName.setWidth("180px");
	lblType.setWidth("180px");
	lblFrom.setWidth("180px");
	lblTo.setWidth("180px");
	lblDays.setWidth("180px");
	lblDecision.setWidth("180px");
	
	lblName.setStyleName("blue");
	lblType.setStyleName("blue");
	lblFrom.setStyleName("blue");
	lblTo.setStyleName("blue");
	lblDays.setStyleName("blue");
	lblDecision.setStyleName("blue");
	
	add(lblName);
	add(lblType);
	add(lblFrom);
	add(lblTo);
	add(lblDays);
	add(lblDecision);
	}
	
	

}
