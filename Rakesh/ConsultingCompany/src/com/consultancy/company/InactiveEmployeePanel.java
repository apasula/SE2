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

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.People;
import com.consultancy.company.service.Util;

public class InactiveEmployeePanel extends JPanel{


	public InactiveEmployeePanel(){
		init();
	}
	
	public void init(){
		setLayout(null);

		JLabel hint = new JLabel("Inactivate Person");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel personidlabel = new JLabel("Enter Person Id:");
		final JTextField personidtf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(500, 30, 400, 30);
		
		personidlabel.setBounds(230, 80, 300, 30);
		personidtf.setBounds(360, 80, 300, 30);
		submitbtn.setBounds(700, 80, 100, 30);


		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (personidtf.getText().trim().equals("")
						|| personidtf.getText().trim().equals("")) {

	            	JOptionPane.showMessageDialog(InactiveEmployeePanel.this, "Please enter person id");

				}
				
				else if (!Util.isInteger(personidtf.getText().trim())) {

					JOptionPane.showMessageDialog(InactiveEmployeePanel.this,
							"Person Id must be integer");
				}
				else {
					PeopleDB peopleDB = new PeopleDB();
					People people = new People();
					people = peopleDB.get(Integer.parseInt(personidtf.getText().trim()));
					people.setActive(0);
					peopleDB.update(people);
	            	JOptionPane.showMessageDialog(InactiveEmployeePanel.this, "person Inactivated");
	          	
				}
				

			}
		});

		add(hint);
		add(personidlabel);
		add(personidtf);
		add(submitbtn);

		setBackground(Color.WHITE);


	}
}
