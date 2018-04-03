package com.leavemanagement.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.leavemanagement.shared.User;

public class ChangePasswordEvent extends GwtEvent<ChangePasswordEventHandler> {

	User loggedInUser = null;
	int selectedYear;

	public ChangePasswordEvent(User loggedInUser){
		this.loggedInUser = loggedInUser;
	}

	public ChangePasswordEvent(User loggedInUser, int year) {
		this.loggedInUser = loggedInUser;
		this.selectedYear = year;
	}

	public static Type<ChangePasswordEventHandler> TYPE = new Type<ChangePasswordEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ChangePasswordEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ChangePasswordEventHandler handler) {
		handler.onChangePassword(this);

	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public int getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(int selectedYear) {
		this.selectedYear = selectedYear;
	}

}


