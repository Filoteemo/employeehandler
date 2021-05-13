package com.Filoteemo.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.Filoteemo.database.EmployeeDao;
import com.Filoteemo.filehandling.DocumentsManager;
import com.Filoteemo.filehandling.FileInput;
import com.Filoteemo.main.Employee;

public class EmployeeGui extends JFrame implements ActionListener, DocumentsManager{ 
	
		private int framewidth = 700;
		private int frameheight = 600;
		private int panelwidth = 150;
		private int panelheight = 150;
		private int buttonWidth = 120;
		private int buttonHeight = 30;
		
		JMenuBar bar = new JMenuBar(); 
		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");
		JMenuItem create, read, update, delete, close, readAbout;
		JPanel sidebar = new JPanel();
		JPanel content = new JPanel(new BorderLayout());
		JTextArea textarea = new JTextArea(50, 5);
		JScrollPane scrollarea = new JScrollPane(textarea); //scrolling area inside JPanel content which holds information about the textarea
		JLabel sidebarHeader = new JLabel("Employee actions");
		JButton createNew = new JButton("Create new");
		JButton readAll = new JButton("Read all");
		JButton readById = new JButton("Read by ID");
		JButton updateById = new JButton("Update by ID");
		JButton deleteById = new JButton("Delete by ID");
		JButton storeResult = new JButton("Store result");
		JButton readFile = new JButton("Read file");
		JFileChooser jc = new JFileChooser();
		JButton clearScrollPane = new JButton("Clear results");
		String newLine = "\n";
		ArrayList<Employee> employees = new ArrayList<>();
		//Iterator<Employee> itr = employees.iterator(); // iterates through arraylist of employees

		public EmployeeGui() {
			
			//JFrame preferences
			super("Employee");
			setSize(framewidth, frameheight);
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setDefaultLookAndFeelDecorated(true);
			setJMenuBar(bar);
			setLayout(new BorderLayout());

			//menu bar preferences
			bar.add(file);
			bar.add(about);			
			//menu item preferences
			create = new JMenuItem("Create new employee...");
			create.addActionListener(this);
			read = new JMenuItem("Display all employees...");
			read.addActionListener(this);
			update = new JMenuItem("Update employee...");
			update.addActionListener(this);
			delete = new JMenuItem("Delete employee...");
			delete.addActionListener(this);
			close = new JMenuItem("Close");
			close.addActionListener(this);
			readAbout = new JMenuItem("About this program");
			readAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					File newFile = new File("C:\\users\\sindr\\eclipse-workspace\\employeehandler\\src\\main\\java\\com\\Filoteemo\\gui\\readme.txt"); // sets default path for reading spesific textfile
					
					try {
						textarea.append(readFromFile(newFile)); //sends the new file to the readFromFile method which returns a String and then appends the result to the textarea
					}
					catch(Exception error) {
						System.out.println(error);
					}
				}
				
			});
			
			//Add item to menu item
			file.add(create);
			file.add(read);
			file.add(update);
			file.add(delete);
			file.add(close);
			about.add(readAbout);
			
			//JLabel preferences
			sidebarHeader.setForeground(Color.WHITE);
			
			//JPanel preferences 
			sidebar.setBackground(new Color(0x333333));
			sidebar.setPreferredSize(new Dimension(panelwidth, panelheight));
			add(sidebar, BorderLayout.WEST);
			sidebar.add(sidebarHeader);
			sidebar.add(createNew);
			sidebar.add(readAll);
			sidebar.add(readById);
			sidebar.add(updateById);
			sidebar.add(deleteById);
			sidebar.add(storeResult);
			sidebar.add(readFile);
			
			
			// content.setBackground(Color.WHITE); 
			content.setPreferredSize(new Dimension(450, panelheight)); 
			add(content, BorderLayout.EAST);
			content.add(scrollarea, BorderLayout.CENTER);
			content.add(clearScrollPane, BorderLayout.SOUTH);

			//JButton preferences
			createNew.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			createNew.addActionListener(this);
			readAll.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			readAll.addActionListener(this);
			readById.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			readById.addActionListener(this);
			updateById.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			updateById.addActionListener(this);
			deleteById.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			deleteById.addActionListener(this);
			storeResult.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			storeResult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					jc.setFileSelectionMode(JFileChooser.FILES_ONLY); // sets the selection to only files
					jc.setDialogTitle("Specify the file you want to save");
					
					jc.setCurrentDirectory(new File("C:\\users\\sindr\\eclipse-workspace\\OBJ2100\\"));
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", ".text", "txt"); // creates a filter to only accept specified filetypes
					jc.setFileFilter(filter); //sets the filter to JFileChooser
					
					int returnValue = jc.showSaveDialog(null); // creates a returnValue variable which holds an integer
					
					if(returnValue == JFileChooser.APPROVE_OPTION) {// if user chooses to click save do the following
						File fileToSave = jc.getSelectedFile();
						
						try {
							writeToFile(textarea.getText(), fileToSave);
							JOptionPane.showMessageDialog(null, "Save successfull");
						}
						catch(Exception error) {
							System.out.println(error);
						}
					}
				}
			});
			readFile.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			readFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { //Actionlistener for reading files
					
					jc.setFileSelectionMode(JFileChooser.FILES_ONLY); // opens a filechooser
					jc.setDialogTitle("Choose the file you want to read"); 
					
					jc.setCurrentDirectory(new File("C:\\users\\sindr\\eclipse-workspace\\OBJ2100\\")); // specified directory
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt",".text","txt"); 
					jc.setFileFilter(filter);
					
					int returnValue = jc.showOpenDialog(null);
					
					if(returnValue == JFileChooser.APPROVE_OPTION) {
						File fileToRead = jc.getSelectedFile();
					
					try {
						textarea.append(readFromFile(fileToRead));
					}
					catch(Exception error) {
						System.out.println(error);
					}
				  }
				}
			});
			clearScrollPane.addActionListener(this);
			
			//JScrollPane preferences
			scrollarea.setPreferredSize(new Dimension(300,panelheight));
			
		        
			//textarea preferences
			//textarea.setPreferredSize(new Dimension(400, panelheight));
			textarea.setMargin(new Insets(10,10,10,10));
			System.out.println("Application started");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==create || e.getSource()==createNew) {
				 CreateEmployee createEmpForm = new CreateEmployee();
				 createEmpForm.setVisible(true);
			 }
			
			if(e.getSource()==readAll || e.getSource()==read) { 
				EmployeeDao emp = new EmployeeDao(); // creates connection from method constructor
				employees = emp.getEmployees(); // calls the getEmployees method
				Iterator<Employee> itr = employees.iterator(); // iterates through arraylist of employees
				while(itr.hasNext()) { // while there is a next row in the list
					Employee viewAll = itr.next(); //append every row in the list to a Employee object named viewAll
					//append all the information about each employee in the database to the JTextArea variable
					textarea.append("Id: "+viewAll.getId()+newLine+"Name: "+viewAll.getFirstname()+" "+viewAll.getLastname()+newLine+"Email: "+viewAll.getEmail()+newLine+"Department: "+viewAll.getDepartment()+newLine+"Salary: "+viewAll.getSalary()+newLine+newLine);
				}
			}
			
			if(e.getSource()==clearScrollPane) {
				textarea.setText(""); // removes all text from the textarea in the scrollarea
			}
			
			if(e.getSource()==close) {
				dispose();
				System.out.println("Application stopped");
			}
			
			if(e.getSource()==readById) {
				int id = 0;
				textarea.setText(""); // clears the textarea before showing the inputdialog.
				String message = JOptionPane.showInputDialog(null, "Please enter employeeId");
				try {
					id = Integer.parseInt(message);
				}
				catch(Exception error) {
					System.out.println("ID not found "+error);
					JOptionPane.showMessageDialog(null, "The ID entered is not valid");
				}
				EmployeeDao conn = new EmployeeDao(); // establish connection
				employees = conn.getEmployee(id); // call method from EmployeeDao class
				Iterator<Employee> itr = employees.iterator(); // iterates through arraylist of employees
				while(itr.hasNext()) {
					Employee viewById = itr.next();
					textarea.append("Id: "+viewById.getId()+newLine+"Name: "+viewById.getFirstname()+" "+viewById.getLastname()+newLine+"Email: "+viewById.getEmail()+newLine+"Department: "+viewById.getDepartment()+newLine+"Salary: "+viewById.getSalary()+newLine+newLine);
				}
			}
			
			if(e.getSource()==updateById || e.getSource()==update) {
				UpdateEmployee updateEmployeeForm = new UpdateEmployee();
				updateEmployeeForm.setVisible(true);
			}
			
			if(e.getSource()==deleteById || e.getSource()==delete) {
				DeleteEmployee deleteEmployeeForm = new DeleteEmployee();
				deleteEmployeeForm.setVisible(true);
			}
			
		}
		
		@Override
		public void writeToFile(String text, File file) throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    writer.write(text);    
		    writer.close();
		}

		@Override
		public String readFromFile(File file) throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(file)); // creates new buffered reader
			String result = ""; // String variable to hold the result 
			String read = reader.readLine(); // String variable which reads every line as long as there is one
			while(read != null) { // while condition that loops through file as long as there is a line
				result += read+newLine; // appends every line in the file to the result variable and adds a new line
				read = reader.readLine(); // reads the next line in the file
			}
			reader.close(); // closes the reader
			return result; // result is returned as the specified return for this method is String datatype
		}
		
		
}
