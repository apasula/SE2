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
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.HoursDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.People;
import com.consultancy.company.model.Project;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ProjectReportPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ProjectReportPanel() {

		init();
	}

	public void print(JTable table) {

		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("projectreport.pdf"));
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
			JOptionPane.showMessageDialog(ProjectReportPanel.this,
					"File downloaded to" + System.getProperty("user.dir"));

			System.out.println("done");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addDatatotable(List<Project> data) {
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Project Id");
			columnNames.add("Project Name");
			columnNames.add("Start Date");
			columnNames.add("End Date");
			columnNames.add("Status");
			// columnNames.add("Client Id");
			columnNames.add("Client Name");
			columnNames.add("Invoice Frequency");
			// columnNames.add("Billing Terms");
			// columnNames.add("Invoice Grouping");
			columnNames.add("Project Budget");
			columnNames.add("Used Budget");
			columnNames.add("Pending");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (Project project : data) {

				Vector<Object> vector = new Vector<Object>();

				ClientDB clientDB = new ClientDB();
				Client client = clientDB.get(project.getClient());

				HoursDB hoursDB = new HoursDB();

				if (client == null) {
					continue;
				}

				vector.add(project.getPnumber());
				vector.add(project.getName());
				vector.add(project.getStartdate());
				vector.add(project.getEnddate());
				vector.add(project.getStatus());
				// vector.add(client.getCnumber());
				vector.add(client.getName());
				vector.add(client.getInvoicefreq());
				// vector.add(client.getBillingterms());
				// vector.add(client.getInvoicegrouping());
				vector.add(project.getBudget());

				double usedbudget = hoursDB.usedbudget(project.getPnumber());
				vector.add(usedbudget);

				vector.add(project.getBudget() - usedbudget);

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
				ProjectDB projectDB = new ProjectDB();
				List<Project> data = projectDB.allProjects();
				System.out.println("DATA SIZE" + data.size());
				addDatatotable(data);
				return null;
			}
		}.execute();
	}

	public void init() {

		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setBackground(Color.WHITE);

		BoxLayout hbox = new BoxLayout(horizontalPanel, BoxLayout.X_AXIS);
		horizontalPanel.setLayout(hbox);

		JButton backBtn = new JButton("Back");

		JLabel headinglabel = new JLabel("Project Report");

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
		// jtable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		jtable.setPreferredScrollableViewportSize(new Dimension(1000, 410));
		JScrollPane jScrollPane = new JScrollPane(jtable);
		rootPanel.add(horizontalPanel);

		rootPanel.add(jScrollPane);


		download.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				print(jtable);
			}
		});
		
		
		loadData();
	}
}
