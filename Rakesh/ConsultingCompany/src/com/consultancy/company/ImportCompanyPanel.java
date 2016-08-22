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
import com.consultancy.company.db.CompanyDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.Company;
import com.consultancy.company.service.CSVFileReader;

public class ImportCompanyPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ImportCompanyPanel() {

		init();
	}

	public void addDatatotable(List<Company> data) {
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Name");
			columnNames.add("Address Line 1");
			columnNames.add("Address Line 2");
			columnNames.add("City");
			columnNames.add("State");
			columnNames.add("Zip");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			Company object = data.get(0);

			if (object != null) {
				Vector<Object> vector = new Vector<Object>();

				vector.add(object.getName());
				vector.add(object.getAddressline1());
				vector.add(object.getAddressline2());
				vector.add(object.getCity());
				vector.add(object.getState());
				vector.add(object.getZip());
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
				CompanyDB companyDB = new CompanyDB();
				List<Company> companies = companyDB.allCompanys();
				System.out.println("CLIENTS" + companies.size());
				addDatatotable(companies);
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
		// jtable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		jtable.setPreferredScrollableViewportSize(new Dimension(1000, 410));

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
							csvFileReader
									.addCompany(fileField.getText().trim());

							uploadButton.setText("Upload");
							uploadButton.setEnabled(true);
							loadData();
							JOptionPane.showMessageDialog(
									ImportCompanyPanel.this, "Data Inserted");

							return null;
						}
					};

					mySwingWorker.execute();

				} else {
					JOptionPane.showMessageDialog(ImportCompanyPanel.this,
							"Please choose file");

				}
			}
		});
		loadData();

	}
}
