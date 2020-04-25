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
    <title>Private News</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/logout">logout</a>

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

<c:if test="${requestScope.articles != null}">
    <c:forEach items="${requestScope.articles}" var="article">
        <h2><c:out value="${article.value.header}"/></h2>
        <p><c:out value="${article.value.author}"/></p>
        <p><c:out value="${article.key}"/></p>
        <p><c:out value="${article.value.text}"/></p>
        <p>$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$</p>
    </c:forEach>
</c:if>

<c:if test="${sessionScope.authUser.role == 'ADMIN' }">
    <form action="${pageContext.request.contextPath}/resources" method="post">
        <label>
            Залоговок<br/>
            <input type="text" name="heading"><br/>
        </label>

        <label>
            Text<br/>
            <input type="text" name="bodyArticle"><br/>
        </label>
        <input type="hidden" name="author" value="${sessionScope.authUser.login}"><br/>
        <input type="submit"><br/>
    </form>
</c:if>

</body>
</html>
