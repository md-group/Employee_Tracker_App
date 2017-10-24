package web.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		if((userName.equals("admin")) && (password.equals("admin"))) {
			request.setAttribute("userName", userName);
			response.sendRedirect("EmployeeControllerServlet");
		}else {
			request.setAttribute("errMessage", "Incorrect user and/or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
