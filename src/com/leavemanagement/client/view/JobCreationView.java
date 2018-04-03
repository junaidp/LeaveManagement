package com.leavemanagement.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.leavemanagement.client.GreetingService;
import com.leavemanagement.client.GreetingServiceAsync;
import com.leavemanagement.shared.Countries;
import com.leavemanagement.shared.Domains;
import com.leavemanagement.shared.Job;
import com.leavemanagement.shared.JobAttributesDTO;
import com.leavemanagement.shared.JobEmployees;
import com.leavemanagement.shared.LineofService;
import com.leavemanagement.shared.Phases;
import com.leavemanagement.shared.SubLineofService;
import com.leavemanagement.shared.User;

public class JobCreationView extends VerticalPanel {

    private ListBox listLineOfService = new ListBox();
    private ListBox listDomain = new ListBox();
    private ListBox listSubLineofService = new ListBox();
    private ListBox listLocation = new ListBox();
    private TextBox txtJobName = new TextBox();
    private ListBox listCountry = new ListBox();
    private ListBox listSupervisor = new ListBox();
    private ListBox listPrincipalConsultant = new ListBox();
    private TextBox txtClient = new TextBox();
    private TextBox txtClientFee = new TextBox();
    private Button btnSubmit = new Button("Submit/Update");
    private Button btnPhase = new Button("Add Phase");
    private TextBox textSupervisorHours = new TextBox();
    private TextBox textPrinicialConsultantHours = new TextBox();
    private ArrayList<Phases> phases = new ArrayList<Phases>();
    JobsListView jobsListView;
    // private ListBox listEmployee1 = new ListBox();
    private Job selectedJob;
    GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
    private Label lblCountryName = new Label("Country Name");
    private VerticalPanel vpnlEmployees = new VerticalPanel();
    private ArrayList<User> employeesList;
    private CostWidget costWidget = new CostWidget();

    public JobCreationView(Job job, User loggedInUser) {
	jobsListView = new JobsListView(loggedInUser);
	this.selectedJob = job;
	listLocation.addItem("Local");
	listLocation.addItem("Overseas");
	FlexTable flex = new FlexTable();
	Label lblLineOfService = new Label("Line of Service");
	Label lblDomain = new Label("Domain");
	Label lblClientFee = new Label("Client Fee");
	Label lblSublineOfService = new Label("Subline of Service");
	Label lblJobName = new Label("Job Name");
	Label lblLocation = new Label("Location");
	Label lblSupervisors = new Label("Supervisor");
	Label lblPrinicipalConsultant = new Label("Principal consultant");

	Label lblClientName = new Label("Client Name");
	Label lblEmployee = new Label("Assign to");
	Image btnAddEmployee = new Image("add.png");
	btnAddEmployee.addStyleName("hover");

	btnAddEmployee.setTitle("Add Employee");
	flex.setWidget(1, 0, lblLineOfService);
	flex.setWidget(1, 1, listLineOfService);
	flex.setWidget(2, 0, lblDomain);
	flex.setWidget(2, 1, listDomain);
	flex.setWidget(3, 0, lblSublineOfService);
	flex.setWidget(3, 1, listSubLineofService);
	flex.setWidget(4, 0, lblJobName);
	flex.setWidget(4, 1, txtJobName);
	flex.setWidget(5, 0, lblLocation);
	flex.setWidget(5, 1, listLocation);
	flex.setWidget(6, 0, lblCountryName);
	flex.setWidget(6, 1, listCountry);
	flex.setWidget(7, 0, lblClientName);
	flex.setWidget(7, 1, txtClient);
	flex.setWidget(7, 2, btnPhase);
	flex.setWidget(8, 0, lblClientFee);
	flex.setWidget(8, 1, txtClientFee);
	// flex.setWidget(9, 0, lblEmployee);
	// flex.setWidget(9, 1, listEmployee1);
	flex.setWidget(8, 2, btnAddEmployee);
	flex.setWidget(10, 1, vpnlEmployees);

	btnAddEmployee.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {

		final AssignedToWidget assignedToWidget = new AssignedToWidget(employeesList);
		vpnlEmployees.add(assignedToWidget);
		assignedToWidget.getBtnRemove().addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			vpnlEmployees.remove(assignedToWidget);

		    }
		});

	    }
	});
	/// end

	flex.setWidget(11, 0, lblSupervisors);
	flex.setWidget(11, 1, listSupervisor);
	flex.setWidget(11, 2, new Label("Hours: "));
	flex.setWidget(11, 3, textSupervisorHours);
	flex.setWidget(12, 0, lblPrinicipalConsultant);
	flex.setWidget(12, 1, listPrincipalConsultant);
	flex.setWidget(12, 2, new Label("Hours: "));
	flex.setWidget(12, 3, textPrinicialConsultantHours);

	flex.setWidget(13, 1, btnSubmit);
	textPrinicialConsultantHours.setWidth("30px");
	textSupervisorHours.setWidth("30px");
	lblLineOfService.setWidth("200px");
	lblDomain.setWidth("200px");
	lblSublineOfService.setWidth("200px");
	lblJobName.setWidth("200px");
	lblLocation.setWidth("200px");
	lblCountryName.setWidth("200px");
	lblClientName.setWidth("200px");
	lblSupervisors.setWidth("200px");
	lblPrinicipalConsultant.setWidth("200px");
	listLineOfService.setWidth("200px");
	lblEmployee.setWidth("200px");
	listDomain.setWidth("200px");
	listSubLineofService.setWidth("200px");
	listSupervisor.setWidth("200px");
	listPrincipalConsultant.setWidth("200px");
	txtJobName.setWidth("200px");
	listLocation.setWidth("200px");
	listCountry.setWidth("200px");
	txtClient.setWidth("200px");
	txtClientFee.setWidth("200px");

	lblCountryName.setVisible(false);
	listCountry.setVisible(false);

	HorizontalPanel hpnl = new HorizontalPanel();
	hpnl.add(flex);

	hpnl.add(costWidget);
	if (loggedInUser.getRoleId().getRoleId() == 5) {
	    add(hpnl);
	    fetchEmployees();
	}
	add(jobsListView);

	setSpacing(5);

	costWidget.getImgRefresh().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		float totalCost = 0;
		for (int i = 0; i < vpnlEmployees.getWidgetCount(); i++) {
		    AssignedToWidget assignedToWidget = (AssignedToWidget) vpnlEmployees.getWidget(i);
		    for (int j = 0; j < employeesList.size(); j++) {
			if (employeesList.get(j).getUserId() == Integer.parseInt(assignedToWidget.getListAssign()
				.getValue(assignedToWidget.getListAssign().getSelectedIndex()))) {
			    float employeeCost = employeesList.get(j).getRoleId().getChargeRate()
				    * Integer.parseInt(assignedToWidget.getTxtDays().getText());
			    totalCost = totalCost + employeeCost;
			    break;
			}

		    }
		}
		try {
		    float supervisorChargeRate = 0;
		    for (int j = 0; j < employeesList.size(); j++) {
			if (employeesList.get(j).getUserId() == Integer
				.parseInt(listSupervisor.getValue(listSupervisor.getSelectedIndex()))) {
			    supervisorChargeRate = employeesList.get(j).getRoleId().getChargeRate();
			}
		    }
		    float SupervisorCost = Integer.parseInt(textSupervisorHours.getText()) * supervisorChargeRate;
		    totalCost = totalCost + SupervisorCost;
		    totalCost = totalCost + Integer.parseInt(textPrinicialConsultantHours.getText()) * 7500;
		} catch (Exception ex) {
		    Window.alert("Enter valid numeric value in Supervisor & princiap Consultant Hours Field");
		    return;
		}
		costWidget.getCost().setText("Total Cost: " + totalCost);
	    }
	});

	listLocation.addChangeHandler(new ChangeHandler() {

	    @Override
	    public void onChange(ChangeEvent event) {
		if (listLocation.getSelectedIndex() == 1) {
		    lblCountryName.setVisible(true);
		    listCountry.setVisible(true);
		} else {
		    lblCountryName.setVisible(false);
		    listCountry.setVisible(false);
		}
	    }
	});

	listLineOfService.addChangeHandler(new ChangeHandler() {

	    @Override
	    public void onChange(ChangeEvent event) {
		fetchDomains();
	    }
	});

	listDomain.addChangeHandler(new ChangeHandler() {

	    @Override
	    public void onChange(ChangeEvent event) {
		fetchSubLineofServices();
	    }
	});

	btnSubmit.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		if (selectedJob == null && phases.size() < 1) {
		    Window.alert("Please add at least 1 phase for this job");
		} else {
		    Job job = null;
		    if (selectedJob == null) {
			job = new Job();
		    } else {
			job = selectedJob;
		    }
		    job.setClient(txtClient.getText());
		    Domains domain = new Domains();
		    domain.setDomainId(Integer.parseInt(listDomain.getValue(listDomain.getSelectedIndex())));
		    LineofService lineofService = new LineofService();
		    lineofService.setLineofServiceId(
			    Integer.parseInt(listLineOfService.getValue(listLineOfService.getSelectedIndex())));
		    SubLineofService subLineofService = new SubLineofService();
		    subLineofService.setSubLineofServiceId(
			    Integer.parseInt(listSubLineofService.getValue(listSubLineofService.getSelectedIndex())));
		    Countries country = new Countries();
		    country.setCountryId(Integer.parseInt(listCountry.getValue(listCountry.getSelectedIndex())));
		    job.setDomainId(domain);
		    job.setCountryId(country);
		    job.setLineofServiceId(lineofService);
		    job.setSubLineofServiceId(subLineofService);
		    job.setJobName(txtJobName.getText());
		    job.setJobPhases(phases);
		    job.setClient(txtClient.getText());
		    job.setSupervisorHours(Integer.parseInt(textSupervisorHours.getText()));
		    job.setPrincipalConsultantHours(Integer.parseInt(textPrinicialConsultantHours.getText()));

		    try {
			job.setClientFee(Integer.parseInt(txtClientFee.getText()));
		    } catch (Exception ex) {
			Window.alert("Please enter valid numeric value in Client Fee");
			return;
		    }
		    User user = new User();
		    // user.setUserId(Integer.parseInt(listEmployee1.getValue(listEmployee1.getSelectedIndex())));
		    // job.setUserId(user);

		    User supervisor = new User();
		    supervisor.setUserId(Integer.parseInt(listSupervisor.getValue(listSupervisor.getSelectedIndex())));
		    job.setSupervisorId(supervisor);

		    User principalConsultant = new User();
		    principalConsultant.setUserId(Integer
			    .parseInt(listPrincipalConsultant.getValue(listPrincipalConsultant.getSelectedIndex())));
		    job.setPrincipalConsultantId(principalConsultant);

		    ArrayList<JobEmployees> JobEmployeesList = new ArrayList<JobEmployees>();

		    for (int i = 0; i < vpnlEmployees.getWidgetCount(); i++) {
			AssignedToWidget assignedToWidget = (AssignedToWidget) vpnlEmployees.getWidget(i);
			JobEmployees jobEmployees = new JobEmployees();
			User employee = new User();
			employee.setUserId(Integer.parseInt(assignedToWidget.getListAssign()
				.getValue(assignedToWidget.getListAssign().getSelectedIndex())));
			employee.setName(assignedToWidget.getListAssign()
				.getItemText(assignedToWidget.getListAssign().getSelectedIndex()));
			jobEmployees.setEmployeeId(employee);

			jobEmployees.setJobEmployeeId(assignedToWidget.getJobEmployeeId());
			try {
			    jobEmployees.setNoOfDays(Integer.parseInt(assignedToWidget.getTxtDays().getText()));
			} catch (Exception ex) {
			    Window.alert("Please enter numeric value for No of Days");
			    return;
			}
			JobEmployeesList.add(jobEmployees);
			if (JobEmployeesList.size() < 1) {
			    Window.alert("Please Assign the job to atleast 1 employee");
			    return;
			}
			job.setJobEmployeesList(JobEmployeesList);
		    }

		    rpcService.saveJob(job, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
			    Window.alert("Job created");
			    jobsListView.fetchJobs();
			}

			@Override
			public void onFailure(Throwable caught) {
			    Window.alert("Fail saveJob" + caught.getLocalizedMessage());
			}
		    });
		}
	    }
	});

	btnPhase.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		final JobPhaseView jobPhaseView = new JobPhaseView();
		final PopupsView popup = new PopupsView(jobPhaseView);
		jobPhaseView.getBtnCancel().addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			popup.getPopup().removeFromParent();
		    }
		});

		jobPhaseView.getBtnSubmit().addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			Phases phase = new Phases();
			phase.setPhaseName(jobPhaseView.getTxtPhase().getText());
			phase.setStartDate(jobPhaseView.getStartDate().getValue());
			phase.setDeliveryDate(jobPhaseView.getDeliveryDate().getValue());
			phase.setSubmissionDate(jobPhaseView.getSubmissionDate().getValue());
			phases.add(phase);
			popup.getPopup().removeFromParent();
		    }
		});

	    }
	});
    }

    private void fetchDomains() {
	int lineofServiceId = Integer.parseInt(listLineOfService.getValue(listLineOfService.getSelectedIndex()));
	rpcService.fetchDomains(lineofServiceId, new AsyncCallback<ArrayList<Domains>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail fetch domain");
	    }

	    @Override
	    public void onSuccess(ArrayList<Domains> result) {
		listDomain.clear();
		for (int i = 0; i < result.size(); i++) {
		    listDomain.addItem(result.get(i).getName(), result.get(i).getDomainId() + "");
		}
		fetchSubLineofServices();
	    }
	});
    }

    private void fetchSubLineofServices() {
	int domainId = Integer.parseInt(listDomain.getValue(listDomain.getSelectedIndex()));

	rpcService.fetchSublineofServices(domainId, new AsyncCallback<ArrayList<SubLineofService>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void onSuccess(ArrayList<SubLineofService> result) {
		listSubLineofService.clear();
		for (int i = 0; i < result.size(); i++) {
		    listSubLineofService.addItem(result.get(i).getName(), result.get(i).getSubLineofServiceId() + "");
		}
	    }
	});
    }

    private void fetchEmployees() {
	rpcService.fetchAllUsers(new AsyncCallback<ArrayList<User>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail fetch users");
	    }

	    @Override
	    public void onSuccess(ArrayList<User> result) {
		employeesList = result;

		///////// Add Employee
		final AssignedToWidget assignedToWidget = new AssignedToWidget(employeesList);
		vpnlEmployees.add(assignedToWidget);
		assignedToWidget.getBtnRemove().addClickHandler(new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			vpnlEmployees.remove(assignedToWidget);

		    }
		});

		for (int i = 0; i < result.size(); i++) {
		    // listEmployee1.addItem(result.get(i).getName(),
		    // result.get(i).getUserId()+"");
		    listSupervisor.addItem(result.get(i).getName(), result.get(i).getUserId() + "");
		    if (result.get(i).getRoleId().getRoleId() == 5) {
			listPrincipalConsultant.addItem(result.get(i).getName(), result.get(i).getUserId() + "");

		    }
		}
	    }
	});

    }

    public void setJobAttributes(JobAttributesDTO jobAttributesDTO) {
	ArrayList<LineofService> lineofServices = new ArrayList<LineofService>();
	ArrayList<SubLineofService> sublineofServices = new ArrayList<SubLineofService>();
	ArrayList<Domains> domains = new ArrayList<Domains>();
	ArrayList<Countries> countries = new ArrayList<Countries>();

	countries = jobAttributesDTO.getCountries();
	lineofServices = jobAttributesDTO.getLineofService();
	sublineofServices = jobAttributesDTO.getSubLineofService();
	domains = jobAttributesDTO.getDomains();
	countries = jobAttributesDTO.getCountries();
	listCountry.clear();
	listLineOfService.clear();
	listSubLineofService.clear();
	listDomain.clear();

	for (int i = 0; i < countries.size(); i++) {
	    listCountry.addItem(countries.get(i).getName(), countries.get(i).getCountryId() + "");
	}
	for (int i = 0; i < lineofServices.size(); i++) {
	    listLineOfService.addItem(lineofServices.get(i).getName(), lineofServices.get(i).getLineofServiceId() + "");
	}
	for (int i = 0; i < sublineofServices.size(); i++) {
	    listSubLineofService.addItem(sublineofServices.get(i).getName(),
		    sublineofServices.get(i).getSubLineofServiceId() + "");
	}
	for (int i = 0; i < domains.size(); i++) {
	    listDomain.addItem(domains.get(i).getName(), domains.get(i).getDomainId() + "");
	}

    }

    public Button getBtnSubmit() {
	return btnSubmit;
    }

    public void setBtnSubmit(Button btnSubmit) {
	this.btnSubmit = btnSubmit;
    }

    public ListBox getListLineOfService() {
	return listLineOfService;
    }

    public void setListLineOfService(ListBox listLineOfService) {
	this.listLineOfService = listLineOfService;
    }

    public ListBox getListDomain() {
	return listDomain;
    }

    public void setListDomain(ListBox listDomain) {
	this.listDomain = listDomain;
    }

    public ListBox getListSubLineofService() {
	return listSubLineofService;
    }

    public void setListSubLineofService(ListBox listSubLineofService) {
	this.listSubLineofService = listSubLineofService;
    }

    public ListBox getListLocation() {
	return listLocation;
    }

    public void setListLocation(ListBox listLocation) {
	this.listLocation = listLocation;
    }

    public TextBox getTxtJobName() {
	return txtJobName;
    }

    public void setTxtJobName(TextBox txtJobName) {
	this.txtJobName = txtJobName;
    }

    public ListBox getListCountry() {
	return listCountry;
    }

    public void setListCountry(ListBox listCountry) {
	this.listCountry = listCountry;
    }

    public TextBox getTxtClient() {
	return txtClient;
    }

    public void setTxtClient(TextBox txtClient) {
	this.txtClient = txtClient;
    }

    public ArrayList<Phases> getPhases() {
	return phases;
    }

    public void setPhases(ArrayList<Phases> phases) {
	this.phases = phases;
    }

    public JobsListView getJobsListView() {
	return jobsListView;
    }

    public void setJobsListView(JobsListView jobsListView) {
	this.jobsListView = jobsListView;
    }

    public Job getSelectedJob() {
	return selectedJob;
    }

    public void setSelectedJob(Job selectedJob) {
	this.selectedJob = selectedJob;
    }

    public ListBox getListSupervisor() {
	return listSupervisor;
    }

    public void setListSupervisor(ListBox listSupervisor) {
	this.listSupervisor = listSupervisor;
    }

    public ListBox getListPrincipalConsultant() {
	return listPrincipalConsultant;
    }

    public void setListPrincipalConsultant(ListBox listPrincipalConsultant) {
	this.listPrincipalConsultant = listPrincipalConsultant;
    }

    public VerticalPanel getVpnlEmployees() {
	return vpnlEmployees;
    }

    public void setVpnlEmployees(VerticalPanel vpnlEmployees) {
	this.vpnlEmployees = vpnlEmployees;
    }

    public TextBox getTxtClientFee() {
	return txtClientFee;
    }

    public void setTxtClientFee(TextBox txtClientFee) {
	this.txtClientFee = txtClientFee;
    }

}
