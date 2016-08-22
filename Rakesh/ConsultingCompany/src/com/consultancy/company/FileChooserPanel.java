package com.consultancy.company;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileChooserPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton uploadButton;
	public JTextField fileField;
	public JButton refreshBtn;

	public FileChooserPanel() {
		JPanel fileChooserPanel = new JPanel();
		FlowLayout layout = new FlowLayout(1, 5, 5);
		fileChooserPanel.setLayout(layout);
		JLabel fileChooserLabel = new JLabel("Choose File");
		fileField = new JTextField("");
		fileField.setSize(100, 10);
		fileField.setColumns(35);
		fileChooserPanel.add(fileChooserLabel);
		fileChooserPanel.add(fileField);
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();

				int value = fileChooser.showOpenDialog(null);
				if (value == JFileChooser.APPROVE_OPTION) {
					fileField.setText(fileChooser.getSelectedFile().toString());
				}
			}
		});
		uploadButton = new JButton("Upload");
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				fileField.setText("");
			}
		});

		 refreshBtn = new JButton("Refresh");
	
		
		fileChooserPanel.add(browseButton);
		fileChooserPanel.add(uploadButton);
		fileChooserPanel.add(cancelButton);
		fileChooserPanel.add(refreshBtn);

		add(fileChooserPanel);
	}

}
