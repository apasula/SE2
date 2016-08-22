package com.consultancy.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.consultancy.company.service.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class ManageProjectsPanel extends Panel {

	private JTabbedPane tabbedPane;

	public ManageProjectsPanel() {

		init();
	}

	public void init() {
		JPanel rootPanel = new JPanel();
		rootPanel.setBackground(Color.WHITE);

		rootPanel.setPreferredSize(new Dimension(1080, 600));

		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setBackground(Color.WHITE);

		BoxLayout hbox = new BoxLayout(horizontalPanel, BoxLayout.X_AXIS);
		horizontalPanel.setLayout(hbox);

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (Constants.loggedinUser == 0)
					MainFrame.showCard("accountanthomepage");
				else if (Constants.loggedinUser == 1)
					MainFrame.showCard("managerhomepage");

			}
		});

		horizontalPanel.add(backBtn);
		horizontalPanel.add(Box.createRigidArea(new Dimension(900, 0)));

		rootPanel.add(horizontalPanel);

		JButton logout = new JButton("Logout");

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});
		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalPanel.add(logout);

		setBackground(Color.WHITE);

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Add Project", new AddProjectPanel());
		tabbedPane.addTab("Update Project", new UpdateProjectPanel());
		tabbedPane.addTab("Inactivate Project", new InactiveProjectPanel());
		tabbedPane.addTab("Activate Project", new ActivateProjectPanel());

		rootPanel.add(tabbedPane, BorderLayout.CENTER);

	}
}