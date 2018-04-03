package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.leavemanagement.shared.User;

public class AssignedToWidget extends FlexTable {
	Image btnRemove = new Image("deleteIcon.png");
	 ListBox listAssign = new ListBox();
	 TextBox txtDays = new TextBox();
	 private int jobEmployeeId= 0;
	public AssignedToWidget(ArrayList<User> employeesList){
		Label lbl = new Label("Assign to ");
		btnRemove.setTitle("Remove Employee");
		btnRemove.addStyleName("hover");
		txtDays.setWidth("20px");
		setWidget(0, 0, lbl);
		setWidget(0, 1, listAssign);
		setWidget(1, 0, new Label("Hours: "));
		setWidget(1, 1, txtDays);
		setWidget(1, 2, btnRemove);
		listAssign.clear();
		for(int i=0; i< employeesList.size(); i++){
			listAssign.addItem(employeesList.get(i).getName(), employeesList.get(i).getUserId()+"");
			
		}
		
	}
	
	public ListBox getListAssign() {
		return listAssign;
	}
	public void setListAssign(ListBox listAssign) {
		this.listAssign = listAssign;
	}
	public TextBox getTxtDays() {
		return txtDays;
	}
	public void setTxtDays(TextBox txtDays) {
		this.txtDays = txtDays;
	}
	public int getJobEmployeeId() {
		return jobEmployeeId;
	}
	public void setJobEmployeeId(int jobEmployeeId) {
		this.jobEmployeeId = jobEmployeeId;
	}

	public Image getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(Image btnRemove) {
		this.btnRemove = btnRemove;
	}

}
