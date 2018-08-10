package repository;

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

/**
 * Servlet implementation class UserRepository
 */
@WebServlet("/UserRepository")
public class UserRepository extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-dgenerated method stub
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		
		String email=request.getParameter("email");
		String password=request.getParameter("Password");
		
		try{
				String DBURL = "jdbc:mysql://" + System.getenv("DBHOST") + "/db100035?user=" + System.getenv("DBUSER")
				      + "&password=" + System.getenv("DBPASSWORD");
				Class.forName("com.mysql.cj.jdbc.Driver"); 
				connect = DriverManager.getConnection(DBURL); 
				preparedStatement = connect.prepareStatement("SELECT email, passward FROM db100035.loginCredential WHERE  EMAIL=? and PASSWARD=?");
          preparedStatement.setString(1, email);
          preparedStatement.setString(2, password);


          resultSet = preparedStatement.executeQuery();
          if(resultSet.next())
          {
         	 RequestDispatcher req=request.getRequestDispatcher("home.html");
             req.forward(request, response); 
          }
          else{
         	 System.out.println("wrong credential Please try again");
         	 RequestDispatcher req=request.getRequestDispatcher("login.html");
             req.forward(request, response); 
          }
		}
		catch (Exception e)
		{
			e.printStackTrace();
			out.println(e);
		}
	}

}
