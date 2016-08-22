package com.consultancy.company;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.db.ProjectPersonDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.Project;
import com.consultancy.company.model.ProjectPerson;
import com.consultancy.company.service.CSVFileReader;

public class SeeProjectPersonPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public SeeProjectPersonPanel() {

		init();
	}

	public void addDatatotable(List<ProjectPerson> data) {
		try {

			ProjectDB projectDB = new ProjectDB();
			List<String> projectNames = projectDB.getprojectNames();

			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Project Number");
			columnNames.add("Person Name");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (ProjectPerson object : data) {

				Vector<Object> vector = new Vector<Object>();
				vector.add(object.getProjectnumber());
				vector.add(object.getPerson());
				if(!projectNames.contains(object.getPerson()))
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
				ProjectPersonDB projectPersonDB = new ProjectPersonDB();
				List<ProjectPerson> projectPersons = projectPersonDB
						.allProjectPersons();
				addDatatotable(projectPersons);
				return null;
			}
		}.execute();
	}

	public void init() {

		JPanel rootPanel = new JPanel();
		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		JTable jtable = new JTable(tableModel);
		jtable.setPreferredScrollableViewportSize(new Dimension(1000, 410));

		JScrollPane jScrollPane = new JScrollPane(jtable);

		rootPanel.add(jScrollPane);

		loadData();

	}

}
