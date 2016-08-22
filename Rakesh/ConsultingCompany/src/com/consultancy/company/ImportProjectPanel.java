
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

import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Project;
import com.consultancy.company.service.CSVFileReader;

public class ImportProjectPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ImportProjectPanel() {

		init();
	}

	public void addDatatotable(List<Project> data) {
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Client");
			columnNames.add("Project Number");
			columnNames.add("Project Name");
			columnNames.add("State Date");
			columnNames.add("End Date");
			columnNames.add("Status");
			columnNames.add("Project Manager");
			columnNames.add("Client Contact");
			columnNames.add("Budget");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (Project object : data) {

				Vector<Object> vector = new Vector<Object>();

				vector.add(object.getClient());
				vector.add(object.getPnumber());
				vector.add(object.getName());
				vector.add(object.getStartdate());
				vector.add(object.getEnddate());
				vector.add(object.getStatus());
				vector.add(object.getManager());
				vector.add(object.getContact());
				vector.add(object.getBudget());

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
				ProjectDB ProjectDB = new ProjectDB();
				List<Project> Projects = ProjectDB.allProjects();
				System.out.println("ProjectS" + Projects.size());
				addDatatotable(Projects);
				return null;
			}
		}.execute();
	}

	public void init() {

		JPanel rootPanel = new JPanel();
		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		FileChooserPanel fileChooserPanel = new FileChooserPanel();
		rootPanel.add(fileChooserPanel);

		JTable jtable = new JTable(tableModel);
		//jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtable.setPreferredScrollableViewportSize(new Dimension(1000,410));

		JScrollPane jScrollPane = new JScrollPane(jtable);

		rootPanel.add(jScrollPane);

		final JButton uploadButton = fileChooserPanel.uploadButton;
		final JTextField fileField = fileChooserPanel.fileField;
		
		fileChooserPanel.refreshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				loadData();
			}
		});

		uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!fileField.getText().trim().equals("")) {

					SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {

							uploadButton.setText("Uploading");
							uploadButton.setEnabled(false);
							CSVFileReader csvFileReader = new CSVFileReader();
							csvFileReader.addProjects(fileField.getText()
									.trim());

							uploadButton.setText("Upload");
							uploadButton.setEnabled(true);
							loadData();
							JOptionPane.showMessageDialog(
									ImportProjectPanel.this, "Data Inserted");

							return null;
						}
					};

					mySwingWorker.execute();

				} else {
					JOptionPane.showMessageDialog(ImportProjectPanel.this,
							"Please choose file");

				}
			}
		});
		loadData();

	}
}
