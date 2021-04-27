package com.Filoteemo.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Filoteemo.database.EmployeeDao;
import com.Filoteemo.main.Employee;

public class DeleteEmployee extends JFrame implements ActionListener {
	
	 private int width = 500;
	 private int height = 400;
	 private int empId;
	 
	 ArrayList<Employee> selectEmployeeId = new ArrayList<>();
	 EmployeeDao emp = new EmployeeDao();
	 
	 private JLabel labelId = new JLabel("Enter ID: ");
	 private JComboBox<Integer> comboId = new JComboBox<Integer>();
	 private JButton submit = new JButton("Delete  ");
	 private JButton exit = new JButton("Close  ");

	JTextField id = new JTextField();
	JPanel newPanel = new JPanel(new GridBagLayout());
	
	public DeleteEmployee() {
		//JFrame preferences
		   super("Delete employee");
		   setSize(width, height);
		   setLocationRelativeTo(null);
		   setResizable(false);
		   setDefaultLookAndFeelDecorated(true);
		   add(newPanel);
					
		   GridBagConstraints constraints = new GridBagConstraints();
		   constraints.anchor = GridBagConstraints.EAST;
		   constraints.insets = new Insets(10, 10, 10, 10);
			         
		   // add components to the panel
		   
		   constraints.gridx = 0;
		   constraints.gridy = 0;     
		   newPanel.add(labelId, constraints);
			
		   selectEmployeeId = emp.getEmployees();
		   Iterator<Employee> itr = selectEmployeeId.iterator(); // iterates through arraylist of employees
		   while(itr.hasNext()) {
				Employee viewById = itr.next();
				comboId.addItem(viewById.getId());
			}
		   constraints.gridx = 1;
		   newPanel.add(comboId, constraints);
		   comboId.setPreferredSize(new Dimension(220,25));
			        
		   constraints.gridx = 0;
		   constraints.gridy = 1;
			        
		   constraints.gridx = 1;
		   newPanel.add(submit, constraints);
	       submit.addActionListener(this);
			        
	       constraints.gridx = 0;
	       constraints.gridy = 1;
			        
	       constraints.gridx = 2;
	       newPanel.add(exit, constraints);
	       exit.addActionListener(this);
			        
	       constraints.gridx = 0;
	       constraints.gridy = 2;
	       constraints.gridwidth = 2;
	       constraints.anchor = GridBagConstraints.CENTER;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exit) {
			dispose();
		}
		
		if(e.getSource()==submit) {
			int id;
			String comboValue;
			comboValue = comboId.getSelectedItem().toString();
			id = Integer.parseInt(comboValue);
			emp.deleteEmployee(id);
			JOptionPane.showMessageDialog(null, "Delete succeed!");
		}
		
	}

}
