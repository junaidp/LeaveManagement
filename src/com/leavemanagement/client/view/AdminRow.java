package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class AdminRow extends HorizontalPanel{
	
	private Label lblName = new Label("");
	private Label lblType = new Label("");
	private Label lblFrom = new Label("");
	private Label lblTo = new Label("");
	private Label lblDays = new Label("");
	private Button btnApprove = new Button("Approve");
	private Button btnDecline = new Button("Decline");
	private TextBox txtRemarks =  new TextBox();
	private Label lblStatus = new Label("");
	private Label lblRemarks = new Label();
	HorizontalPanel hpnlButton = new HorizontalPanel();
	public AdminRow(){
		btnApprove.setStyleName("btnStyle");
		btnDecline.setStyleName("btnStyle");
		setStyleName("form-row");
		lblName.setWidth("180px");
		lblType.setWidth("180px");
		lblFrom.setWidth("180px");
		lblTo.setWidth("180px");
		lblDays.setWidth("180px");
		
		
		hpnlButton.add(btnApprove);
		hpnlButton.add(btnDecline);
		hpnlButton.add(txtRemarks);
		hpnlButton.setSpacing(4);
		
		add(lblName);
		add(lblType);
		add(lblFrom);
		add(lblTo);
		add(lblDays);
		add(hpnlButton);
		add(lblStatus);
		add(lblRemarks);
		
		hpnlButton.setVisible(false);
//		lblStatus.setVisible(false);
		txtRemarks.setVisible(false);
	}

	public Label getLblName() {
		return lblName;
	}

	public void setLblName(Label lblName) {
		this.lblName = lblName;
	}

	public Label getLblType() {
		return lblType;
	}

	public void setLblType(Label lblType) {
		this.lblType = lblType;
	}

	public Label getLblFrom() {
		return lblFrom;
	}

	public void setLblFrom(Label lblFrom) {
		this.lblFrom = lblFrom;
	}

	public Label getLblTo() {
		return lblTo;
	}

	public void setLblTo(Label lblTo) {
		this.lblTo = lblTo;
	}

	public Label getLblDays() {
		return lblDays;
	}

	public void setLblDays(Label lblDays) {
		this.lblDays = lblDays;
	}

	
	public Button getBtnApprove() {
		return btnApprove;
	}

	public void setBtnApprove(Button btnApprove) {
		this.btnApprove = btnApprove;
	}

	public Button getBtnDecline() {
		return btnDecline;
	}

	public void setBtnDecline(Button btnDecline) {
		this.btnDecline = btnDecline;
	}

	public TextBox getTxtRemarks() {
		return txtRemarks;
	}

	public void setTxtRemarks(TextBox txtRemarks) {
		this.txtRemarks = txtRemarks;
	}

	public Label getLblStatus() {
		return lblStatus;
	}

	public void setLblStatus(Label lblStatus) {
		this.lblStatus = lblStatus;
	}

	public HorizontalPanel getHpnlButton() {
		return hpnlButton;
	}

	public void setHpnlButton(HorizontalPanel hpnlButton) {
		this.hpnlButton = hpnlButton;
	}

	public Label getLblRemarks() {
		return lblRemarks;
	}

	public void setLblRemarks(Label lblRemarks) {
		this.lblRemarks = lblRemarks;
	}

}
