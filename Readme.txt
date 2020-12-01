Assignment1 Submission Form
================================================================================
I/we declare that the attached assignment is my/our own work in accordance with Seneca Academic Policy. No
part of this assignment has been copied manually or electronically from any other source (including web sites) or
distributed to other students.

Name(s): Yuhang Zhao(150467199)

--------------------------------------------------------------------------
The database.properties stores my Oracle database information, including driver, username and password. 

In support folder:
CreateSecurityTable.sql is to create SECURITY table in the Oracle database.
For P_SECURITY package, there are one F_SECURITY function and another P_EMP_INFO procedure within it. Firstly I create a package specification and 
then create a corresponding package body. 
The F_SECURITY function defined two IN parameters and return a number. I also define another employeeID 
variable, then using select statement to query the corresponding EMPLOYEE_ID by ID and PASSWORD provided by user. If this HR account doesn't exist,
return 0; otherwise, return employeeID.
The P_EMP_INFO procedure defines one IN parameter "P_EMPLOYEEID" and another OUT REF_CURSOR "p_info". Based on the employeeID, OUT the REF_CURSOR "p_info".

In ca.myseneca.model package:  
DAUtilities class provides the functionalities of loading the properties file, setting up JDBC connection to your Oracle database on Zenit(THIN driver or OCI driver), 
retrieve and print SQLWarning, SQLException, SQLWarnings, and BatchUpdateException, also closing connection. This application uses THIN driver in default.

Employee class is a JavaBean class which is corresponding to Employees table in your Oracle database.
 
DAManager class is a data access manager class provides the interface that is used to manipulate data in the database without exposing details of the database.
1.getEmployeeID method is to call P_SECURITY.F_SECURITY function to get the emp_id if the user is authorized, otherwise would return 0.
2.addEmployee method is to add one employee into EMPLOYEES table using PreparedStatement.
3.getAllEmployees method would return an ArrayList<Employee> empArrList using Statement.
4.getEmployeesByDepartmentID method would also return an ArrayList<Employee> empArrList based on deaprtmentID using PreparedStatement.
5.getEmployeeByID is to get the info of one specific employee. Here using REF-CURSOR to get the whole row's info for that employee using OracleCallableStatement.
6.updateEmployee is update one existing employee using PreparedStatement.
7.deleteEmployeeByID is to delete an employee based on empid using CallableStatement.
8.batchUpdate method is to execute batch processing for an array of String SQL statements.

In ca.myseneca.test package:
The HRManagement class is a test class that mimics an application. Prompt user input a username and a password from the console. If pass the credential check, 
show the user’s info as employee, then execute methods on DAManager class one by one.
Finally, there would be three other employees inserted into EMPLOYEES table. 
-------------------------------------------------------------------------- 

