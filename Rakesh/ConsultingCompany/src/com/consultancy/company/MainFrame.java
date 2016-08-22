package com.consultancy.company;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame {

	public static void main(String[] args) {

		for (LookAndFeelInfo lookandfeel : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(lookandfeel.getName())) {
				try {
					UIManager.setLookAndFeel(lookandfeel.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		MainFrame main = new MainFrame();
		main.init();
	}

	static AssignProjectsPanel assignProjectsPanel;
	static EmployeeHomePagePanel employeeHomePagePanel;
	static ManagerTimeApprovalPanel managerTimeApprovalPanel;
	static HoursDetailsPanel hoursDetailsPanel;
	static GenerateInvoicePanel generateInvoicePanel;
	static InvoiceReportPanel invoiceReportPanel;
	
	public void addPanelstoCard(JPanel cards) {

		LandingPanel landingPanel = new LandingPanel();
		AccountantLoginPanel accountantlogin = new AccountantLoginPanel();
		EmployeeLoginPanel employeelogin = new EmployeeLoginPanel();
		ManagerLoginPanel managerlogin = new ManagerLoginPanel();
		ImportDataPanel importDataPanel = new ImportDataPanel();
		AccountantHomePagePanel accountanthomepage = new AccountantHomePagePanel();
		LogoutPanel logoutPanel = new LogoutPanel();
		ManageClientsPanel manageClientsPanel = new ManageClientsPanel();
		ManageProjectsPanel manageProjectsPanel = new ManageProjectsPanel();
		ManageEmployeesPanel manageEmployeesPanel = new ManageEmployeesPanel();
		ManagerHomePagePanel managerHomePagePanel = new ManagerHomePagePanel();
		 generateInvoicePanel = new GenerateInvoicePanel();
		PayrollReportPanel payrollReportPanel = new PayrollReportPanel();
		ProjectReportPanel projectReportPanel = new ProjectReportPanel();
		invoiceReportPanel = new InvoiceReportPanel();
		
		
		employeeHomePagePanel = new EmployeeHomePagePanel();
		managerTimeApprovalPanel = new ManagerTimeApprovalPanel();
		hoursDetailsPanel = new HoursDetailsPanel();

		assignProjectsPanel = new AssignProjectsPanel();

		cards.add(logoutPanel, "logoutpanel");
		cards.add(landingPanel, "landingpanel");
		cards.add(accountantlogin, "accountantlogin");
		cards.add(employeelogin, "employeelogin");
		cards.add(managerlogin, "managerlogin");
		cards.add(accountanthomepage, "accountanthomepage");
		cards.add(importDataPanel, "importDataPanel");
		cards.add(manageClientsPanel, "manageClientsPanel");
		cards.add(manageProjectsPanel, "manageProjectsPanel");
		cards.add(manageEmployeesPanel, "manageEmployeesPanel");
		cards.add(managerHomePagePanel, "managerhomepage");
		cards.add(assignProjectsPanel, "assignProjectsPanel");
		cards.add(employeeHomePagePanel, "employeeHomePagePanel");
		cards.add(managerTimeApprovalPanel, "managerTimeApprovalPanel");
		cards.add(generateInvoicePanel, "generateInvoicePanel");
		cards.add(hoursDetailsPanel, "hoursDetailsPanel");
		cards.add(payrollReportPanel, "payrollReportPanel");
		cards.add(projectReportPanel, "projectReportPanel");
		cards.add(invoiceReportPanel, "invoiceReportPanel");


	}

	public static void showCard(String panelname) {
		
		if(panelname.equals("accountantlogin")){
		
			employeedetails.setText("");
		}
		
		CardLayout cl = (CardLayout) (cards.getLayout());

		if (panelname.equals("assignProjectsPanel")) {
			cards.remove(assignProjectsPanel);
			assignProjectsPanel = new AssignProjectsPanel();
			cards.add(assignProjectsPanel, "assignProjectsPanel");

		}
		
		if (panelname.equals("employeeHomePagePanel")) {
			cards.remove(employeeHomePagePanel);
			employeeHomePagePanel = new EmployeeHomePagePanel();
			cards.add(employeeHomePagePanel, "employeeHomePagePanel");
		}
		
		if (panelname.equals("managerTimeApprovalPanel")) {
			cards.remove(managerTimeApprovalPanel);
			managerTimeApprovalPanel = new ManagerTimeApprovalPanel();
			managerTimeApprovalPanel.init();
			cards.add(managerTimeApprovalPanel, "managerTimeApprovalPanel");
		}
		if (panelname.equals("hoursDetailsPanel")) {
			cards.remove(hoursDetailsPanel);
			hoursDetailsPanel = new HoursDetailsPanel();
			hoursDetailsPanel.init();
			cards.add(hoursDetailsPanel, "hoursDetailsPanel");
		}
		
		if (panelname.equals("generateInvoicePanel")) {
			cards.remove(generateInvoicePanel);
			generateInvoicePanel = new GenerateInvoicePanel();
			generateInvoicePanel.init();
			cards.add(generateInvoicePanel, "generateInvoicePanel");
		}
		
		if (panelname.equals("invoiceReportPanel")) {
			cards.remove(invoiceReportPanel);
			invoiceReportPanel = new InvoiceReportPanel();
			invoiceReportPanel.init();
			cards.add(invoiceReportPanel, "invoiceReportPanel");
		}
		
		cl.show(cards, panelname);

	}

	public static JPanel cards;
	public static JLabel employeedetails;
	
	public void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 600);
		setLocation(50, 50);
		setTitle("Eagle Consulting Company");

		JPanel rootPanel = new JPanel();

		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);

		employeedetails = new JLabel();
		employeedetails.setAlignmentX(LEFT_ALIGNMENT);

		employeedetails.setText("");
		employeedetails.setForeground(Color.blue);
		employeedetails.setFont(new Font("Serif", Font.BOLD, 20));
		
		cards = new JPanel(new CardLayout());

		addPanelstoCard(cards);
		rootPanel.add(employeedetails);

		rootPanel.add(cards);
		
		add(rootPanel);
		

		showCard("accountantlogin");
		getContentPane().setBackground(Color.WHITE);

		setResizable(false);
		setVisible(true);
	}
}
