package com.consultancy.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Project;
import com.consultancy.company.service.GenerateInvoice;
import com.consultancy.company.service.SendEmailAttachment;

public class InvoiceReportPanel extends JPanel {

	public InvoiceReportPanel() {

	}

	public void init() {

		JPanel rootPanel = new JPanel();
		rootPanel.setBackground(Color.WHITE);
		// rootPanel.setPreferredSize(new Dimension(1080, 600));
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

				MainFrame.showCard("accountanthomepage");
			}
		});

		horizontalPanel.add(backBtn);
		horizontalPanel.add(Box.createRigidArea(new Dimension(900, 0)));
		rootPanel.add(horizontalPanel);

		JButton logout = new JButton("Logout");

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});
		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalPanel.add(logout);

		JPanel mainJPanel = new JPanel();
		mainJPanel.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setPreferredSize(new Dimension(200, 700));
		JScrollPane scrollPane1 = new JScrollPane();
		final DefaultListModel listModel = new DefaultListModel();

		ProjectDB projectDB = new ProjectDB();
		List<Project> projects = projectDB.allProjects();
		for (int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
			listModel
					.addElement(project.getPnumber() + "-" + project.getName());

		}

		final JList list = new JList(listModel);
		Dimension d = list.getPreferredSize();
		d.width = 185;

		list.setPreferredSize(d);
		list.setFont(new Font("Serif", Font.ITALIC, 20));
		scrollPane1.setPreferredSize(new Dimension(200, 400));
		scrollPane1.setViewportView(list);

		Box box1 = new Box(BoxLayout.Y_AXIS);
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("Select Project");
		jLabel1.setForeground(Color.blue);
		jLabel1.setFont(new Font("Serif", Font.BOLD, 20));
		box1.setBorder(BorderFactory.createTitledBorder(""));
		box1.add(jLabel1);
		box1.add(leftPanel);
		leftPanel.add(scrollPane1);
		rootPanel.add(mainJPanel);
		mainJPanel.add(box1, BorderLayout.WEST);
		setBackground(Color.WHITE);

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		BoxLayout box2 = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		rightPanel.setLayout(box2);

		JPanel horizontalpanel = new JPanel();
		horizontalpanel.setMaximumSize(new Dimension(850, 40));

		BoxLayout box3 = new BoxLayout(horizontalpanel, BoxLayout.X_AXIS);
		horizontalpanel.setLayout(box3);

		UtilDateModel model = new UtilDateModel();

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		final JDatePickerImpl startdatetf = new JDatePickerImpl(datePanel,
				new DateLabelFormatter1());

		model = new UtilDateModel();

		datePanel = new JDatePanelImpl(model, p);

		final JDatePickerImpl enddatetf = new JDatePickerImpl(datePanel,
				new DateLabelFormatter1());

		final JTextField hourstf = new JTextField();

		JLabel startdatelabel = new JLabel("Start Date:");
		JLabel enddatelabel = new JLabel("End Date:");
		final JButton submit = new JButton("Invoice Report");
		submit.setPreferredSize(new Dimension(150, 40));

		horizontalpanel.add(startdatelabel);
		horizontalpanel.add(startdatetf);
		horizontalpanel.add(enddatelabel);
		horizontalpanel.add(enddatetf);
		horizontalpanel.add(submit);

		rightPanel.add(horizontalpanel);

		final JEditorPane jep = new JEditorPane();
		jep.setEditable(false);
		jep.setContentType("text/html");
		jep.setText("<html></html>");

		JScrollPane scrollPane = new JScrollPane(jep);
		scrollPane.setMaximumSize(new Dimension(700, 440));
		rightPanel.add(scrollPane);

		mainJPanel.add(rightPanel, BorderLayout.CENTER);

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() {

						try {
							submit.setText("Wait");
							submit.setEnabled(false);
							String projectnumber = list.getSelectedValue()
									.toString().split("-")[0];
							String projectname = list.getSelectedValue()
									.toString().split("-")[1];

							String startdate = startdatetf
									.getJFormattedTextField().getText()
									.toString().trim();
							String enddate = enddatetf.getJFormattedTextField()
									.getText().toString().trim();

							GenerateInvoice generateInvoice = new GenerateInvoice();
							generateInvoice.invoicereport = true;

							String htmlcontent = generateInvoice
									.generatInvoice(startdate, enddate,
											projectnumber, projectname);
							jep.setText(htmlcontent);

							submit.setText("Submit");
							submit.setEnabled(true);
						} catch (Exception e) {

							submit.setText("Submit");
							submit.setEnabled(true);
							e.printStackTrace();
						}
						return null;
					}
				}.execute();

			}
		});
	}
}
