package web.controller;

import java.io.IOException;
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
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			listEmployees(request, response);
			
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get employees from db util
		List<Employee> employees = employeeDbUtil.getEmployees();
		
		// add employees to the request
		request.setAttribute("STUDENT_LIST", employees);
		
		// send to JSP page
		request.getRequestDispatcher("/list-employees.jsp").forward(request, response);
	}

}
