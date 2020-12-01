package ca.myseneca.test;

import java.util.ArrayList;
import java.util.Scanner;
import ca.myseneca.model.*;
/**
 * HRManagement class is a test class that mimics an application. 
 * @author 
 *
 */
public class HRManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("----------------HR representatives Log in----------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your USERNAME: ");
		String username = input.nextLine();
		System.out.println("Please enter your PASSWORD: ");
		String password = input.nextLine();
		System.out.println();
		
		//test public static int getEmployeeID (String user, String password) method
		int empID = DAManager.getEmployeeID(username, password);
		
		if (empID == 0) {
			System.out.println("You are an unauthorized user.");
			System.exit(0);
		}else {
			System.out.println("You have passed the credential check.\n");
			System.out.println("Your information is : ");
			//test public static Employee getEmployeeByID(int empid) method 
			Employee HR_emp = DAManager.getEmployeeByID(empID);
			HR_emp.display();
			
			//test public static void addEmployee (Employe emp) method
			System.out.println("\nAdd one employee inte EMPLOYEE table");
		    Employee added_emp = new Employee(207, "Yuhang", "Zhao", "yzhao248@myseneca.ca", "111-222-3333", java.sql.Date.valueOf("2020-09-01"), "IT_PROG", 30000, 0.0,102,60);
			DAManager.addEmployee(added_emp);
			
			//test public static ArrayList<Employe> getAllEmployees() method
			System.out.println("\nGet an ArrayList including all employee info");
			ArrayList<Employee> empList = DAManager.getAllEmployees();
			System.out.println("The 1st employee info within this arrayList is:");
			(empList.get(0)).display();
			System.out.println("The number of employees in EMPLOYEES table is: "+ empList.size());
			
			//test public static ArrayList<Employe> getEmployeesByDepartmentID (int depid) method 
			System.out.println("\nGet an ArrayList including all employee info in the department_ID 60: ");
			ArrayList<Employee> empListByDep = DAManager.getEmployeesByDepartmentID(60);
			System.out.println("The 1st employee info within this arrayList is:");
			(empListByDep.get(0)).display();
			System.out.println("The number of employees in deaprtment ID 60 is: "+empListByDep.size());
			
			//test public static int updateEmployee (Employee emp) method
			System.out.println("\nUpdate the employee#99 info");
			Employee updated_emp = new Employee(207, "James", "Bob", "1111@gmail.com", "123-345-1111", java.sql.Date.valueOf("2019-11-01"), "FI_ACCOUNT", 24000, 0.0, 108, 100);
			DAManager.updateEmployee(updated_emp);
			
			
			
			//test public static boolean batchUpdate(String[] SQLs) method
			System.out.println("\nTest batchUpdate method: ");
			String batchSQL1 = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, hire_date, job_id) " + 
					"VALUES(208, 'Lebron', 'James', 'aaa@aaa.com', '111-111-2222', TO_DATE('01-Jan-2017', 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American'), 'IT_PROG')";
			String batchSQL2 = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, hire_date, job_id) " + 
					"VALUES(209, 'Kevin', 'Durant', 'bbb@aaa.com', '111-222-3333', SYSDATE, 'AD_VP')";
			String batchSQL3 = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, hire_date, job_id) " + 
					"VALUES(210, 'Kris', 'Smith', 'ccc@aaa.com', '111-333-4444', SYSDATE, 'ST_MAN')";
			String[] SQLs = new String[3];
			SQLs[0] = batchSQL1;
			SQLs[1] = batchSQL2;
			SQLs[2] = batchSQL3;
			if(DAManager.batchUpdate(SQLs)) {
				System.out.println("Success. This transaction is successfully executed!");
			}else{
				System.out.println("Failed. All SQL statements are rolled back.");
			};
			
			//test public static int deleteEmployeeByID (int empid) method
			System.out.println("\nDelete the employee#210");
			DAManager.deleteEmployeeByID(210);
		}

	}

}
