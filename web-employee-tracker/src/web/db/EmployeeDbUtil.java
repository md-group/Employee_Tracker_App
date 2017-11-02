package web.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
				Date oldEmployee = rs.getDate("old_employee");
				
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

	public void addEmployee(Employee newEmployee) throws Exception{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get a connection
			conn = dataSource.getConnection();
			
			// create sql statement for insert
			String sql = "insert into employee "
					+ "(pass, first_name, last_name, age, email, salary, old_employee)"
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			
			// set the param values for the employee
			stmt.setString(1, newEmployee.getPass());
			stmt.setString(2, newEmployee.getFirstName());
			stmt.setString(3, newEmployee.getLastName());
			stmt.setInt(4, newEmployee.getAge());
			stmt.setString(5, newEmployee.getEmail());
			stmt.setInt(6, newEmployee.getSalary());
			stmt.setDate(7, newEmployee.getOldEmployee());
			
			// execute sql insert
			stmt.execute();
			
		}finally {
			// clean up JDBS objects
			close(conn, stmt, null);
		}
		
	}

	public Employee getEmployee(String employeeId) throws Exception {
		
		Employee employee = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int employeeDbId;
		
		try {
			// convert employee id to int
			employeeDbId = Integer.parseInt(employeeId);
			
			// get a connection
			conn = dataSource.getConnection();
			
			// create sql query to get selected employee
			String sql = "select * from employee where id=?";
			
			// create prepared statement
			stmt = conn.prepareStatement(sql);
			
			// set params
			stmt.setInt(1, employeeDbId);
			
			// execute query
			rs = stmt.executeQuery();
			
			// retrieve data from result set row
			if(rs.next()) {
				String pass = rs.getString("pass");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				int salary = rs.getInt("salary");
				Date oldEmployee = rs.getDate("old_employee");
				
				// use the employeeDbId during construction
				employee = new Employee(employeeDbId, pass, firstName, lastName, age, email, salary, oldEmployee);
			}else {
				throw new Exception("Couldn't find employee id: " + employeeDbId);
			}
			
			return employee;
			
		}finally {
			// clean up JDBC objects
			close(conn, stmt, rs);
		}
	}

	public void updateEmployee(Employee employee) throws Exception{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get a connection
			conn = dataSource.getConnection();
			
			// create sql update statement
			String sql = "update employee "
					+ "set pass=?, first_name=?, last_name=?, age=?, email=?, salary=?, old_employee=? "
					+ "where id=?";
			
			// prepare statement
			stmt = conn.prepareStatement(sql);
			
			// set params
			stmt.setString(1, employee.getPass());
			stmt.setString(2, employee.getFirstName());
			stmt.setString(3, employee.getLastName());
			stmt.setInt(4, employee.getAge());
			stmt.setString(5, employee.getEmail());
			stmt.setInt(6, employee.getSalary());
			stmt.setDate(7, employee.getOldEmployee());
			stmt.setInt(8, employee.getId());
			
			// execute query
			stmt.execute();
			
		}finally {
			// clean JDBC objects
			close(conn, stmt, null);
		}
	}

}
