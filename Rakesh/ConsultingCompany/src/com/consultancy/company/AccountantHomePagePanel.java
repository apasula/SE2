package com.consultancy.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountantHomePagePanel extends JPanel {

	public AccountantHomePagePanel() {

		init();
	}

	public void init() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(400, 500));

		JPanel topPnl = new JPanel();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnPnl.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

		JButton clientBtn = new JButton("Client");
		JButton projectBtn = new JButton("Project");
		JButton employeeBtn = new JButton("Employee");
		JButton importDataBtn = new JButton("Import Data");
		final JButton generateInvoiceBtn = new JButton("Generate Invoice");
		final JButton employeehoursbtn = new JButton("Employee Hours");
		final JButton payrollreportbtn = new JButton("Payroll Report");
		final JButton projectreportbtn = new JButton("Project Report");
		final JButton invoicereportbtn = new JButton("Invoice Report");

		final JButton logoutBtn = new JButton("Logout");

		clientBtn.setPreferredSize(new Dimension(150, 40));
		projectBtn.setPreferredSize(new Dimension(150, 40));
		employeeBtn.setPreferredSize(new Dimension(150, 40));
		importDataBtn.setPreferredSize(new Dimension(150, 40));
		generateInvoiceBtn.setPreferredSize(new Dimension(150, 40));
		logoutBtn.setPreferredSize(new Dimension(150, 40));
		employeehoursbtn.setPreferredSize(new Dimension(150, 40));
		payrollreportbtn.setPreferredSize(new Dimension(150, 40));
		projectreportbtn.setPreferredSize(new Dimension(150, 40));
		invoicereportbtn.setPreferredSize(new Dimension(150, 40));

		clientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("manageClientsPanel");
			}
		});

		projectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("manageProjectsPanel");

			}

		});

		employeeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("manageEmployeesPanel");

			}
		});

		importDataBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("importDataPanel");

			}
		});

		generateInvoiceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("generateInvoicePanel");

			}
		});

		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");

			}
		});
		
		employeehoursbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("hoursDetailsPanel");

			}
		});
		
		payrollreportbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("payrollReportPanel");

			}
		});
		
		projectreportbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("projectReportPanel");

			}
		});
		
		invoicereportbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("invoiceReportPanel");

			}
		});

		btnPnl.add(clientBtn);
		btnPnl.add(projectBtn);
		btnPnl.add(employeeBtn);
		btnPnl.add(importDataBtn);
		btnPnl.add(generateInvoiceBtn);
		btnPnl.add(employeehoursbtn);
		btnPnl.add(payrollreportbtn);
		btnPnl.add(projectreportbtn);
		btnPnl.add(invoicereportbtn);
		btnPnl.add(logoutBtn);

		mainPanel.add(topPnl, BorderLayout.NORTH);
		mainPanel.add(btnPnl, BorderLayout.CENTER);

		JLabel label = new JLabel("Accountant Home Page");
		label.setFont(new Font("Serif", Font.PLAIN, 24));

		topPnl.add(label);

		add(mainPanel, BorderLayout.CENTER);
		btnPnl.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);

		setBackground(Color.WHITE);
	}
}
