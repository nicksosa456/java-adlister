<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nicholassosa
  Date: 2019-08-21
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%  String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username.equalsIgnoreCase("admin") && password.equals("password")) {
        response.sendRedirect("/profile.jsp");
    }
%>



<html>
<head>
    <title>login</title>
</head>
<body>

<form method="POST" action="login.jsp">

    <label for="username">Username</label>
    <input id="username" name="username" type="text">
    <br>
    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <br>
    <input id="submit" type="submit">
</form>

</body>
</html>
