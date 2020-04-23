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
    <title><fmt:message key="indexJsp.title" bundle="resourceBundle"/></title>
</head>
<body>
<p><fmt:message key="indexJsp.pWelcome" bundle="resourceBundle"/></p>
<p><fmt:message key="indexJsp.currentTime"
                bundle="resourceBundle"/> <%= Util.getDateTimeBeBy(request)%>
</p>
<p>
    <fmt:message key="indexJsp.linkToResources_p1" bundle="resourceBundle"/> <a
        href="${pageContext.request.contextPath}/resources">
    <fmt:message key="indexJsp.linkToResources_p2"
                 bundle="resourceBundle"/></a>.
</p>
<p><fmt:message key="indexJsp.warning" bundle="resourceBundle"/></p>
</body>
</html>