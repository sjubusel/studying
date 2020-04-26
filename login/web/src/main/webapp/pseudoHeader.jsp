<a href="${pageContext.request.contextPath}/index">Home</a>

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
