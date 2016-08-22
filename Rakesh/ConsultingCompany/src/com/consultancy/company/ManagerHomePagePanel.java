
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

public class ManagerHomePagePanel extends JPanel {

	public ManagerHomePagePanel() {

		init();
	}

	public void init() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(400, 500));

		JPanel topPnl = new JPanel();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnPnl.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

		JButton projectBtn = new JButton("Project");
		JButton assignProjectsBtn = new JButton("Assign Projects");
		JButton timeapprovalbtn = new JButton("Approve Hours");
		JButton timerecordbtn = new JButton("Enter working hours");
		final JButton logoutBtn = new JButton("Logout");

		projectBtn.setPreferredSize(new Dimension(150, 40));
		assignProjectsBtn.setPreferredSize(new Dimension(150, 40));
		timeapprovalbtn.setPreferredSize(new Dimension(150, 40));
		timerecordbtn.setPreferredSize(new Dimension(150, 40));

		logoutBtn.setPreferredSize(new Dimension(150, 40));

	
		projectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("manageProjectsPanel");

			}

		});

		assignProjectsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("assignProjectsPanel");

			}
		});

		timeapprovalbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("managerTimeApprovalPanel");

			}
		});
		
		timerecordbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("employeeHomePagePanel");

			}
		});
		

		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");

			}
		});

		btnPnl.add(projectBtn);
		btnPnl.add(assignProjectsBtn);
		btnPnl.add(timeapprovalbtn);
		btnPnl.add(timerecordbtn);
		btnPnl.add(logoutBtn);

		mainPanel.add(topPnl, BorderLayout.NORTH);
		mainPanel.add(btnPnl, BorderLayout.CENTER);

		JLabel label = new JLabel("Manager Home Page");
		label.setFont(new Font("Serif", Font.PLAIN, 24));

		topPnl.add(label);

		add(mainPanel, BorderLayout.CENTER);
		btnPnl.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);

		setBackground(Color.WHITE);
	}
}
