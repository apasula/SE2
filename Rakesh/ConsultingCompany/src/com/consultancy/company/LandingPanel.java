package com.consultancy.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LandingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LandingPanel() {

		init();
	}

	public void init() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(500, 200));

		JPanel topPnl = new JPanel();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton btnLeft = new JButton("Accountant");
		JButton btnRight = new JButton("Project Manager");
		JButton btnEmployee = new JButton("Employee");

		btnLeft.setPreferredSize(new Dimension(150, 40));
		btnRight.setPreferredSize(new Dimension(150, 40));
		btnEmployee.setPreferredSize(new Dimension(150, 40));
		
		btnLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("accountantlogin");

			}
		});
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("managerlogin");
		}
		});
		btnEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("employeelogin");
		}
		});

		btnPnl.add(btnLeft);
		btnPnl.add(btnRight);
		btnPnl.add(btnEmployee);

		mainPanel.add(topPnl, BorderLayout.NORTH);
		mainPanel.add(btnPnl, BorderLayout.SOUTH);
		

		JLabel label = new JLabel("Oklahama Christian University");
		label.setFont(new Font("Serif", Font.PLAIN, 24));

		topPnl.add(label);

		add(mainPanel, BorderLayout.CENTER);
		btnPnl.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);

		setBackground(Color.WHITE);

	}
}
