package com.leavemanagement.client.view;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CostWidget extends VerticalPanel {
	
	private Label cost = new Label("");
	private Image imgRefresh = new Image("refresh.png");
	public CostWidget(){
		add(imgRefresh);
		Label lbl = new Label("Cost");
		add(lbl);
		lbl.setStyleName("blue");
		add(cost);
	}

	public Label getCost() {
		return cost;
	}

	public void setCost(Label cost) {
		this.cost = cost;
	}

	public Image getImgRefresh() {
		return imgRefresh;
	}

	public void setImgRefresh(Image imgRefresh) {
		this.imgRefresh = imgRefresh;
	}

}
