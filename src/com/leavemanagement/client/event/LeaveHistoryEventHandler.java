package com.leavemanagement.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LeaveHistoryEventHandler extends EventHandler {
	void onLeaveHistory(LeaveHistoryEvent event);
}
