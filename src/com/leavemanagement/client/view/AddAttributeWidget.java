package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class AddAttributeWidget extends HorizontalPanel {

	TextBox txtAttribute = new TextBox();
	ListBox lstAttribute = new ListBox();
	Button btnSave = new Button("Save");
	Button btnDelete = new Button("Delete");
	private int jobId=0;

	public AddAttributeWidget() {
		lstAttribute.addItem("10%", "0");
		lstAttribute.addItem("20%", "1");
		lstAttribute.addItem("30%", "2");
		lstAttribute.addItem("40%", "3");
		lstAttribute.addItem("50%", "4");

		add(txtAttribute);
		add(lstAttribute);
		add(btnSave);
		add(btnDelete);
		setSpacing(7);
		
		txtAttribute.setHeight("20px");
		lstAttribute.setHeight("30px");
		btnSave.setHeight("30px");
		btnDelete.setHeight("30px");


	}

	public TextBox getTxtAttribute() {
		return txtAttribute;
	}

	public void setTxtAttribute(TextBox txtAttribute) {
		this.txtAttribute = txtAttribute;
	}

	public ListBox getLstAttribute() {
		return lstAttribute;
	}

	public void setLstAttribute(ListBox lstAttribute) {
		this.lstAttribute = lstAttribute;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void readOnlyView(){
		btnDelete.setVisible(false);
		btnSave.setVisible(false);
		txtAttribute.setEnabled(false);
		lstAttribute.setEnabled(false);
		
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}
