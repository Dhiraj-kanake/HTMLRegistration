<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
</head>

<body>

<h1>Hello</h1>
        <p>Welcome
       <%String user=(String)request.getAttribute("name");   %> 
   <% out.println("<b>Welcome " + user + "!</b>");%> 
<br /> 
<a href="login.html"><button>LOGOUT</button></a> 

</body>
</html>