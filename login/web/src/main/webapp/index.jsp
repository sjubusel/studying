<%@ page import="com.github.sjubusel.studying.login.Util" %>
<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 22.04.2020
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<html>
<head>
    <title><fmt:message key="indexJsp.title" bundle="${text}"/></title>
</head>
<body>
<%-- todo create FORM instead of buttons --%>
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

<p><fmt:message key="indexJsp.pWelcome" bundle="${text}"/></p>

<p>
    <fmt:message key="indexJsp.currentTime" bundle="${text}"/> <%= Util.getDateTime(request)%>
</p>

<p>
    <fmt:message key="indexJsp.linkToResources_p1" bundle="${text}"/>
    <a href="${pageContext.request.contextPath}/resources">
        <fmt:message key="indexJsp.linkToResources_p2" bundle="${text}"/>
    </a>
</p>

<p><fmt:message key="indexJsp.warning" bundle="${text}"/></p>

</body>
</html>