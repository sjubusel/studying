<%--TODO add a link to "/index"--%>
<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 23.04.2020
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<html>
<head>
    <title><fmt:message key="loginJsp.title" bundle="${text}"/></title>
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
            <form method="post" action="${pageContext.request.contextPath}/language">
                <button formaction="${pageContext.request.contextPath}/language" formmethod="post" name="lang"
                        value="english" type="submit">en
                </button>
            </form>
        </td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/language">
                <button formaction="${pageContext.request.contextPath}/language" formmethod="post" name="lang"
                        value="russian" type="submit">ru
                </button>
            </form>
        </td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td style="width: 150px; alignment: right"><fmt:message key="indexJsp.language" bundle="${text}"/></td>
    </tr>
    </tfoot>
</table>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label>
        <fmt:message bundle="${text}" key="loginJsp.loginText"/>:
        <input name="login" type="text">
    </label>
    <label>
        <fmt:message bundle="${text}" key="loginJsp.passwordText"/>:
        <input name="password" type="password">
    </label>
    <input type="submit">
</form>

<p>
    <a href="${pageContext.request.contextPath}/register">
        <fmt:message bundle="${text}" key="loginJsp.signUp"/>
    </a>
</p>

<p class="error">${requestScope.error}</p>

</body>
</html>
