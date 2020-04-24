<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 24.04.2020
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<html>
<head>
    <title><fmt:message key="registerJsp.title" var="${text}"/></title>
</head>
<body>
<h1><fmt:message var="${text}" key="registerJsp.bodyH1"/>:</h1>
<form action="${pageContext.request.contextPath}/register">
    <p style="width: 100px"><%= String.format("%18s", "Login:")%>
    </p>
    <label>
        <fmt:message var="${text}" key="registerJsp.loginText"/>:<br/>
        <input name="login" type="text">
    </label>
    <label>
        <fmt:message var="${text}" key="registerJsp.passwordText"/>:<br/>
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>
</body>
</html>
