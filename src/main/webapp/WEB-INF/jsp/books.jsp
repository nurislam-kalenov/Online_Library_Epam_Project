<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.04.2017
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<my:design-pattern role="user">
    <table>
        <c:forEach items="${books}" var="book">
            <tr>
                <td><c:out value="${book.id}" /></td>
                <td><c:out value="${book.name}" /></td>
                <td><c:out value="${book.date}" /></td>
                <td><c:out value="${book.description}" /></td>
            </tr>
        </c:forEach>
    </table>
    <my:list-pages currentPage="${currentPage}" noOfPages="${noOfPages}" books_url="books?page="/>

</my:design-pattern>
