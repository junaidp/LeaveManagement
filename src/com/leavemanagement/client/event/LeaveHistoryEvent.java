package com.leavemanagement.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.leavemanagement.shared.User;

		public class LeaveHistoryEvent extends GwtEvent<LeaveHistoryEventHandler> {
			
			User loggedInUser = null;
			int selectedYear;
			
			public LeaveHistoryEvent(User loggedInUser){
				this.loggedInUser = loggedInUser;
			}
			
		  public LeaveHistoryEvent(User loggedInUser, int year) {
			  this.loggedInUser = loggedInUser;
			  this.selectedYear = year;
			}

		public static Type<LeaveHistoryEventHandler> TYPE = new Type<LeaveHistoryEventHandler>();

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<LeaveHistoryEventHandler> getAssociatedType() {
		    return TYPE;
		}

		@Override
		protected void dispatch(LeaveHistoryEventHandler handler) {
		    handler.onLeaveHistory(this);
			
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


