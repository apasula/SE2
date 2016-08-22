package com.consultancy.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.db.ProjectPersonDB;
import com.consultancy.company.model.People;
import com.consultancy.company.model.Project;
import com.consultancy.company.model.ProjectPerson;
import com.consultancy.company.service.Util;

public class AssignProjectsFormPanel extends JPanel {

	public AssignProjectsFormPanel() {
		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Assign Projects");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel projectnumberlabel = new JLabel("Choose Project Number:");
		JLabel personlabel = new JLabel("Choose Person:");

		ProjectDB projectDB = new ProjectDB();
		List<Project> projects = projectDB.allProjects();
		String[] projectnumberslabels = new String[projects.size()];
		for (int i=0;i<projects.size();i++) {
			Project project =  projects.get(i);
			projectnumberslabels[i] =""+project.getPnumber();
		}
		final JComboBox projectnumbertf = new JComboBox(projectnumberslabels);

		PeopleDB peopleDB = new PeopleDB();
		List<People> peoples = peopleDB.allPeoples();
		String[] personlabels = new String[peoples.size()];
		for (int i=0;i<peoples.size();i++) {
			People  people =  peoples.get(i);
			personlabels[i] =""+people.getName();
		}
		final JComboBox persontf = new JComboBox(personlabels);

		JButton submitbtn = new JButton("Assign");

		hint.setBounds(500, 30, 400, 30);

		int topindex = 70;
		int topmargin = 40;

		int firstcolindex = 200;
		int secondcolindex = 360;
		int width = 150;

		projectnumberlabel.setBounds(firstcolindex, topindex += topmargin,
				width, 30);
		personlabel.setBounds(firstcolindex, topindex += topmargin, width, 30);

		topindex = 70;
		topmargin = 40;
		width = 500;

		// firstcolindex += 150;
		// secondcolindex += 150;

		projectnumbertf.setBounds(secondcolindex, topindex += topmargin, width,
				30);
		persontf.setBounds(secondcolindex, topindex += topmargin, width, 30);

		submitbtn.setBounds(500, topindex += topmargin + 50, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				ProjectPersonDB projectPersonDB = new ProjectPersonDB();

				ProjectPerson projectPerson = new ProjectPerson();
				projectPerson.setPerson((String) persontf.getSelectedItem());
				projectPerson.setProjectnumber(Integer
						.parseInt((String) projectnumbertf.getSelectedItem()));
				projectPersonDB.insert(projectPerson);
				JOptionPane.showMessageDialog(AssignProjectsFormPanel.this,
						"Project Assign to person");
			}
		});

		add(hint);
		add(projectnumberlabel);
		add(personlabel);

		add(projectnumbertf);
		add(persontf);

		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
