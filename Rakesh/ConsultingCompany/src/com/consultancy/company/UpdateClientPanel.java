package com.consultancy.company;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.service.Util;

public class UpdateClientPanel extends JPanel {

	public UpdateClientPanel() {
		init();
	}

	int presentClientid;

	public void init() {

		setLayout(null);

		JLabel clientidlabelview = new JLabel("Enter Client Number:");
		final JTextField clientidtfview = new JTextField();
		JButton submitbtnview = new JButton("Submit");

		JLabel clientnamelabel = new JLabel("Enter Client Name:");
		JLabel addressline1label = new JLabel("Enter Address Line 1:");
		JLabel addressline2label = new JLabel("Enter Address Line 2:");
		JLabel citylabel = new JLabel("Enter City:");
		JLabel statelabel = new JLabel("Enter State:");
		JLabel ziplabel = new JLabel("Enter Zip:");
		JLabel emaillabel = new JLabel("Enter Email:");
		JLabel contactlabel = new JLabel("Enter Contact:");
		JLabel invoicefreqlabel = new JLabel("Invoice Freq:");
		JLabel billingtermslabel = new JLabel("Billing Terms:");
		JLabel invoicegroupinglabel = new JLabel("Invoice Grouping:");

		final JTextField clientnametf = new JTextField();
		final JTextField addressline1tf = new JTextField();
		final JTextField addressline2tf = new JTextField();
		final JTextField citytf = new JTextField();
		final JTextField statetf = new JTextField();
		final JTextField ziptf = new JTextField();
		final JTextField emailtf = new JTextField();
		final JTextField contacttf = new JTextField();
		String invoicefreqlabels[] = { "Weekly", "BiWeekly", "Monthly",
				"Monthly-Cal" };
		final JComboBox invoicefreqtf = new JComboBox(invoicefreqlabels);
		String billingtermslabels[] = { "Due on Recipt", "Net 10 Days",
				"Net 20 Days", "Net 30 Days", "Net 60 Days" };
		final JComboBox billingtermstf = new JComboBox(billingtermslabels);
		String invoicegrouplabels[] = { "Project", "Invoice" };
		final JComboBox invoicegrouptf = new JComboBox(invoicegrouplabels);
		JButton submitbtn = new JButton("Submit");

		submitbtnview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (clientidtfview.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateClientPanel.this,
							"Please enter Client id");

				}
				
				else if (!Util.isInteger(clientidtfview.getText().trim())) {

					JOptionPane.showMessageDialog(UpdateClientPanel.this,
							"Client Number must be integer");
				}
				
				else {
					ClientDB ClientDB = new ClientDB();
					Client client = ClientDB.get(Integer
							.parseInt(clientidtfview.getText().trim()));
					if (client == null) {

						JOptionPane.showMessageDialog(UpdateClientPanel.this,
								"Invalid Client Id");

					} else {
						presentClientid = client.getCnumber();
						System.out.println();
						clientnametf.setText(client.getName());
						addressline1tf.setText(client.getAddressline1());
						addressline2tf.setText(client.getAddressline2());
						citytf.setText(client.getCity());
						statetf.setText(client.getState());
						ziptf.setText(client.getZip());
						emailtf.setText(client.getEmail());
						contacttf.setText(client.getContact());
						invoicefreqtf.setSelectedItem(client.getInvoicefreq());
						billingtermstf.setSelectedItem(client.getBillingterms());
						invoicegrouptf.setSelectedItem(client.getInvoicegrouping());
					}
				}

			}
		});

		
		clientidlabelview.setBounds(230, 10, 300, 30);
		clientidtfview.setBounds(360, 10, 300, 30);
		submitbtnview.setBounds(700, 10, 100, 30);

		
		JLabel hint = new JLabel("Update Client");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		hint.setBounds(500, 50, 400, 30);

		int topindex = 100;
		int topmargin = 40;

		int firstcolindex = 50;
		int secondcolindex = 600;
		int width = 300;

		clientnamelabel.setBounds(secondcolindex, topindex, width, 30);
		addressline1label.setBounds(firstcolindex, topindex += topmargin,
				width, 30);
		addressline2label.setBounds(secondcolindex, topindex, width, 30);
		citylabel.setBounds(firstcolindex, topindex += topmargin, width, 30);
		statelabel.setBounds(secondcolindex, topindex, width, 30);
		ziplabel.setBounds(firstcolindex, topindex += topmargin, width, 30);
		emaillabel.setBounds(secondcolindex, topindex, width, 30);
		contactlabel.setBounds(firstcolindex, topindex += topmargin, width, 30);
		invoicefreqlabel.setBounds(secondcolindex, topindex, width, 30);
		billingtermslabel.setBounds(firstcolindex, topindex += topmargin,
				width, 30);
		invoicegroupinglabel.setBounds(secondcolindex, topindex, width, 30);

		topindex = 100;
		topmargin = 40;

		firstcolindex += 150;
		secondcolindex += 150;

		clientnametf.setBounds(secondcolindex, topindex, width, 30);
		addressline1tf.setBounds(firstcolindex, topindex += topmargin, width,
				30);
		addressline2tf.setBounds(secondcolindex, topindex, width, 30);
		citytf.setBounds(firstcolindex, topindex += topmargin, width, 30);
		statetf.setBounds(secondcolindex, topindex, width, 30);
		ziptf.setBounds(firstcolindex, topindex += topmargin, width, 30);
		emailtf.setBounds(secondcolindex, topindex, width, 30);
		contacttf.setBounds(firstcolindex, topindex += topmargin, width, 30);
		invoicefreqtf.setBounds(secondcolindex, topindex, width, 30);
		billingtermstf.setBounds(firstcolindex, topindex += topmargin, width,
				30);
		invoicegrouptf.setBounds(secondcolindex, topindex, width, 30);

		submitbtn.setBounds(500, topindex += topmargin + 20, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (clientnametf.getText().trim().equals("")
						|| addressline1tf.getText().trim().equals("")
						|| citytf.getText().trim().equals("")
						|| statetf.getText().trim().equals("")
						|| ziptf.getText().trim().equals("")
						|| emailtf.getText().trim().equals("")
						|| contacttf.getText().trim().equals("")

				) {
					JOptionPane.showMessageDialog(UpdateClientPanel.this,
							"Please enter all fields");

				} else {

					ClientDB clientDB = new ClientDB();
					Client client = new Client();
    				client.setCnumber(presentClientid);
    				client.setName(clientnametf.getText().trim());
    				client.setAddressline1(addressline1tf.getText().trim());
    				client.setAddressline2(addressline2tf.getText().trim());
    				client.setCity(citytf.getText().trim());
    				client.setState(statetf.getText().trim());
    				client.setZip(ziptf.getText().trim());
    				client.setEmail(emailtf.getText().trim());
    				client.setContact(contacttf.getText().trim());
    				client.setInvoicefreq((String)invoicefreqtf.getSelectedItem());
    				client.setBillingterms((String)billingtermstf.getSelectedItem());
    				client.setInvoicegrouping((String)invoicegrouptf.getSelectedItem());
					clientDB.update(client);
					JOptionPane.showMessageDialog(UpdateClientPanel.this,
							"Client Updated");
					clientidtfview.setText("");
					clientnametf.setText("");
					addressline1tf.setText("");
					addressline2tf.setText("");
					citytf.setText("");
					statetf.setText("");
					ziptf.setText("");
					emailtf.setText("");
					contacttf.setText("");
				}
				

			}
		});

		add(hint);

		add(clientidlabelview);
		add(clientidtfview);
		add(submitbtnview);

		add(clientnamelabel);
		add(clientnametf);
		add(addressline1label);
		add(addressline1tf);

		add(addressline2label);
		add(addressline2tf);

		add(citylabel);
		add(citytf);

		add(statelabel);
		add(statetf);

		add(ziplabel);
		add(ziptf);

		add(emaillabel);
		add(emailtf);

		add(contactlabel);
		add(contacttf);

		add(invoicefreqlabel);
		add(invoicefreqtf);

		add(billingtermslabel);
		add(billingtermstf);

		add(invoicegroupinglabel);
		add(invoicegrouptf);

		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
