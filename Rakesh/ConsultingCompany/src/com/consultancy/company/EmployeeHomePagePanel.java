package com.consultancy.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;



import com.consultancy.company.db.HoursDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Hours;
import com.consultancy.company.model.Project;
import com.consultancy.company.service.Constants;
import com.consultancy.company.service.Util;

public class EmployeeHomePagePanel extends JPanel {

	public EmployeeHomePagePanel() {
		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel datelabel = new JLabel("Enter date (mm/dd/yyyy):");
		JLabel selectprojectlabel = new JLabel("Select Project:");
		JLabel hourslabel = new JLabel("Enter hours:");
		
		ProjectDB projectDB = new ProjectDB();
		final List<Project> projects =  projectDB.allProjects();
		
		String[] projectnameslabels = new String[projects.size()];
		for (int i=0;i<projects.size();i++) {
			Project project =  projects.get(i);
			projectnameslabels[i] =""+project.getName();
		}
        
		final JComboBox projectnamestf = new JComboBox(projectnameslabels);


		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
	
		// Don't know about the formatter, but there it is...
		final JDatePickerImpl datetf = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		final JTextField hourstf = new JTextField();

		JButton submitbtn = new JButton("Submit");

		hint.setBounds(500, 30, 400, 30);
		int topindex = 70;
		int topmargin = 40;

		int firstcolindex = 200;
		int secondcolindex = 360;
		int width = 150;

		datelabel.setBounds(firstcolindex, topindex, width, 30);
		hourslabel.setBounds(firstcolindex, topindex+=topmargin, width, 30);
		selectprojectlabel.setBounds(firstcolindex, topindex+=topmargin, width, 30);

		firstcolindex += 150;
		
		topindex = 70;
		topmargin = 40;
		 width = 500;

		datetf.setBounds(secondcolindex, topindex, width, 30);
		hourstf.setBounds(secondcolindex, topindex+=topmargin, width, 30);
		projectnamestf.setBounds(secondcolindex, topindex+=topmargin, width, 30);

		submitbtn.setBounds(500, topindex += topmargin + 20, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (datetf.getJFormattedTextField().getText().trim().equals("")
						|| hourstf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(EmployeeHomePagePanel.this,
							"Please enter all fields");
					return;

				}

				if (!Util.validDate(datetf.getJFormattedTextField().getText().trim())) {

					JOptionPane.showMessageDialog(EmployeeHomePagePanel.this,
							"Date is not valid");
					return;
				}

				if (!Util.isInteger(hourstf.getText().trim())) {

					JOptionPane.showMessageDialog(EmployeeHomePagePanel.this,
							"Hours must be integer");
					return;
				}
				
				Hours hours = new Hours();
				hours.setEmpid(Constants.empid);
				hours.setDate(datetf.getJFormattedTextField().getText().trim());
				hours.setHours(Integer.parseInt(hourstf.getText().trim()));
				hours.setProjectnumber(projects.get(projectnamestf.getSelectedIndex()).getPnumber());
				
				if(Constants.loggedinUser==1){
					hours.setApproved(1);
					//hours.setEmpid(Integer.MAX_VALUE);
				}
				
                HoursDB hoursDB = new HoursDB();
                
                
                hoursDB.insert(hours);
                
                
                

				JOptionPane.showMessageDialog(EmployeeHomePagePanel.this,
						"Hours added");

			}
		});
		

		
		JButton logout = new JButton("Logout");
		logout.setBounds(950, 10, 100, 30);


		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});
		

		add(logout);
		
		if(Constants.loggedinUser==1){
			JButton backBtn = new JButton("Back");
			backBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {

					MainFrame.showCard("managerhomepage");
				}
			});
			backBtn.setBounds(150, 10, 100, 30);
			
			add(backBtn);

		}

		add(datelabel);
		add(datetf);

		add(hourslabel);
		add(hourstf);
		
		add(selectprojectlabel);
		add(projectnamestf);

		

		add(submitbtn);
		
	
		
		
		setBackground(Color.WHITE);

	}

}
