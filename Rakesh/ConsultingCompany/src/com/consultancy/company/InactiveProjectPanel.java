package com.consultancy.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Project;
import com.consultancy.company.service.Util;

public class InactiveProjectPanel extends JPanel{

	public InactiveProjectPanel(){
		init();
	}
	
	public void init(){
		setLayout(null);

		JLabel hint = new JLabel("Inactivate project");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel projectidlabel = new JLabel("Enter project number:");
		final JTextField projectidtf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(500, 30, 400, 30);
		
		projectidlabel.setBounds(230, 80, 300, 30);
		projectidtf.setBounds(360, 80, 300, 30);
		submitbtn.setBounds(700, 80, 100, 30);


		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (projectidtf.getText().trim().equals("")
						|| projectidtf.getText().trim().equals("")) {

	            	JOptionPane.showMessageDialog(InactiveProjectPanel.this, "Please enter project id");

				}
				
				else if (!Util.isInteger(projectidtf.getText().trim())) {

					JOptionPane.showMessageDialog(InactiveProjectPanel.this,
							"project Number must be integer");
				}
				else {
					ProjectDB projectDB = new ProjectDB();
					Project project = new Project();
					project = projectDB.get(Integer.parseInt(projectidtf.getText().trim()));
					project.setStatus("Closed");
					projectDB.update(project);
	            	JOptionPane.showMessageDialog(InactiveProjectPanel.this, "project Inactivated");
	          	
				}
				

			}
		});

		add(hint);
		add(projectidlabel);
		add(projectidtf);
		add(submitbtn);

		setBackground(Color.WHITE);


	}
}

