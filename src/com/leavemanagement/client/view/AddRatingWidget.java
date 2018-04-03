package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class AddRatingWidget extends HorizontalPanel {

	Label txtAttribute = new Label();
	ListBox lstAttribute = new ListBox();
	Button btnSave = new Button("Save");
	ListBox listScore = new ListBox();
	Label lblRating = new Label();
	private int jobId=0;

	public AddRatingWidget() {
		lstAttribute.addItem("10%", "0");
		lstAttribute.addItem("20%", "1");
		lstAttribute.addItem("30%", "2");
		lstAttribute.addItem("40%", "3");
		lstAttribute.addItem("50%", "4");
		
		listScore.addItem("1");
		listScore.addItem("2");
		listScore.addItem("3");
		
		add(txtAttribute);
		add(lstAttribute);
		add(listScore);
		add(lblRating);
		add(btnSave);
		
		lblRating.setStyleName("blue");
		setSpacing(7);
		
		txtAttribute.setHeight("20px");
		lstAttribute.setHeight("30px");
		btnSave.setHeight("30px");
		

		calculate();
	}

	

	private void calculate() {
		listScore.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				float score = Integer.parseInt(listScore.getValue(listScore.getSelectedIndex()));
				String attributeValue = lstAttribute.getItemText(lstAttribute.getSelectedIndex());
				attributeValue = attributeValue.substring(0, attributeValue.length()-1);
				float attrValue = Integer.parseInt(attributeValue) /100;
				float rating = score* attrValue;
				lblRating.setText(rating+"");
			}});
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

	
	public void readOnlyView(){
		btnSave.setVisible(false);
		listScore.setEnabled(false);
		lstAttribute.setEnabled(false);
		
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}



	public Label getLblRating() {
		return lblRating;
	}



	public void setLblRating(Label lblRating) {
		this.lblRating = lblRating;
	}



	public Label getTxtAttribute() {
		return txtAttribute;
	}



	public void setTxtAttribute(Label txtAttribute) {
		this.txtAttribute = txtAttribute;
	}



	public ListBox getListScore() {
		return listScore;
	}



	public void setListScore(ListBox listScore) {
		this.listScore = listScore;
	}
}
