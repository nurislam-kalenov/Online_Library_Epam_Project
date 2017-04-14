<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.04.2017
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:designPattern role="user">
    <div class="wrapper">
        <table id="acrylic">
            <thead>
            <tr>
                <th>Название книги</th>
                <th>Стоимость</th>
                <th>Взял</th>
                <th>Вернул</th>

                <th>Вернуть</th>
                <th>Одобрение</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${customer_book}" var="book">
                <tr>
                    <td>${book.bookInfo.book.name}</td>
                    <td>${book.bookInfo.price} тг.</td>
                    <td>${book.startDate}</td>
                    <td>${book.endDate}</td>

                    <td>
                        <form action="returnBook" method="POST">
                            <input type="hidden" name="return_book" value="${book.id}">
                            <button id="submit" name="submit" class="btn btn-danger" >Вернуть</button>
                        </form>
                    </td>
                    <td>Подтверждение</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</my:designPattern>
