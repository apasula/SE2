package com.consultancy.company;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoutPanel extends JPanel {

	public LogoutPanel() {

		init();
	}

	public void init() {
		setLayout(null);
		JLabel hint = new JLabel(
				"You have successfully logout .Click the below button to login");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 18));
		setBackground(Color.WHITE);
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("accountantlogin");
				
			}
		});
		hint.setBounds(150, 100, 600, 30);
		loginBtn.setBounds(350, 160, 100, 30);

		add(hint);
		add(loginBtn);

	}
}
