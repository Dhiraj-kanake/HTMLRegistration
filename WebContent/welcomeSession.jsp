<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>

<body>

   <h1><%out.println(request.getAttribute("name"));%></h1>
   <br />
  <a href="logout.jsp" ><b>LOGOUT</b></a>

</body>

</html>