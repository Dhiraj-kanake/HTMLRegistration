package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-dgenerated method stub
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("Name");
		String email=request.getParameter("Email");
		String number=request.getParameter("Number");
		String password=request.getParameter("Password");
		
		try{
				String DBURL = "jdbc:mysql://" + System.getenv("DBHOST") + "/db100035?user=" + System.getenv("DBUSER")
				      + "&password=" + System.getenv("DBPASSWORD");
				Class.forName("com.mysql.cj.jdbc.Driver"); 
				connect = DriverManager.getConnection(DBURL); 
				preparedStatement = connect.prepareStatement("INSERT INTO db100035.loginCredential (NAME,EMAIL,NUMBER,PASSWARD) VALUES ( ?, ?, ?,?)");
          preparedStatement.setString(1, name);
          preparedStatement.setString(2, email);
          preparedStatement.setString(3, number);
          preparedStatement.setString(4, password);
          preparedStatement.executeUpdate();
          
          RequestDispatcher req=request.getRequestDispatcher("login.html");
          req.forward(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			out.println(e);
		}
	}
}
