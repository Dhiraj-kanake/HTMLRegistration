package controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class UserServlet extends HttpServlet{
	public void service(ServletRequest req,ServletResponse res) {
	System.out.println(req.getParameter("name"));
	System.out.println(req.getParameter("Email"));
	System.out.println(req.getParameter("number"));
	System.out.println(req.getParameter("password"));
}}
