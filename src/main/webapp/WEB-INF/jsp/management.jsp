<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.04.2017
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:designPattern role="${sessionScope.role}">
    <div class="wrapper">
        <table id="acrylic">
            <thead>
            <tr>
                <th>Название книги</th>

                <th>Одобрить</th>

                <th>Поступило</th>
                <th>Одобрение</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${managements}" var="manage">
                <tr>
                    <td>${manage.transaction.bookInfo.book.name}</td>
                    <td>
                        <form action="returnBook" method="POST">
                            <input type="hidden" name="return_book" value="${book.id}">
                            <button id="submit" name="submit" class="btn btn-danger" >Вернуть</button>
                        </form>
                    </td>
                    <td>${manage.transaction.endDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="row">
        <div class="col-md-offset-5">
            <my:listPages currentPage="${currentPage}" noOfPages="${noOfPages}" books_url="management?page="/>
        </div>
    </div>

</my:designPattern>