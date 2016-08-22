package com.consultancy.company;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.consultancy.company.ManagerTimeApprovalPanel.ButtonEditor;
import com.consultancy.company.ManagerTimeApprovalPanel.ButtonRenderer;
import com.consultancy.company.db.HoursDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Hours;
import com.consultancy.company.model.People;
import com.consultancy.company.model.Project;

public class HoursDetailsPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public HoursDetailsPanel() {

		// init();
	}

	private JTable jtable;

	public void addDatatotable(List<Hours> data) {

		System.out.println("DATA SUZE" + data.size());
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Id");

			columnNames.add("Employee Name");
			columnNames.add("Project Name");
			columnNames.add("Date");
			columnNames.add("Hours");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (Hours object : data) {

				if (object.getEmpid() == Integer.MAX_VALUE) {
					continue;
				}

				Vector<Object> vector = new Vector<Object>();
				vector.add(object.getId());

				PeopleDB peopleDB = new PeopleDB();
				People people = peopleDB.get(object.getEmpid());
				if (people != null) {
					vector.add(people.getName());
				} else {
					vector.add("Employee id" + object.getEmpid());

				}

				ProjectDB projectDB = new ProjectDB();
				Project project = projectDB.get(object.getProjectnumber());
				if (project != null) {
					vector.add(project.getName());
				} else {
					vector.add("Project id" + object.getProjectnumber());

				}

				vector.add(object.getDate());
				vector.add(object.getHours());

				datalist.add(vector);
			}

			tableModel.setDataVector(datalist, columnNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadData() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				HoursDB hoursDB = new HoursDB();
				List<Hours> hours = hoursDB.allHourss();
				addDatatotable(hours);
				return null;
			}
		}.execute();
	}

	public void init() {
		JPanel rootPanel = new JPanel();
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

				MainFrame.showCard("managerhomepage");
			}
		});

		JButton logout = new JButton("Logout");

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});

		horizontalPanel.add(backBtn);
		horizontalPanel.add(Box.createRigidArea(new Dimension(900, 0)));
		rootPanel.add(horizontalPanel);

		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalPanel.add(logout);

	

		setBackground(Color.WHITE);

		jtable = new JTable(tableModel);

		jtable.setPreferredScrollableViewportSize(new Dimension(1000, 410));
		JScrollPane jScrollPane = new JScrollPane(jtable);
		jScrollPane.setBackground(Color.WHITE);

		jScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));

		rootPanel.add(jScrollPane);

		loadData();
	}
}
