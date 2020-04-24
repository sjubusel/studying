<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 24.04.2020
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title>Private News</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/logout">logout</a>
<c:if test="${requestScope.articles != null}">
    <c:forEach items="${requestScope.articles}" var="article">
        <h2><c:out value="${article.value.header}"/></h2>
        <p><c:out value="${article.key}"/></p>
        <p><c:out value="${article.value.text}"/></p>
        <p>$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$</p>
    </c:forEach>
</c:if>

<c:if test="${sessionScope.authUser.role == 'ADMIN' }">
    <form action="${pageContext.request.contextPath}/register" method="post">
        <label>
            Залоговок<br/>
            <input type="text" name="heading">
        </label>

        <label>
            Text<br/>
            <input type="text" name="bodyArticle">
        </label>
        <input type="hidden" name="author" value="${sessionScope.authUser.login}">
        <input type="submit">
    </form>
</c:if>
</body>
</html>
