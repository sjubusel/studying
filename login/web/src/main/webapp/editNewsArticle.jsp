<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 14.05.2020
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<%@ include file="pseudoHeader.jsp" %>
<a href="${pageContext.request.contextPath}/logout"><fmt:message bundle="${text}" key="resourcesJsp.logout"/></a>

<html>
<head>
    <title><fmt:message bundle="${text}" key="editNewsArticleJsp.title"/></title>
</head>

<body>

<c:if test="${requestScope.articleToEdit != null}">
    <p><b><fmt:message bundle="${text}" key="editNewsArticleJsp.headline"/></b></p>
    <form method="post" action="${pageContext.request.contextPath}/postEditedNewsArticle">
        <p><fmt:message bundle="${text}" key="resourcesJsp.newsHeading"/>:</p>
        <label>
            <textarea name="newHeading" cols="40" rows="5">${requestScope.articleToEdit.header}</textarea>
        </label>
        <p><fmt:message bundle="${text}" key="resourcesJsp.newsText"/>:</p>
        <label>
            <textarea name="newText" cols="40" rows="5">${requestScope.articleToEdit.text}</textarea>
        </label>
        <input name="newAuthor" value="${sessionScope.authUser.login}" type="hidden">
        <input name="idToEdit" value="${requestScope.articleToEdit.newsId}" type="hidden">
        <p><input type="submit"></p>
    </form>
</c:if>

<c:if test="${requestScope.editMessage != null}">
    <p><c:out value="${requestScope.editMessage}"/></p>
</c:if>

<c:if test="${sessionScope.repostedMessage != null}">
    <p><c:out value="${sessionScope.repostedMessage}"/></p>
    <c:remove var="repostedMessage" scope="session"/>
</c:if>

<c:if test="${requestScope.articleToEdit == null
&& requestScope.editMessage == null && sessionScope.repostedMessage == null}">
    <p><fmt:message bundle="${text}" key="editNewsArticleJsp.nullToEdit"/> </p>
</c:if>

<%--<c:choose>--%>
<%--    <c:when test="${sessionScope.repostedMessage != null}">--%>
<%--        <c:out value="${sessionScope.repostedMessage}"/>--%>
<%--        <c:remove var="repostedMessage" scope="session"/>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <p>Нету </p>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>

<a href="${pageContext.request.contextPath}/resources">
    <fmt:message bundle="${text}" key="editNewsArticleJsp.backToResources"/>
</a>

</body>
</html>
