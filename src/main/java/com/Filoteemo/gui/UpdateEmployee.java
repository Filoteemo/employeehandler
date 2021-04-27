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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Filoteemo.database.EmployeeDao;
import com.Filoteemo.main.Employee;

public class UpdateEmployee extends JFrame implements ActionListener{
	
	 private int width = 600;
	 private int height = 400;
	 private int empId;
	 
	 ArrayList<Employee> selectEmployeeId = new ArrayList<>();
	 EmployeeDao emp = new EmployeeDao();
	 
	 private JLabel labelId = new JLabel("Enter ID: ");
	 private JComboBox<Integer> comboId = new JComboBox<Integer>();
	// private JTextField textId = new JTextField(20);
	 private JLabel labelFirstname = new JLabel("Enter firstname: ");
	 private JTextField textFirstname = new JTextField(20);
	 private JLabel labelLastname = new JLabel("Enter lastname: ");
	 private JTextField textLastname = new JTextField(20);
	 private JLabel labelEmail = new JLabel("Enter email: ");
	 private JTextField textEmail = new JTextField(20);
	 private JLabel labelDepartment = new JLabel("Enter department: ");
	 private JTextField textDepartment = new JTextField(20);
	 private JLabel labelSalary = new JLabel("Enter salary: ");
	 private JTextField textSalary = new JTextField(20);
	 private JButton submit = new JButton("Update  ");
	 private JButton exit = new JButton("Close  ");

	JTextField id = new JTextField();
	JPanel newPanel = new JPanel(new GridBagLayout());
	
	public UpdateEmployee() {
		//JFrame preferences
	   super("Update employee");
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
	   newPanel.add(labelFirstname, constraints);
		         
	   constraints.gridx = 1;
	   newPanel.add(textFirstname, constraints);
		        
	   constraints.gridx = 0;
	   constraints.gridy = 2;
	   newPanel.add(labelLastname, constraints);
		        
	   constraints.gridx = 1;
	   newPanel.add(textLastname, constraints);
		        
	   constraints.gridx = 0;
	   constraints.gridy = 3;
	   newPanel.add(labelEmail, constraints);
		        
	   constraints.gridx = 1;
	   newPanel.add(textEmail, constraints);
		        
	   constraints.gridx = 0;
	   constraints.gridy = 4;
	   newPanel.add(labelDepartment, constraints);
		        
	   constraints.gridx = 1;
	   newPanel.add(textDepartment, constraints);
		        
	   constraints.gridx = 0;
	   constraints.gridy = 5;
	   newPanel.add(labelSalary, constraints);
		        
	   constraints.gridx = 1;
	   newPanel.add(textSalary, constraints);
		        
	   constraints.gridx = 0;
	   constraints.gridy = 6;
		        
	   constraints.gridx = 1;
	   newPanel.add(submit, constraints);
       submit.addActionListener(this);
		        
       constraints.gridx = 0;
       constraints.gridy = 6;
		        
       constraints.gridx = 4;
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
			double salary;
			Employee updateEmp = new Employee();
			comboValue = comboId.getSelectedItem().toString();
			id = Integer.parseInt(comboValue);
			salary = Integer.parseInt(textSalary.getText());
			
			updateEmp.setId(id);
			updateEmp.setLastname(textLastname.getText());
			updateEmp.setFirstname(textFirstname.getText());
			updateEmp.setEmail(textEmail.getText());
			updateEmp.setDepartment(textDepartment.getText());
			updateEmp.setSalary(salary);
			emp.updateEmployee(updateEmp);
			System.out.println(id);
		}
		
	}
	
	
	
}
