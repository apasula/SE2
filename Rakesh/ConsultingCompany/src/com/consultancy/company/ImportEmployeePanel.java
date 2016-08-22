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

import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.model.People;
import com.consultancy.company.service.CSVFileReader;

public class ImportEmployeePanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ImportEmployeePanel() {

		init();
	}

	public void addDatatotable(List<People> data) {
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Id");
			columnNames.add("Name");
			columnNames.add("Title");
			columnNames.add("Bill Rate");
			columnNames.add("Role");
			columnNames.add("Track");
	
			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (People object : data) {

				Vector<Object> vector = new Vector<Object>();

				vector.add(object.getId());
				vector.add(object.getName());
				vector.add(object.getTitle());
				vector.add(object.getBillrate());
				vector.add(object.getRole());
				if(object.getActive()==1){
					vector.add("Active");

				}
				else{
					vector.add("InActive");
					
				}
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
				PeopleDB PeopleDB = new PeopleDB();
				List<People> Peoples = PeopleDB.allPeoples();
				System.out.println("PeopleS" + Peoples.size());
				addDatatotable(Peoples);
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
	//	jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
							csvFileReader.addPeoples(fileField.getText()
									.trim());

							uploadButton.setText("Upload");
							uploadButton.setEnabled(true);
							loadData();
							JOptionPane.showMessageDialog(
									ImportEmployeePanel.this, "Data Inserted");

							return null;
						}
					};

					mySwingWorker.execute();

				} else {
					JOptionPane.showMessageDialog(ImportEmployeePanel.this,
							"Please choose file");

				}
			}
		});
		loadData();

	}
}
