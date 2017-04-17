<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.04.2017
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:designPattern role="user">
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-1" style="margin-top: 50px">
            <a href="deptCustomerBook" class="btn btn-danger btn-circle btn-xl" role="button">На руках</a>
            <a href="returnCustomerBook?active=false" class="btn btn-warning btn-circle btn-xl" role="button">Ждем</a>
            <a href="returnCustomerBook?active=true" class="btn btn-success danger btn-circle btn-xl" role="button">История</a>
        </div>
        <div class="col-md-8">
    <div class="wrapper">
        <table id="acrylic">
            <thead>
            <tr>
                <th class="col-md-2">Название книги</th>
                <th class="col-md-2">Стоимость</th>
                <th class="col-md-2">Взял</th>
                <th class="col-md-2">Вернуть</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${customer_book}" var="book">
                <tr>
                    <td>${book.bookInfo.book.name}</td>
                    <td>${book.bookInfo.price} тг.</td>
                    <td>${book.startDate}</td>
                    <td>
                        <form action="returnBook" method="POST">
                            <input type="hidden" name="return_book" value="${book.id}">
                            <button id="submit" name="submit" class="btn btn-danger" >Вернуть</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
        </div>
    </div>
</my:designPattern>