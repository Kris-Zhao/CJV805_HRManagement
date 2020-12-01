package ca.myseneca.model;

import java.io.Serializable;

/**
 * Employee is a Javabean class.
 * @author 
 *
 */
public class Employee implements Serializable {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private java.sql.Date hire_date;
	private String job_id;
	private double salary;
	private double commission_pct;
	private int manager_id;
	private int department_id;
	
	//constructor with no argument
	public Employee() {
		
	}
	
	//constructor with arguments
	public Employee(int employee_id, String first_name, String last_name, String email, String phone_number, java.sql.Date hire_date, String job_id, double salary, double commission_pct, int manager_id, int department_id) {
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.hire_date = hire_date;
		this.job_id = job_id;
		this.salary = salary;
		this.commission_pct = commission_pct;
		this.manager_id =manager_id;
		this.department_id = department_id;
	}
	
	//getter methods
	public int getEmployeeId() {
		return this.employee_id;
	}
	public String getFirstName() {
		return this.first_name;
	}
	public String getLastName() {
		return this.last_name;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPhoneNum() {
		return this.phone_number;
	}
	public java.sql.Date getHireDate() {
		return this.hire_date;
	}
	public String getJobId() {
		return this.job_id;
	}
	public double getSalary() {
		return this.salary;
	}
	public double getCommPct() {
		return this.commission_pct;
	}
	public int getManagerId() {
		return this.manager_id;
	}
	public int getDepartmentId() {
		return this.department_id;
	}
	
	//setter methods
	public void setEmployeeId(int employee_id) {
		this.employee_id = employee_id;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNum(String phone_number) {
		this.phone_number = phone_number;
	}
	public void setHireDate(java.sql.Date hire_date) {
		this.hire_date = hire_date;
	}
	public void setJobId(String job_id) {
		this.job_id = job_id;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setCommPct(double commission_pct) {
		this.commission_pct = commission_pct;
	}
	public void setManagerId(int manager_id) {
		this.manager_id = manager_id;
	}
	public void setDepartmentId(int department_id) {
		this.department_id = department_id;
	}
	
	//display
	public void display() {
		System.out.println("employee_id: "+this.employee_id+"\nFirst name: "+this.first_name+"\nLast name: "+this.last_name+"\nEmail: "+this.email+"\nPhone number: "+this.phone_number+"\nHire data: "+(this.hire_date).toString()+
				"\nJob Id: "+this.job_id+"\nSalary: "+this.salary+"\nCommission_pct: "+this.commission_pct+"\nManager Id: "+this.manager_id+"\nDepartment Id: "+this.department_id);
	}
	
}
