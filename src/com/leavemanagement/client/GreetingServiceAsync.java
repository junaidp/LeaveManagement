package com.leavemanagement.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.leavemanagement.shared.Company;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributes;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.LeaveRecord;
import com.leavemanagement.shared.LeaveTypes;
import com.leavemanagement.shared.LeavesDTO;
import com.leavemanagement.shared.LeavesDTOForAllUsers;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.Roles;
import com.leavemanagement.shared.SubLineofService;
import com.leavemanagement.shared.TimeSheet;
import com.leavemanagement.shared.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void fetchLeavesRemaining(User loggedInUser, AsyncCallback<ArrayList<LeavesDTO>> callback)throws IllegalArgumentException;
	void signIn(String userid, String password, AsyncCallback<User> callback);
	void fetchLeaveTypes(AsyncCallback<ArrayList<LeaveTypes>> callback)throws IllegalArgumentException;
	void saveLeaveRequest(LeaveRecord leaveRecord, AsyncCallback<String> callback)throws IllegalArgumentException;
	void fetchDatesDifference(Date from , Date to,AsyncCallback<String> callback)throws IllegalArgumentException;
	void fetchLeavesRecord(int userId, AsyncCallback<ArrayList<LeaveRecord>> callback)throws IllegalArgumentException;
	void approveDeclineRequest(LeaveRecord leaveRecord, AsyncCallback<String> callback)throws Exception;
	void fetchOldPassword(int userId, AsyncCallback<String> callback)throws Exception;
	void updatePassword(User user, AsyncCallback<String> callback)throws Exception;
	void addUser(User user, AsyncCallback<String> callback)throws Exception;
	void addCompany(Company company, User user, AsyncCallback<String> callback)throws Exception;
	
	void fetchAllUsersExceptDirector(AsyncCallback<ArrayList<User>> callback);
	void fetchAllUsers(AsyncCallback<ArrayList<User>> callback);
	void fetchPendingLeavesRecord(AsyncCallback<ArrayList<LeaveRecord>> callback)throws IllegalArgumentException;
	void fetchPendingLeavesRecordOfLoggedInUser(int userId, AsyncCallback<ArrayList<LeaveRecord>> callback)throws IllegalArgumentException;
	
	void fetchLeavesRemainingForAllUsers(AsyncCallback<ArrayList<LeavesDTOForAllUsers>> callback)throws IllegalArgumentException;
	void fetchAllRoles(AsyncCallback<ArrayList<Roles>> callback);
	void deleteUser(int userId, AsyncCallback<String> callback);
	void fetchJobAttributes(AsyncCallback<JobAttributesDTO> callback);
	void saveJob(Job job, AsyncCallback<String> callback);
	void fetchJobs(User loggedInUser, AsyncCallback<ArrayList<Job>> callback);
	void updatePhase(Phases phase, AsyncCallback<String> callback);
	void deletePhase(Phases phase, AsyncCallback<String> callback);
	void fetchDomains(int lineofServiceId, AsyncCallback<ArrayList<Domains>> callback);
	void fetchSublineofServices(int domainId, AsyncCallback<ArrayList<SubLineofService>> callback);
	void deleteJobEmployee(int jobEmployeeId,AsyncCallback<String> callback);
	void deleteJob(int jobId, AsyncCallback<String> callback);
	void saveJobAttribute(JobAttributes jobAttributes, AsyncCallback<String> callback);
	void deleteJobAttribute(int jobAttributeId, AsyncCallback<String> callback);

	void saveTimeSheet(ArrayList<TimeSheet> timeSheetList, AsyncCallback<String> callback);
	
	void fetchTimeReport(int selectedJob, int selectedMonth,
			int selecteduser, int selectedJobType, AsyncCallback<String> asyncCallback);
	void getLineOfServices( AsyncCallback<ArrayList<LineofService>> asyncCallback);
}
