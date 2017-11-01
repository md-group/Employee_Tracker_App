package web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import web.db.EmployeeDbUtil;
import web.model.Employee;


@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmployeeDbUtil employeeDbUtil;
	
	@Resource(name="jdbc/web_employee_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read the command parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing employees
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST":
				listEmployees(request, response);
				break;
				
			case "ADD":
				addEmployee(request, response);
				break;
				
			default: 
				listEmployees(request, response);
			}
			
			listEmployees(request, response);
			
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read employee info from form data
		String pass = request.getParameter("pass");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String strDate = request.getParameter("oldEmployee");
		SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = frmt.parse(strDate);
		java.sql.Date oldEmployee = new java.sql.Date(date.getTime());
		
		// create a new employee object
		Employee newEmployee = new Employee(pass, firstName, lastName, age, email, salary, oldEmployee);
		
		// add the employee to the database
		employeeDbUtil.addEmployee(newEmployee);
		
		// send back to main page (the employee list)
		listEmployees(request, response);
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get employees from db util
		List<Employee> employees = employeeDbUtil.getEmployees();
		
		// add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees);
		
		// send to JSP page
		request.getRequestDispatcher("/list-employees.jsp").forward(request, response);
	}

}
