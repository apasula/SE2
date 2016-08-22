package com.consultancy.company;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.omg.CORBA.INTERNAL;

import com.consultancy.company.db.ClientDB;
import com.consultancy.company.db.HoursDB;
import com.consultancy.company.db.PeopleDB;
import com.consultancy.company.db.ProjectDB;
import com.consultancy.company.model.Client;
import com.consultancy.company.model.Hours;
import com.consultancy.company.model.People;
import com.consultancy.company.model.Project;
import com.sun.javafx.event.EventQueue;

public class ManagerTimeApprovalPanel extends JPanel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ManagerTimeApprovalPanel() {

	//	init();
	}

	private JTable jtable;

	public void addDatatotable(List<Hours> data) {
		
		
		System.out.println("DATA SUZE"+data.size());
		try {
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Id");

			columnNames.add("Employee Name");
			columnNames.add("Project Name");
			columnNames.add("Date");
			columnNames.add("Hours");
			columnNames.add("Approve");

			Vector<Vector<Object>> datalist = new Vector<Vector<Object>>();
			for (Hours object : data) {
				
				if(object.getEmpid()==Integer.MAX_VALUE){
					continue;
				}

				Vector<Object> vector = new Vector<Object>();
				vector.add(object.getId());

				
				PeopleDB peopleDB = new PeopleDB();
				People people = peopleDB.get(object.getEmpid());
				if(people!=null){
				  vector.add(people.getName());
				}
				else{
					  vector.add("Employee id"+object.getEmpid());
					
				}
				
				ProjectDB projectDB = new ProjectDB();
				Project project = projectDB.get(object.getProjectnumber());
				if(project!=null){
				  vector.add(project.getName());
				}
				else{
					  vector.add("Project id"+object.getProjectnumber());
					
				}
				
				vector.add(object.getDate());
				vector.add(object.getHours());
				vector.add("Approve");

				datalist.add(vector);
			}

			tableModel.setDataVector(datalist, columnNames);
			jtable.setRowHeight(40, 70);

			
			 jtable.getColumn("Approve").setCellRenderer(new ButtonRenderer());
			 jtable.getColumn("Approve").setCellEditor(
			        new ButtonEditor(new JCheckBox()));
			 
			 updateRowHeights();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadData() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				HoursDB hoursDB = new HoursDB();
				List<Hours> hours = hoursDB.allnonapprovedhours();
				addDatatotable(hours);
				return null;
			}
		}.execute();
	}

	public void init() {

		JPanel rootPanel = new JPanel();
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

				MainFrame.showCard("managerhomepage");
			}
		});

		JButton logout = new JButton("Logout");

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});

		horizontalPanel.add(backBtn);
		horizontalPanel.add(Box.createRigidArea(new Dimension(900, 0)));
		rootPanel.add(horizontalPanel);

		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalPanel.add(logout);
		
		
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				loadData();
			}
		});


		rootPanel.add(refreshBtn);

		setBackground(Color.WHITE);

		jtable = new JTable(tableModel);
		
	
		jtable.setPreferredScrollableViewportSize(new Dimension(1000, 410));
		JScrollPane jScrollPane = new JScrollPane(jtable);
		jScrollPane.setBackground(Color.WHITE);

		jScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));

		rootPanel.add(jScrollPane);

		loadData();

	}
	
	private void updateRowHeights()
	{
	    try
	    {
	        for (int row = 0; row < jtable.getRowCount(); row++)
	        {
	            int rowHeight = jtable.getRowHeight();

	            for (int column = 0; column < jtable.getColumnCount(); column++)
	            {
	                Component comp = jtable.prepareRenderer(jtable.getCellRenderer(row, column), row, column);
	                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	            }

	            jtable.setRowHeight(row, rowHeight);
	        }
	    }
	    catch(ClassCastException e) {}
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
			//	setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}


	class ButtonEditor extends DefaultCellEditor {
		protected JButton button;

		private String label;

		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			
			
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			
			button.setPreferredSize(new Dimension(40, 80));
			
			HoursDB hoursDB = new HoursDB();
			int id = (Integer)table.getValueAt(row, 0);
			Hours hours = hoursDB.get(id);
			hours.setApproved(1);
			hours.setHours(Integer.parseInt(""+ table.getValueAt(row, 4)));
			hoursDB.update(hours);
			JOptionPane.showMessageDialog(ManagerTimeApprovalPanel.this,"Approved");

			((DefaultTableModel)jtable.getModel()).removeRow(row);

			isPushed = true;
			return button;
		}

		public Object getCellEditorValue() {
			if (isPushed) {
				//
				//
				// System.out.println(label + ": Ouch!");
			}
			isPushed = false;
			return new String(label);
		}

		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}

}
