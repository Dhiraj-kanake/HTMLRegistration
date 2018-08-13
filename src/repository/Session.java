package repository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Session
 */
@SuppressWarnings("serial")
@WebServlet("/Session")
public class Session extends HttpServlet{
	public String email = null, password = null;
	public HttpSession session;
	public PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		session = request.getSession(false);
		try {
					session = request.getSession();
					User user2 = (User) session.getAttribute("user");
					request.setAttribute("name", user2.getName());
					RequestDispatcher req = request.getRequestDispatcher("welcomeSession.jsp");
					req.forward(request, response);
		} catch (Exception ex) {
			//ex.printStackTrace();
			 response.sendRedirect("login.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-dgenerated method stub
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		response.setContentType("text/html");
		email = request.getParameter("email");
		password = request.getParameter("password");
		User user = new User();
		out = response.getWriter();
	
		try {
			String DBURL = "jdbc:mysql://" + System.getenv("DBHOST") + "/db100035?user=" + System.getenv("DBUSER")
			      + "&password=" + System.getenv("DBPASSWORD");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DBURL);
			preparedStatement = connect.prepareStatement(
			      "SELECT name,email, passward,number FROM loginCredential WHERE  EMAIL=? and PASSWARD=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String Name = resultSet.getString("name");
				String Email = resultSet.getString("email");
				String number = resultSet.getString("number");
				user.setName(Name);
				user.setEmail(Email);
				user.setNumber(number);
				// RequestDispatcher req=request.getRequestDispatcher("home.html");
				HttpSession session = request.getSession();
				
					session.setAttribute("user", user);
				 request.setAttribute("name",Name);
					RequestDispatcher req = request.getRequestDispatcher("welcomeSession.jsp");
					req.forward(request, response);
			
			} else {
				System.out.println("wrong credential Please try again");

				
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e);
		}
	}
}
