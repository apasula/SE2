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

import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.model.People;
import com.consultancy.company.service.Util;

public class UpdateEmployeePanel extends JPanel {

	public UpdateEmployeePanel() {
		init();
	}

	int presentpeopleid;

	public void init() {

		setLayout(null);

		JLabel projectnumberlabelview = new JLabel("Enter Employee Id:");
		final JTextField personidtf = new JTextField();
		JButton submitbtnview = new JButton("Submit");

		JLabel namelabel = new JLabel("Enter Name:");
		JLabel titlelabel = new JLabel("Enter Title:");
		JLabel billratelabel = new JLabel("Enter Bill Rate:");
		JLabel rolelabel = new JLabel("Enter Role");

		final JTextField nametf = new JTextField();
		final JTextField titletf = new JTextField();
		final JTextField billratetf = new JTextField();
		String roleslabels[] = { "Project Manager", "Developer" };
		final JComboBox roletf = new JComboBox(roleslabels);

		JButton submitbtn = new JButton("Submit");

		submitbtnview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (personidtf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateEmployeePanel.this,
							"Please enter Person id");

				}

				else if (!Util.isInteger(personidtf.getText().trim())) {

					JOptionPane.showMessageDialog(UpdateEmployeePanel.this,
							"Id must be integer");
				}

				else {
					PeopleDB peopleDB = new PeopleDB();
					People people = peopleDB.get(Integer
							.parseInt(personidtf.getText().trim()));
					if (people == null) {

						JOptionPane.showMessageDialog(UpdateEmployeePanel.this,
								"Invalid Person Id");

					} else {
						presentpeopleid = people.getId();
						nametf.setText("" + people.getName());
						titletf.setText(people.getTitle());
						billratetf.setText(""+people.getBillrate());
						roletf.setSelectedItem(people.getRole());

				
					}
				}

			}
		});

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (nametf.getText().trim().equals("")
						|| titletf.getText().trim().equals("")
						|| billratetf.getText().trim().equals("")

				) {

					JOptionPane.showMessageDialog(UpdateEmployeePanel.this,
							"Please enter all fields");

				}

				else if (!Util.isInteger(billratetf.getText().trim())) {

					JOptionPane.showMessageDialog(UpdateEmployeePanel.this,
							"Bill rate must be integer");
				}

				else {

					PeopleDB peopleDB = new PeopleDB();

					People people = new People();
					people.setId(presentpeopleid);
					people.setName(nametf.getText().trim());
					people.setTitle(titletf.getText().trim());
					people.setBillrate(Integer.parseInt(billratetf.getText()
							.trim()));
					people.setRole((String) roletf.getSelectedItem());

					peopleDB.update(people);
					JOptionPane.showMessageDialog(UpdateEmployeePanel.this,
							"Person Updated");
					nametf.setText("");
					titletf.setText("");
					billratetf.setText("");
				}

			}
		});

		projectnumberlabelview.setBounds(230, 10, 300, 30);
		personidtf.setBounds(360, 10, 300, 30);
		submitbtnview.setBounds(700, 10, 100, 30);

		JLabel hint = new JLabel("Update Person");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		hint.setBounds(500, 50, 400, 30);

		int topindex = 70;
		int topmargin = 40;

		int firstcolindex = 200;
		int secondcolindex = 360;
		int width = 150;

		namelabel.setBounds(firstcolindex, topindex+=topmargin, width, 30);
		titlelabel.setBounds(firstcolindex,  topindex+=topmargin, width, 30);
		billratelabel.setBounds(firstcolindex,  topindex+=topmargin, width, 30);
		rolelabel.setBounds(firstcolindex,  topindex+=topmargin, width, 30);

		topindex = 70;
		topmargin = 40;
		width = 500;


		//firstcolindex += 150;
		//secondcolindex += 150;

		nametf.setBounds(secondcolindex,  topindex+=topmargin, width, 30);
		titletf.setBounds(secondcolindex,  topindex+=topmargin, width, 30);
		billratetf.setBounds(secondcolindex,  topindex+=topmargin, width, 30);
		roletf.setBounds(secondcolindex,  topindex+=topmargin, width, 30);

		submitbtn.setBounds(500, topindex += topmargin + 50, 100, 30);

		add(hint);
		add(projectnumberlabelview);
		add(personidtf);
		add(submitbtnview);

		add(namelabel);
		add(nametf);

		add(titlelabel);
		add(titletf);

		add(billratelabel);
		add(billratetf);

		add(rolelabel);
		add(roletf);

		add(submitbtn);
		setBackground(Color.WHITE);

	}
}
