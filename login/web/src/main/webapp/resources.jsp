<%--
  Created by IntelliJ IDEA.
  User: SB
  Date: 24.04.2020
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resourceBundle" var="text"/>

<html>
<head>
    <title><fmt:message bundle="${text}" key="resourcesJsp.title"/></title>
</head>
<body>

<%@ include file="pseudoHeader.jsp" %>

<a href="${pageContext.request.contextPath}/logout"><fmt:message bundle="${text}" key="resourcesJsp.logout"/></a>

<c:if test="${sessionScope.deleteMessage !=null}">
    <p>${sessionScope.deleteMessage}</p>
    <c:remove var="deleteMessage" scope="session"/>
    <form method="post" action="${pageContext.request.contextPath}/restoreArticle">
        <button formaction="${pageContext.request.contextPath}/restoreArticle" formmethod="post"
                name="idToRestore"
                value="${sessionScope.deletedId}" type="submit">
            <fmt:message bundle="${text}" key="resourcesJsp.restoreButton"/>
        </button>
    </form>
</c:if>

<c:if test="${sessionScope.restoreMessage !=null}">
    <p>${sessionScope.restoreMessage}</p>
    <c:remove var="restoreMessage" scope="session"/>
</c:if>

<c:if test="${requestScope.articles != null}">
    <c:forEach items="${requestScope.articles}" var="article">
        <h2><c:out value="${article.value.header}"/></h2>
        <p><c:out value="${article.value.author}"/></p>
        <p><c:out value="${article.key}"/></p>
        <p><c:out value="${article.value.text}"/></p>
        <%--        TODO edit article--%>
        <%--        <form method="post" action="...">--%>
        <%--            <button formaction="${pageContext.request.contextPath}/..." formmethod="post" name="idToUpdate"--%>
        <%--                    value="${article.value.newsId}" type="submit">--%>
        <%--                <fmt:message bundle="${text}" key="resourcesJsp.updateButton"/>--%>
        <%--            </button>--%>
        <%--        </form>--%>
        <form method="post" action="${pageContext.request.contextPath}/deleteNewsArticle">
            <button formaction="${pageContext.request.contextPath}/deleteNewsArticle" formmethod="post"
                    name="idToDelete"
                    value="${article.value.newsId}" type="submit">
                <fmt:message bundle="${text}" key="resourcesJsp.deleteButton"/>
            </button>
        </form>
        <%--        <br/>--%>
        <p>$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$</p>
    </c:forEach>
</c:if>

<c:if test="${sessionScope.authUser.role == 'ADMIN' }">
    <form action="${pageContext.request.contextPath}/resources" method="post">
        <label>
            <fmt:message bundle="${text}" key="resourcesJsp.newsHeading"/><br/>
            <input type="text" name="heading"><br/>
        </label>

        <label>
            <fmt:message bundle="${text}" key="resourcesJsp.newsText"/><br/>
            <input type="text" name="bodyArticle"><br/>
        </label>
        <input type="hidden" name="author" value="${sessionScope.authUser.login}"><br/>
        <input type="submit"><br/>
    </form>
</c:if>

</body>
</html>
