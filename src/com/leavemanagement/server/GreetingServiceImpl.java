package com.leavemanagement.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.leavemanagement.client.GreetingService;
import com.leavemanagement.database.MySQLRdbHelper;
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
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

HttpSession session ;
	
	@Override
	public String greetServer(String name) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		User user = (User) rdbHelper.getAuthentication("test", "test");

		if(user!=null)
		{
			session=getThreadLocalRequest().getSession(true);
			
			session.setAttribute("user", user);
		
		}
		return user.getName();
	}

	@Override
	public ArrayList<LeavesDTO> fetchLeavesRemaining(User userId)
			throws IllegalArgumentException, Exception {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchAvailableLeaves(userId);
	}

	@Override
	public User signIn(String userid, String password) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		User user = (User) rdbHelper.getAuthentication(userid, password);
		if(user!=null)
		{
			session=getThreadLocalRequest().getSession(true);
			
			session.setAttribute("user", user);
			session.setAttribute("companyId", user.getCompanyId().getCompanyId());
			
		
		}
		return user;
	}

	@Override
	public ArrayList<LeaveTypes> fetchLeaveTypes()
			throws IllegalArgumentException, Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchLeaveTypes();
	}

	@Override
	public String saveLeaveRequest(LeaveRecord leaveRecord) throws IllegalArgumentException, Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		session=getThreadLocalRequest().getSession(true);
		User user = (User) session.getAttribute("user");
		leaveRecord.setUserId(user);
		return rdbHelper.saveLeaveRequest(leaveRecord);
	}

	@Override
	public String fetchDatesDifference(Date from, Date to) throws Exception {
//		long diff = Math.abs(from.getTime() - to.getTime());
		long diff = getDiffInDates(from , to);
//		long diffDays = diff / (24 * 60 * 60 * 1000);
//		diffDays = diffDays+1;
		return diff+1+"";
	}
	
	public Long getDiffInDates(Date date1, Date date2)throws Exception{
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//	    Date date1 = df.parse("10/08/2013");
//	    Date date2 = df.parse("21/08/2013");
	    Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();
	    cal1.setTime(date1);
	    cal2.setTime(date2);

	    long numberOfDays = 0;
	    while (cal1.before(cal2)) {
	        if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
	           &&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
	            numberOfDays++;
	            cal1.add(Calendar.DATE,1);
	        }else {
	            cal1.add(Calendar.DATE,1);
	        }
	    }
	    System.out.println(numberOfDays);
		return numberOfDays;
	}


	@Override
	public ArrayList<LeaveRecord> fetchLeavesRecord(int userId) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		int companyId = (Integer) session.getAttribute("companyId");

		return rdbHelper.fetchLeaveRecord(userId, companyId);
		
	}

	@Override
	public String approveDeclineRequest(LeaveRecord leaveRecord) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.approveDeclineRequest(leaveRecord);
	}

	@Override
	public String fetchOldPassword(int userId) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchOldPassword(userId);
	}

	@Override
	public String updatePassword(User user) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.updatePassword(user);
	}

	@Override
	public String addUser(User user) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.addUser(user);
	}

	@Override
	public ArrayList<User> fetchAllUsers() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		int companyId = (Integer) session.getAttribute("companyId");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchAllUsers(companyId);
	}
	
	@Override
	public ArrayList<User> fetchAllUsersExceptDirector() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		int companyId = (Integer) session.getAttribute("companyId");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchAllUsersExceptDirector(companyId );
	}

	@Override
	public ArrayList<LeaveRecord> fetchPendingLeavesRecord()
			throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		int companyId = (Integer) session.getAttribute("companyId");
		User user = (User) session.getAttribute("user");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchPendingLeavesRecord(companyId, user.getUserId());
	}
	
	@Override
	public ArrayList<LeaveRecord> fetchPendingLeavesRecordOfLoggedInUser(int userId)
			throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		int companyId = (Integer) session.getAttribute("companyId");
		
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchPendingLeavesRecordOfLoggedInUser(userId, companyId);
	}

	@Override
	public ArrayList<LeavesDTOForAllUsers> fetchLeavesRemainingForAllUsers()
			throws IllegalArgumentException, Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		int companyId = (Integer) session.getAttribute("companyId");
		System.out.println(companyId+"");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		
		return rdbHelper.fetchLeavesRemainingForAllUsers(companyId);
	}

	@Override
	public ArrayList<Roles> fetchAllRoles() throws IllegalArgumentException,
			Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchAllRoles();
	}

	@Override
	public String addCompany(Company company, User user) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.addCompany(company, user);
	}

	@Override
	public String deleteUser(int userId) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.deleteUser(userId);
	}

	@Override
	public JobAttributesDTO fetchJobAttributes() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.getJobAttributes();
	}

	@Override
	public String saveJob(Job job) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.saveJob(job);
	}

	@Override
	public ArrayList<Job> fetchJobs(User loggedInUser) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchJobs(loggedInUser);
	}

	@Override
	public String updatePhase(Phases phase) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.updatePhase(phase);
	}

	@Override
	public String deletePhase(Phases phase) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.deletePhase(phase);
	}

	@Override
	public ArrayList<Domains> fetchDomains(int lineOfServiceId)
			throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchDomains(lineOfServiceId);
	}

	@Override
	public ArrayList<SubLineofService> fetchSublineofServices(int domainId)
			throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchSubLineOfServices(domainId);
	}

	@Override
	public String deleteJobEmployee(int jobEmployeeId) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.deleteJobEmployee(jobEmployeeId);
	}

	@Override
	public String deleteJob(int jobId) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.deleteJob(jobId);
	}

	@Override
	public String saveJobAttribute(JobAttributes jobAttribute) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.saveJobAttribute(jobAttribute);
	}

	@Override
	public String deleteJobAttribute(int jobAttributeId) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.deleteJobAttribute(jobAttributeId);
	}

	@Override
	public String saveTimeSheet(ArrayList<TimeSheet> timeSheet)  {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.saveTimeSheet(timeSheet);
	}

	@Override
	public String fetchTimeReport(int selectedJob, int selectedMonth,
			int selecteduser, int selectedJobType) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.fetchTimeReportforSelectedMonth(selectedJob,selectedMonth,
				 selecteduser, selectedJobType);
	}

	@Override
	public ArrayList<LineofService> getLineOfServices() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MySQLRdbHelper rdbHelper = (MySQLRdbHelper) ctx.getBean("ManagerExams");
		return rdbHelper.getLineOfServices();
	}
}