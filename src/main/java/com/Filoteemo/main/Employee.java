package com.Filoteemo.main;

public class Employee {

	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String department;
	private double salary;
	
	public int getId() {
		return id;
	}
	
	public void setId(int empId) {
		this.id = empId;
	}
	
	public String getFirstname() {
		return first_name;
	}
	public void setFirstname(String fname) {
		this.first_name = fname;
	}
	
	public String getLastname() {
		return last_name;
	}
	
	public void setLastname(String lname) {
		this.last_name = lname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String mail) {
		this.email = mail;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String dep) {
		this.department = dep;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double newSalary) {
		this.salary = newSalary;
	}
	
	
}
