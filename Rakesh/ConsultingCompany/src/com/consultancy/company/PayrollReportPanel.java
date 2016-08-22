package com.consultancy.company;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.HoursDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.People;
import com.consultancy.company.service.CSVFileReader;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PayrollReportPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public PayrollReportPanel() {

		init();
	}

	public void addDatatotable(List<Object[]> data) {
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Employee Id");
			columnNames.add("Employee Name");
			columnNames.add("Title");
			columnNames.add("Bill Rate");
			columnNames.add("No of hours worked");
			columnNames.add("Total Bill");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (Object[] object : data) {

				Vector<Object> vector = new Vector<Object>();

				long hours  = (Long)object[0];
				int employeeid  = (Integer)object[1];
				
				
				
				System.out.println("EMP ID"+employeeid);
				int projectnumber  = (Integer)object[2];
 
				PeopleDB peopleDB = new PeopleDB();
				People people = peopleDB.get(employeeid);
				
				if(people==null)
					continue;
				
				vector.add(people.getId());
				vector.add(people.getName());
				vector.add(people.getTitle());
				vector.add(people.getBillrate());
				
				
				vector.add(hours);
				vector.add(people.getBillrate() * hours);
				
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
				System.out.println("LOAD DATA");
				HoursDB hoursDB = new HoursDB();
				List<Object[]> data = hoursDB.getWorkedHours();
				System.out.println("DATA SIZE"+data.size());
				addDatatotable(data);
				return null;
			}
		}.execute();
	}
	
	public void print(JTable table) {

		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("payrollreport.pdf"));
			doc.open();
			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
			for (int i = 0; i < table.getColumnCount(); i++) {
				pdfTable.addCell(table.getColumnName(i));
			}
			for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					pdfTable.addCell(table.getModel().getValueAt(rows, cols)
							.toString());

				}
			}
			doc.add(pdfTable);
			doc.close();
			JOptionPane.showMessageDialog(PayrollReportPanel.this,
					"File download to" + System.getProperty("user.dir"));

			System.out.println("done");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void init() {

		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setBackground(Color.WHITE);

		BoxLayout hbox = new BoxLayout(horizontalPanel, BoxLayout.X_AXIS);
		horizontalPanel.setLayout(hbox);

		JButton backBtn = new JButton("Back");
		
		JLabel headinglabel = new JLabel("Payroll Report");
		
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("accountanthomepage");
			}
		});

		
		horizontalPanel.add(backBtn);
		horizontalPanel.add(Box.createRigidArea(new Dimension(15, 0)));

		headinglabel.setForeground(Color.blue);
		headinglabel.setFont(new Font("Serif", Font.BOLD, 20));

		horizontalPanel.add(headinglabel);
		horizontalPanel.add(Box.createRigidArea(new Dimension(650, 0)));

		JButton refreshBtn = new JButton("Refresh");
		horizontalPanel.add(refreshBtn);
		
		JButton download = new JButton("Download");
		horizontalPanel.add(download);

	
		

		refreshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				loadData();
			}
		});

		JButton logout = new JButton("Logout");

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});
		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalPanel.add(logout);

		JPanel rootPanel = new JPanel();
		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		final JTable jtable = new JTable(tableModel);
		download.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				print(jtable);
			}
		});
		// jtable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		jtable.setPreferredScrollableViewportSize(new Dimension(1000, 410));
		JScrollPane jScrollPane = new JScrollPane(jtable);
		rootPanel.add(horizontalPanel);

		rootPanel.add(jScrollPane);

		loadData();

	}
}
