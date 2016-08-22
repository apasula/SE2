package com.consultancy.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.service.Util;

public class ActivateClientPanel extends JPanel{

	public ActivateClientPanel(){
		init();
	}
	
	public void init(){
		setLayout(null);

		JLabel hint = new JLabel("Activate Client");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel clientidlabel = new JLabel("Enter Client number:");
		final JTextField clientidtf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(500, 30, 400, 30);
		
		clientidlabel.setBounds(230, 80, 300, 30);
		clientidtf.setBounds(360, 80, 300, 30);
		submitbtn.setBounds(700, 80, 100, 30);


		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (clientidtf.getText().trim().equals("")
						|| clientidtf.getText().trim().equals("")) {

	            	JOptionPane.showMessageDialog(ActivateClientPanel.this, "Please enter client id");

				}
				
				else if (!Util.isInteger(clientidtf.getText().trim())) {

					JOptionPane.showMessageDialog(ActivateClientPanel.this,
							"Client Number must be integer");
				}
				else {
					ClientDB clientDB = new ClientDB();
					Client client = new Client();
					client = clientDB.get(Integer.parseInt(clientidtf.getText().trim()));
					client.setActive(1);
					clientDB.update(client);
	            	JOptionPane.showMessageDialog(ActivateClientPanel.this, "client Activated");
	          	
				}
				

			}
		});

		add(hint);
		add(clientidlabel);
		add(clientidtf);
		add(submitbtn);

		setBackground(Color.WHITE);


	}
}


