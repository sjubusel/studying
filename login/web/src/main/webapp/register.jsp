<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 24.04.2020
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>
<p>A registration form:</p>
<form action="${pageContext.request.contextPath}/register">
    <p style="width: 100px"><%= String.format("%18s", "Login:")%>
    </p>
    <label>
        Login:<br/>
        <input name="login" type="text">
    </label>
    <label>
        Password:<br/>
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>
</body>
</html>
