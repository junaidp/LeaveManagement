package com.leavemanagement.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.presenter.LeaveHistoryPresenter.Display;


public class LeaveHistoryView extends VerticalPanel implements IsWidget,Display {
	
	private VerticalPanel vpnlContainer = new VerticalPanel();
	private Anchor logOff = new Anchor("Log off");
	private Button btnBack = new Button("Back");
	
	public LeaveHistoryView(){
		Label lblHeading = new Label("Leave History");
		
		lblHeading.setStyleName("headerSignin");
		lblHeading.setWidth(Window.getClientWidth()-100+"px");
//		add(lblHeading);
		
		HorizontalPanel hpnlWelcome = new HorizontalPanel();
		hpnlWelcome.setWidth("100%");
		HorizontalPanel hpnlSpace = new HorizontalPanel();
		hpnlSpace.setWidth("80%");
		hpnlWelcome.add(hpnlSpace);
		hpnlWelcome.add(logOff);
		hpnlWelcome.setSpacing(5);
		setSpacing(10);
		HorizontalPanel hpnlHeader = new HorizontalPanel();
		hpnlHeader.setWidth("100%");
		setWidth("100%");
		Label lblHeader = new Label("Leave Management System");
		lblHeader.setStyleName("headerSignin");
		hpnlHeader.setStyleName("headerSignin");
		hpnlHeader.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hpnlHeader.add(lblHeader);
		hpnlHeader.add(hpnlWelcome);
		
		add(hpnlHeader);
		add(vpnlContainer);
		
		logOff.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
			}});
	}


	public VerticalPanel getVpnlContainer() {
		return vpnlContainer;
	}


	public void setVpnlContainer(VerticalPanel vpnlContainer) {
		this.vpnlContainer = vpnlContainer;
	}


	public Button getBtnBack() {
		return btnBack;
	}


	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}


	
	
}
