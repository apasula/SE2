package com.consultancy.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.Project;
import com.consultancy.company.service.Util;

public class AddProjectPanel extends JPanel{

	public AddProjectPanel() {
		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Add Project");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel projectnumberlabel = new JLabel("Enter Project Number:");
		JLabel clientnumberlabel = new JLabel("Enter Client Number:");
		JLabel projectnamelabel = new JLabel("Enter Project Name:");
		JLabel startdatelabel = new JLabel("State Date(mm/dd/yyyy):");
		JLabel enddatelabel = new JLabel("End Date(mm/dd/yyyy):");
		JLabel projectmanagerlabel = new JLabel("Project Manager:");
		JLabel clientcontactlabel = new JLabel("Client Contact:");
		JLabel budgetlabel = new JLabel("Budget:");
	
		final JTextField projectnumbertf = new JTextField();
		final JTextField clientnumbertf = new JTextField();
		final JTextField projectnametf = new JTextField();
		final JTextField startdatetf = new JTextField();
		final JTextField enddatetf = new JTextField();
		final JTextField projectmanagertf = new JTextField();
		final JTextField clientcontacttf = new JTextField();
		final JTextField budgettf = new JTextField();
		
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(500, 30, 400, 30);

		int topindex = 70;
		int topmargin = 40;

		int firstcolindex = 50;
		int secondcolindex = 600;
		int width = 300;

		projectnumberlabel.setBounds(firstcolindex, topindex, width, 30);
		clientnumberlabel.setBounds(secondcolindex, topindex, width, 30);
		projectnamelabel.setBounds(firstcolindex, topindex += topmargin,
				width, 30);
		startdatelabel.setBounds(secondcolindex, topindex, width, 30);
		enddatelabel.setBounds(firstcolindex, topindex += topmargin, width, 30);
		projectmanagerlabel.setBounds(secondcolindex, topindex, width, 30);
		clientcontactlabel.setBounds(firstcolindex, topindex += topmargin, width, 30);
		budgetlabel.setBounds(secondcolindex, topindex, width, 30);
	
		topindex = 70;
		topmargin = 40;

		firstcolindex += 150;
		secondcolindex += 150;

		projectnumbertf.setBounds(firstcolindex, topindex, width, 30);
		clientnumbertf.setBounds(secondcolindex, topindex, width, 30);
		projectnametf.setBounds(firstcolindex, topindex += topmargin, width,
				30);
		startdatetf.setBounds(secondcolindex, topindex, width, 30);
		enddatetf.setBounds(firstcolindex, topindex += topmargin, width, 30);
		projectmanagertf.setBounds(secondcolindex, topindex, width, 30);
		clientcontacttf.setBounds(firstcolindex, topindex += topmargin, width, 30);
		budgettf.setBounds(secondcolindex, topindex, width, 30);
	
		submitbtn.setBounds(500, topindex += topmargin + 20, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (projectnumbertf.getText().trim().equals("")
						|| clientnumbertf.getText().trim().equals("")
						|| projectnametf.getText().trim().equals("")
						|| startdatetf.getText().trim().equals("")
						|| enddatetf.getText().trim().equals("")
						|| projectmanagertf.getText().trim().equals("")
						|| clientcontacttf.getText().trim().equals("")
						|| budgettf.getText().trim().equals("")

				) {

					JOptionPane.showMessageDialog(AddProjectPanel.this,
							"Please enter all fields");

				}

				else if (!Util.isInteger(projectnumbertf.getText().trim())) {

					JOptionPane.showMessageDialog(AddProjectPanel.this,
							"Project Number must be integer");
				}
				
				else if (!Util.isInteger(clientnumbertf.getText().trim())) {

					JOptionPane.showMessageDialog(AddProjectPanel.this,
							"Client Number must be integer");
				}
				else if (!Util.isInteger(budgettf.getText().trim())) {

					JOptionPane.showMessageDialog(AddProjectPanel.this,
							"Budget must be integer");
				}
				
				else if (!Util.validDate(startdatetf.getText().trim())) {

					JOptionPane.showMessageDialog(AddProjectPanel.this,
							"Start date is not valid");
				}

				else if (!Util.validDate(enddatetf.getText().trim())) {

					JOptionPane.showMessageDialog(AddProjectPanel.this,
							"End date is not valid");
				}


				else {

					
					ProjectDB projectDB = new ProjectDB();
					if (projectDB.get(Integer.parseInt(projectnumbertf.getText()
							.trim())) != null) {

						JOptionPane.showMessageDialog(AddProjectPanel.this,
								"Project Number already exists");
						return;

					}

					
					ClientDB clientDB = new ClientDB();
					if (clientDB.get(Integer.parseInt(clientnumbertf.getText()
							.trim())) == null) {

						JOptionPane.showMessageDialog(AddProjectPanel.this,
								"Client Number does not exists");
						return;

					}

					else {
						Project project = new Project();
        				project.setPnumber(Integer.parseInt(projectnumbertf.getText()
								.trim()));
        				project.setClient(Integer.parseInt(clientnumbertf.getText()
								.trim()));
        				project.setName(projectnametf.getText().trim());
        				project.setStartdate(startdatetf.getText().trim());
        				project.setEnddate(enddatetf.getText().trim());
        				project.setStatus("In Progress");
        				project.setManager(projectmanagertf.getText().trim());
        				project.setContact(clientcontacttf.getText().trim());
        				project.setBudget(Integer.parseInt(budgettf.getText()
								.trim()));
        				
                 		projectDB.insert(project);
    					JOptionPane.showMessageDialog(AddProjectPanel.this,
								"Project Added");
    					projectnumbertf.setText("");
    					clientnumbertf.setText("");
    					projectnametf.setText("");
    					startdatetf.setText("");
    					enddatetf.setText("");
    					projectmanagertf.setText("");
    					clientcontacttf.setText("");
    					budgettf.setText("");
    					

    					
				
					}
				}

			}
		});

		add(hint);
		add(projectnumberlabel);
		add(projectnumbertf);

		add(clientnumberlabel);
		add(clientnumbertf);

		add(projectnamelabel);
		add(projectnametf);

		add(startdatelabel);
		add(startdatetf);

		add(enddatelabel);
		add(enddatetf);

		add(projectmanagerlabel);
		add(projectmanagertf);

		add(clientcontactlabel);
		add(clientcontacttf);

		add(budgetlabel);
		add(budgettf);

		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
