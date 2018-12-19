<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>servletWeb project</title>
</head>

<body>
<jsp:forward page="/servlet/ControllerServlet">
  <jsp:param value="showAllCustomers" name="op"/>
</jsp:forward>
</body>
</html>