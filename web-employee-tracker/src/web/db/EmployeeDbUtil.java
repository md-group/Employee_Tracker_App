package web.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import web.model.Employee;

public class EmployeeDbUtil {
	
	private DataSource dataSource;
	
	public EmployeeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Employee> getEmployees() throws Exception {
		
		List<Employee> employees = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// get a connection
			conn = dataSource.getConnection();
			
			// create sql statement
			String sql = "SELECT * FROM employee ORDER BY last_name";
			
			stmt = conn.createStatement();
			
			// execute query
			rs = stmt.executeQuery(sql);
			
			// process result set
			while(rs.next()) {
				
				// retrieve data from result set row
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				int salary = rs.getInt("salary");
				int oldEmployee = rs.getInt("old_employee");
				
				// create new employee object
				Employee tempEmployee = new Employee(id, pass, firstName, lastName, age, email, salary, oldEmployee);
				
				// add it to the list of employees
				employees.add(tempEmployee);
			}
			
			return employees;
		} finally {
			// close JDBC objects
			close(conn, stmt, rs);
		}
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			
			if(rs != null) {
				rs.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			if (conn != null) {
				conn.close(); 
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
