package com.consultancy.company;

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

public class AddClientPanel extends JPanel {

	public AddClientPanel() {
		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Add Client");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel clientidlabel = new JLabel("Enter Client Number:");
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

		final JTextField clientidtf = new JTextField();
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

		hint.setBounds(500, 30, 400, 30);

		int topindex = 70;
		int topmargin = 40;

		int firstcolindex = 50;
		int secondcolindex = 600;
		int width = 300;

		clientidlabel.setBounds(firstcolindex, topindex, width, 30);
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

		topindex = 70;
		topmargin = 40;

		firstcolindex += 150;
		secondcolindex += 150;

		clientidtf.setBounds(firstcolindex, topindex, width, 30);
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

				if (clientidtf.getText().trim().equals("")
						|| clientnametf.getText().trim().equals("")
						|| addressline1tf.getText().trim().equals("")
						|| citytf.getText().trim().equals("")
						|| statetf.getText().trim().equals("")
						|| ziptf.getText().trim().equals("")
						|| emailtf.getText().trim().equals("")
						|| contacttf.getText().trim().equals("")

				) {

					JOptionPane.showMessageDialog(AddClientPanel.this,
							"Please enter all fields");

				}

				else if (!Util.isInteger(clientidtf.getText().trim())) {

					JOptionPane.showMessageDialog(AddClientPanel.this,
							"Client Number must be integer");
				}

				else {

					ClientDB clientDB = new ClientDB();
					if (clientDB.get(Integer.parseInt(clientidtf.getText()
							.trim())) != null) {

						JOptionPane.showMessageDialog(AddClientPanel.this,
								"Client Number already exists");

					}

					else {
						Client client = new Client();
        				client.setCnumber(Integer.parseInt(clientidtf.getText()
								.trim()));
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
    					clientDB.insert(client);
    					JOptionPane.showMessageDialog(AddClientPanel.this,
								"Client Added");
    					clientidtf.setText("");
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

			}
		});

		add(hint);
		add(clientidlabel);
		add(clientidtf);

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
