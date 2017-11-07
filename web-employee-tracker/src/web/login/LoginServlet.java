package web.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import web.db.EmployeeDbUtil;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get user and pass from "login.jsp"
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			if((employeeDbUtil.getEmail(email).equals("admin@email.com") && (employeeDbUtil.getPass(password).equals("admin")))) {
				response.sendRedirect("AdminControllerServlet");
			}else if ((employeeDbUtil.getEmail(email) != null) && (employeeDbUtil.getPass(password) != null)) {
				request.setAttribute("DATA", "DATA");
				request.setAttribute("email", email);
				RequestDispatcher dispatcher = request.getRequestDispatcher("AdminControllerServlet");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("errMessage", "Incorrect user and/or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
