package com.Filoteemo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.Filoteemo.main.Employee;

public class EmployeeDao {

	Connection connect = null;
	
	//ArrayList<Employee> employees;
	
	public EmployeeDao() { // db connection constructor
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String username = "root";
		String password = "EayfruRm";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(dbUrl, username, password);
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Employee> getEmployees(){ // gets all the employees from the employeetable
		
		ArrayList<Employee> employees = new ArrayList<>();
		String sql = "select * from employees";
		
		try {
			Statement st = connect.createStatement(); // creates statement
			ResultSet rs = st.executeQuery(sql); // creates resultset to retrieve rows from query
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt(1));
				e.setLastname(rs.getString(2));
				e.setFirstname(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setDepartment(rs.getString(5));
				e.setSalary(rs.getDouble(6));
				
				employees.add(e);
			}
		}
		catch(Exception e) {
			System.out.println(e);		}
		return employees;
	}
	
	public ArrayList<Employee> getEmployee(int id) { // metoden henter en rad fra tabellen Employees basert på gitt id
		ArrayList<Employee> employee = new ArrayList<>();
		Employee emp = new Employee();
		String sql = "select * from employees where id="+id;
		
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "Could not find the given ID");
			}
			else {
			
			    do{
				emp.setId(rs.getInt(1));
				emp.setLastname(rs.getString(2));
				emp.setFirstname(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setDepartment(rs.getString(5));
				emp.setSalary(rs.getDouble(6));
				employee.add(emp);
			}
			    while(rs.next());
		}
	}
		catch(Exception e) {
			System.out.println(e);
		}
		return employee;
	}
	
	public void createNewEmployee(Employee e) { // takes an object of employee as an argument
		
		String sql = "insert into employees (last_name, first_name, email, department, salary) values (?,?,?,?,?)"; // sql query
		
		try {
			PreparedStatement st = connect.prepareStatement(sql); // connects the sql query to the prepared statement
			st.setString(1, e.getLastname());
			st.setString(2, e.getFirstname());
			st.setString(3, e.getEmail());
			st.setString(4, e.getDepartment());
			st.setDouble(5, e.getSalary());
			st.executeUpdate(); // executes statement
		}
		
		catch(Exception error) {
			System.out.println(error);
		}
		
		System.out.println("Firstname: "+e.getFirstname());
		System.out.println("Lastname: "+e.getLastname());
		System.out.println("Email: "+e.getEmail());
		System.out.println("Department: "+e.getDepartment());
		System.out.println("Salary: "+e.getSalary());
	}
	
	public void updateEmployee(Employee e) {
		
		String sql = "update employees set last_name=?, first_name=?, email=?, department=?, salary=? where id=?";
		
		try {
			PreparedStatement st = connect.prepareStatement(sql);
			st.setString(1, e.getLastname());
			st.setString(2, e.getFirstname());
			st.setString(3, e.getEmail());
			st.setString(4, e.getDepartment());
			st.setDouble(5, e.getSalary());
			st.setInt(6, e.getId());
			st.executeUpdate();
			System.out.println("Update succeed");
		}
		catch(Exception error) {
			System.out.println("No valid ID entered"+error);
		}
		
	}

	
	public void deleteEmployee(int id) {
		String sql = "delete from employees where id=?";
		
		try {
			PreparedStatement st = connect.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			System.out.println("Delete succeed");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
}
