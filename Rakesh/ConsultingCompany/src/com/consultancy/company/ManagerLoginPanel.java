package com.consultancy.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.consultancy.company.service.Constants;
import com.consultancy.company.service.UserLogin;

public class ManagerLoginPanel extends JPanel {

	public ManagerLoginPanel() {

		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Project Manager Login");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel usernamelabel = new JLabel("Enter Username:");
		JLabel passwordlabel = new JLabel("Enter Password:");
		final JTextField usernametf = new JTextField();
		final JPasswordField passwordtf = new JPasswordField();
		JButton submitbtn = new JButton("Submit");
		JButton backbtn = new JButton("Cancel");

		hint.setBounds(450, 30, 400, 30);
		usernamelabel.setBounds(350, 70, 200, 30);
		passwordlabel.setBounds(350, 110, 200, 30);
		usernametf.setBounds(450, 70, 200, 30);
		passwordtf.setBounds(450, 110, 200, 30);
		submitbtn.setBounds(450, 160, 100, 30);
		backbtn.setBounds(550, 160, 100, 30);
		
	

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				UserLogin login = new UserLogin();
				boolean result = login.managerLogin(usernametf.getText()
						.toString().trim(), passwordtf.getText().toString()
						.trim());

				if (result) {

					Constants.loggedinUser =1;
					MainFrame.showCard("managerhomepage");
				} else {
					JOptionPane.showMessageDialog(ManagerLoginPanel.this,
							"Invalid Credentials");
				}
			}
		});
		
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("landingpanel");

			}
		});

		add(hint);
		add(usernamelabel);
		add(usernametf);
		add(passwordlabel);
		add(passwordtf);
		add(submitbtn);
		add(backbtn);

		setBackground(Color.WHITE);

	}
}
