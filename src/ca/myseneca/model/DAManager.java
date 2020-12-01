package ca.myseneca.model;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

/**
 * The DAManager.java is a data access manager class provides the interface that 
 * is used to manipulate data in the database without exposing details of the database. 
 * @author  
 *
 */
public class DAManager {

	/**
	 * Using thin driver to build the connection. Using CallableStatement to call the
	 * PL/SQL function F_SECURITY in the P_SECURITY package. If the user is valid, return
	 * empID, otherwise return 0.(CallableStatement)
	 * get 
	 * @param user
	 * @param password
	 * @return empID
	 */
	public static int getEmployeeID (String user, String password) {
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		String getEmployeeID = "{? = call P_SECURITY.F_SECURITY(?,?)}";
		
		try {
			dbConnection = DBUtilities.getConnection();
			
			callableStatement = dbConnection.prepareCall(getEmployeeID);
			callableStatement.setString(2, user);
			callableStatement.setString(3, password);
			callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			
			callableStatement.execute();
			
			int employeeId = callableStatement.getInt(1);
			
			if(employeeId == 0) {
				return 0;
			}else {
				return employeeId;
			}
			
			
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }
		catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch(IOException e) {
	    	
	    }
		finally {
			DBUtilities.closeConnectionAndStat(dbConnection, callableStatement);
	    }
		
		return -1;
	}
	
	//The below methods of CRUD (create, read, update, and delete) operations for the Employees tables. 
	/**
	 * Add one employee into EMPLOYEES table.(PreparedStatement)
	 * @param emp
	 */
	public static void addEmployee (Employee emp) {
		Connection dbConnection = null;
		PreparedStatement preStmt = null;
		
		String sql = "INSERT INTO EMPLOYEES VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			dbConnection = DBUtilities.getConnection();
			preStmt = dbConnection.prepareStatement(sql);
			preStmt.setInt(1, emp.getEmployeeId());
			preStmt.setString(2, emp.getFirstName());
			preStmt.setString(3, emp.getLastName());
			preStmt.setString(4, emp.getEmail());
			preStmt.setString(5, emp.getPhoneNum());
			preStmt.setDate(6, emp.getHireDate());
			preStmt.setString(7, emp.getJobId());
			preStmt.setDouble(8, emp.getSalary());
			preStmt.setDouble(9, emp.getCommPct());
			preStmt.setInt(10, emp.getManagerId());
			preStmt.setInt(11, emp.getDepartmentId());
			preStmt.executeUpdate();
			
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (IOException e) {
	    	
	    }
		finally {
			DBUtilities.closeConnectionAndStat(dbConnection, preStmt);
	    }
	}
	
	/**
	 * get an ArrayList consisting of all employees' information(Static SQL)
	 * @return ArrayList<Employee>
	 */
	public static ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> empArrList = new ArrayList<Employee>();
		Connection dbConnection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM EMPLOYEES";
		try {
			dbConnection = DBUtilities.getConnection();
			stmt = dbConnection.createStatement();
			resultSet = stmt.executeQuery(sql);
			while(resultSet.next()) {
				int employee_id = resultSet.getInt(1);
				String first_name = resultSet.getString(2);
				String last_name= resultSet.getString(3);
				String email= resultSet.getString(4);
				String phone_number= resultSet.getString(5);
				java.sql.Date hire_date= resultSet.getDate(6);
				String job_id= resultSet.getString(7);
				double salary = resultSet.getDouble(8);
				double commission_pct = resultSet.getDouble(9);
				int manager_id = resultSet.getInt(10);
				int department_id = resultSet.getInt(11);
				Employee emp = new Employee(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
				empArrList.add(emp);
			}
			return empArrList;
			
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (IOException e) {
	    	
	    }finally {
	    	DBUtilities.closeConnectionAndStatAndRst(dbConnection, stmt, resultSet);
	    }
		return null;
	}
	
	/**
	 * Based on the given department_ID, return an ArrayList consisting of all 
	 * employees' information within the given department. (PreparedStatement)
	 * @param depid
	 * @return ArrayList<Employee>
	 */
	public static ArrayList<Employee> getEmployeesByDepartmentID (int depid){
		ArrayList<Employee> empArrList = new ArrayList<Employee>();
		Connection dbConnection = null;
		PreparedStatement preStmt = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID = ?";
		try {
			dbConnection = DBUtilities.getConnection();
			preStmt = dbConnection.prepareStatement(sql);
			preStmt.setInt(1, depid);
			resultSet = preStmt.executeQuery();
			while(resultSet.next()) {
				int employee_id = resultSet.getInt(1);
				String first_name = resultSet.getString(2);
				String last_name= resultSet.getString(3);
				String email= resultSet.getString(4);
				String phone_number= resultSet.getString(5);
				java.sql.Date hire_date= resultSet.getDate(6);
				String job_id= resultSet.getString(7);
				double salary = resultSet.getDouble(8);
				double commission_pct = resultSet.getDouble(9);
				int manager_id = resultSet.getInt(10);
				int department_id = resultSet.getInt(11);
				Employee emp = new Employee(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
				empArrList.add(emp);
			}
			return empArrList;
			
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (IOException e) {
	    	
	    }finally {
	    	DBUtilities.closeConnectionAndStatAndRst(dbConnection, preStmt, resultSet);
			
	    }
		return null;
	}
	
	/**
	 * get an Employee object based on Employee_ID(OracleCallableStatement--REF_CURSOR)
	 * @param empid
	 * @return an Employee object
	 */
	public static Employee getEmployeeByID(int empid) {
		Connection dbConnection = null;
		OracleCallableStatement oracleCallableStatement = null;
		OracleResultSet orset = null;
		
		String getEMPLOYEEByEmployeeIdSql = "{call P_SECURITY.P_EMP_INFO(?,?)}";
		
		try {
			dbConnection = DBUtilities.getConnection();
			oracleCallableStatement = (OracleCallableStatement)dbConnection.prepareCall(getEMPLOYEEByEmployeeIdSql);
			
			oracleCallableStatement.setInt(1, empid);
			oracleCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			oracleCallableStatement.execute();
			
			orset = (OracleResultSet) oracleCallableStatement.getCursor(2); 
			
			while(orset.next()) {
				int employee_id = orset.getInt(1);
				String first_name = orset.getString(2);
				String last_name= orset.getString(3);
				String email= orset.getString(4);
				String phone_number= orset.getString(5);
				java.sql.Date hire_date= orset.getDate(6);
				String job_id= orset.getString(7);
				double salary = orset.getDouble(8);
				double commission_pct = orset.getDouble(9);
				int manager_id = orset.getInt(10);
				int department_id = orset.getInt(11);
				Employee emp = new Employee(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
				return emp;
			}
			
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (IOException e) {
	    	
	    }finally {
	    	DBUtilities.closeConnectionAndStatAndRst(dbConnection, oracleCallableStatement, orset);
	    }
		return null;
	}
	
	/**
	 * update one employee info based on the given Employee object(PreparedStatement)
	 * @param emp
	 */
	public static void updateEmployee (Employee emp) {
		int employee_id = emp.getEmployeeId();
		String first_name = emp.getFirstName();
		String last_name= emp.getLastName();
		String email= emp.getEmail();
		String phone_number= emp.getPhoneNum();
		java.sql.Date hire_date= emp.getHireDate();
		String job_id= emp.getJobId();
		double salary = emp.getSalary();
		double commission_pct = emp.getCommPct();
		int manager_id = emp.getManagerId();
		int department_id = emp.getDepartmentId();
		
		Connection dbConnection = null;
		PreparedStatement pstmt = null;
		
		try {
			dbConnection = DBUtilities.getConnection();
			String sql = "UPDATE EMPLOYEES SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE_NUMBER=?, HIRE_DATE=?, JOB_ID=?, SALARY=?, "
					+ "COMMISSION_PCT=?, MANAGER_ID=?, DEPARTMENT_ID=? WHERE EMPLOYEE_ID=?";
			pstmt = dbConnection.prepareStatement(sql);
			
			
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			pstmt.setString(3, email);
			pstmt.setString(4, phone_number);
			pstmt.setDate(5, hire_date);
			pstmt.setString(6, job_id);
			pstmt.setDouble(7, salary);
			pstmt.setDouble(8, commission_pct);
			pstmt.setInt(9, manager_id);
			pstmt.setInt(10, department_id);
			pstmt.setInt(11, employee_id);
			// Execute the update statement
			pstmt.executeUpdate();
			
			System.out.println("Employee Id "+emp.getEmployeeId()+" has been updated in EMPLOYEES table");
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (IOException e) {
	    	
	    }finally {
	    	DBUtilities.closeConnectionAndStat(dbConnection, pstmt);
	    }
	}
	
	/**
	 * Delete one Employee in EMPLOYEES table based on his/her employee_id
	 * @param empid
	 */
	public static void deleteEmployeeByID (int empid) {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		try {
			dbConnection = DBUtilities.getConnection();
			callableStatement = dbConnection.prepareCall("{call DELETEEMPLOYEEBYID(?)}");
			callableStatement.setInt(1, empid);
			callableStatement.executeUpdate();
			System.out.println("Employee ID "+empid+" has been removed from EMPLOYEES table.");
		}catch (SQLException e) {
		      DBUtilities.printSQLException(e);
	    }catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (IOException e) {
	    	
	    }finally {
	    	DBUtilities.closeConnectionAndStat(dbConnection, callableStatement);
	    }
	}
	
	/**
	 * BatchUpdating a set of SQL statements
	 * @param String[] SQLs
	 * @return boolean
	 */
	public static boolean batchUpdate(String[] SQLs) {
		Connection dbConnection = null;
		Statement stmt = null; 
		System.out.println("These SQL statemetns are considered as one transcation.");
		try {
			dbConnection = DBUtilities.getConnection();
			stmt = dbConnection.createStatement();
			dbConnection.setAutoCommit(false);
			
			for(int i=0;i<SQLs.length;i++) {
				stmt.addBatch(SQLs[i]);
			}
			
			int[] count = stmt.executeBatch();
			if(count.length == SQLs.length) {
				dbConnection.commit();
				return true;}
			else {
				dbConnection.rollback();
				return false;}
			
		}catch (BatchUpdateException e) {
			DBUtilities.printBatchUpdateException(e);
		}catch (ClassNotFoundException e) {
	    	System.err.println("Failed to load JDBC/ODBC driver.");
	    }catch (SQLException e) {
		    DBUtilities.printSQLException(e);
	    }catch (IOException e) {
	    	
	    }finally {
	    	DBUtilities.closeConnectionAndStat(dbConnection, stmt);
	    }
		return false;
	}
	
}


