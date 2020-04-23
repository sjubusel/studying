<%@ page import="com.github.sjubusel.studying.login.Util" %>
<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 22.04.2020
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%! %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>
<html>
<head>
    <title><fmt:message key="indexJsp.title" bundle="${text}"/></title>
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
<p><fmt:message key="indexJsp.pWelcome" bundle="${text}"/>
</p>
<p><fmt:message key="indexJsp.currentTime"
                bundle="${text}"/> <%= Util.getDateTimeBeBy(request)%>
</p>
<p>
    <fmt:message key="indexJsp.linkToResources_p1" bundle="${text}"/> <a
        href="${pageContext.request.contextPath}/resources">
    <fmt:message key="indexJsp.linkToResources_p2"
                 bundle="${text}"/></a>.
</p>
<p><fmt:message key="indexJsp.warning" bundle="${text}"/></p>
</body>
</html>