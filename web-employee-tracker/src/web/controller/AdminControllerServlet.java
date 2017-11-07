package web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import web.db.EmployeeDbUtil;
import web.model.Employee;


@WebServlet("/AdminControllerServlet")
public class AdminControllerServlet extends HttpServlet {
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getAttribute("DATA").equals("DATA")){
			try {
				listEmployee(req, resp);
			} catch (Exception e) {
				throw new ServletException(e);
			}
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
				
			case "LOAD":
				loadEmployee(request, response);
				break;
				
			case "UPDATE":
				updateEmployee(request, response);
				break;
				
			case "DELETE":
				deleteEmployee(request, response);
				break;
				
			default: 
				listEmployees(request, response);
			}
			
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read email employee from "LoginServlet"
		String email = (String)request.getAttribute("email");
		
		// get unique employee from db util
		List<Employee> uniqueEmployee = employeeDbUtil.getUniqueEmployee(email);
		
		// add unique employee to the request
		request.setAttribute("UNIQUE_EMPLOYEE", uniqueEmployee);
		
		// send to JSP page
		request.getRequestDispatcher("/list-employee.jsp").forward(request, response);
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read id employee from form data
		String employeeId = request.getParameter("employeeId");
		
		// delete employee from database
		employeeDbUtil.deleteEmployee(employeeId);
		
		// send them back to "list employees" page
		listEmployees(request, response);
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// read employee info from form data
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
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
		
		// create a new Employee object
		Employee employee = new Employee(employeeId, pass, firstName, lastName, age, email, salary, oldEmployee);
		
		// perform update on database
		employeeDbUtil.updateEmployee(employee);
		
		// send them back to the "list employees" page
		listEmployees(request, response);
	}

	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read employee id from form data
		String employeeId = request.getParameter("employeeId");
		
		// get employee from database
		Employee employee = employeeDbUtil.getEmployee(employeeId);
		
		// place employee in the request attribute
		request.setAttribute("EMPLOYEE", employee);
		
		//send to jsp page "update-employee-form.jsp"
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
		dispatcher.forward(request, response);
		
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
