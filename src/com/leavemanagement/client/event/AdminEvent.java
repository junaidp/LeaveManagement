package com.leavemanagement.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.leavemanagement.shared.User;

		public class AdminEvent extends GwtEvent<AdminEventHandler> {
			
			User loggedInUser = null;
			int selectedYear;
			
			public AdminEvent(User loggedInUser){
				this.loggedInUser = loggedInUser;
			}
			
		  public AdminEvent(User loggedInUser, int year) {
			  this.loggedInUser = loggedInUser;
			  this.selectedYear = year;
			}

		public static Type<AdminEventHandler> TYPE = new Type<AdminEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<AdminEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(AdminEventHandler handler) {
		    handler.onAdmin(this);
			
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


