<%--TODO add a link to "/index"--%>
<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 24.04.2020
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<html>
<head>
    <title><fmt:message key="registerJsp.title" bundle="${text}"/></title>
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

<h1>
    <fmt:message bundle="${text}" key="registerJsp.bodyH1"/>:
</h1>

<form action="${pageContext.request.contextPath}/register" method="post">
    <label>
        <fmt:message bundle="${text}" key="registerJsp.loginText"/>:<br/>
        <input name="login" type="text"><br/>
    </label>
    <label>
        <fmt:message bundle="${text}" key="registerJsp.passwordText"/>:<br/>
        <input name="password" type="password"><br/>
    </label>
    <input type="submit"><br/>
</form>

<p class="error">${requestScope.error}</p>

</body>
</html>
