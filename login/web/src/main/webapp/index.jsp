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

<%@ include file="pseudoHeader.jsp" %>

<p><fmt:message key="indexJsp.pWelcome" bundle="${text}"/></p>

<p>
    <fmt:message key="indexJsp.currentTime" bundle="${text}"/> <%= Util.getDateTime(session.getAttribute("language"))%>
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