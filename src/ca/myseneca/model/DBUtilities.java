package ca.myseneca.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Properties;

/**
 * This utilities class provides the functionalities of loading the properties file,
 * setting up JDBC connection to your Oracle database on Zenit, retrieveSQLException, 
 * SQLWarnings, and BatchUpdateException. At the same time, The class has the ability 
 * to connect to your Oracle database use both Thin and OCI drivers. 
 */
public class DBUtilities {
	
	public static void printWarnings(SQLWarning warning) throws SQLException {
		if (warning != null) {
			System.out.println("\n---Warnings---\n");
			while (warning != null) {
				System.out.println("Message: " + warning.getMessage());
				System.out.println("SQLState: " + warning.getSQLState());
				System.out.print("Vendor error code: ");
				System.out.println(warning.getErrorCode());
				System.out.println("");
				warning = warning.getNextWarning();
			}
		}
	}
	
	public static void printBatchUpdateException(BatchUpdateException b) {
		System.err.println("----BatchUpdateException----");
		System.err.println("SQLState:  " + b.getSQLState());
		System.err.println("Message:  " + b.getMessage());
		System.err.println("Vendor:  " + b.getErrorCode());
		System.err.print("Update counts:  ");
		int[] updateCounts = b.getUpdateCounts();
		for (int i = 0; i < updateCounts.length; i++) {
			System.err.print(updateCounts[i] + "   ");
		}
	}
	
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException)e).getSQLState());
				System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
		Properties dbProps = new Properties();
		FileInputStream fis = new FileInputStream("database.properties");
		dbProps.load(fis);
		
		String driver = dbProps.getProperty("ORACLE_DB_DRIVER");
		
		//thin driver
		String ORACLE_DB_THIN_DRIVER_URL = dbProps.getProperty("ORACLE_DB_THIN_DRIVER_CONNECT_DESCRIPTION_URL");
		//oci driver
		String ORACLE_DB_OCI_DRIVER_URL = dbProps.getProperty("ORACLE_DB_OCI_DRIVER_CONNECT_DESCRIPTION_URL");
		
		Class.forName(driver);
		//For this application, I use thin driver to connect to the Oracle database. You can also configure using OCI driver.
		Connection conn = DriverManager.getConnection(ORACLE_DB_THIN_DRIVER_URL, dbProps.getProperty("ORACLE_DB_USERNAME"), dbProps.getProperty("ORACLE_DB_PASSWORD"));
		System.out.println("Connected to database");
		return conn;
	}
	
	public static void closeConnectionAndStat(Connection connArg, Statement stmt) {
		System.out.println("Releasing all open resources ...");
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		}catch(SQLException sqle) {
			DBUtilities.printSQLException(sqle);
		}
	}
	
	public static void closeConnectionAndStatAndRst(Connection connArg, Statement stmt, ResultSet rst) {
		System.out.println("Releasing all open resources ...");
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
			if (rst != null) {
				rst.close();
			}
		}catch(SQLException sqle) {
			DBUtilities.printSQLException(sqle);
		}
	}
}
