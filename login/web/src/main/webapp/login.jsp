<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 23.04.2020
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<html>
<head>
    <title><fmt:message key="loginJsp.title" var="${text}"/></title>
    <style>
        .error {
            color: red;
            font-size: 200%;
        }
    </style>
</head>
<body>
<table style="float: right;">
    <tbody>
    <tr>
        <td>
            <button formaction="/language" formmethod="post" name="lang" value="english" type="submit">en
            </button>
        </td>
        <td>
            <button formaction="/language" formmethod="post" name="lang" value="russian" type="submit">ru</button>
        </td>
        <td width="150px" align=right><fmt:message key="indexJsp.language" bundle="${text}"/></td>
    </tr>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label>
        <fmt:message var="${text}" key="loginJsp.loginText"/>:
        <input name="login" type="text">
    </label>
    <label>
        <fmt:message var="${text}" key="loginJsp.passwordText"/>:
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>
<p><a href="${pageContext.request.contextPath}/register"><fmt:message var="${text}" key="loginJsp.signUp"/></a></p>
<p class="error">${requestScope.error}</p>
</body>
</html>
