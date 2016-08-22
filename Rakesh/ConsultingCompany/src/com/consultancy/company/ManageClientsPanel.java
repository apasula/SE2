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
public class ManageClientsPanel extends Panel {

	private JTabbedPane tabbedPane;

	public ManageClientsPanel() {

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

				MainFrame.showCard("accountanthomepage");
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
		tabbedPane.addTab("Add Client", new AddClientPanel());
		tabbedPane.addTab("Update Client", new UpdateClientPanel());
		tabbedPane.addTab("Inactivate Client", new InactiveClientPanel());
		tabbedPane.addTab("Activate Client", new ActivateClientPanel());

		rootPanel.add(tabbedPane, BorderLayout.CENTER);

	}
}

