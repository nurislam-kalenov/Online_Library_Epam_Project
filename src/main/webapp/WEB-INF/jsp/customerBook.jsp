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
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<my:designPattern role="user">
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-1" style="margin-top: 50px">
            <a href="customerBook?active=false" class="btn btn-danger btn-circle btn-xl" role="button">На руках</a>
            <a href="customerBook?active=false" class="btn btn-warning btn-circle btn-xl" role="button">Ждем</a>
            <a href="customerBook?active=true" class="btn btn-success danger btn-circle btn-xl" role="button">История</a>
        </div>
        <div class="col-md-8">
            <div class="wrapper">
                <table id="acrylic">
                    <c:if test="${isActiveState eq false}">
                        <thead>
                        <tr>
                            <th class="col-md-2">Название книги</th>
                            <th class="col-md-2">Стоимость</th>
                            <th class="col-md-2">Взял</th>
                            <th class="col-md-2">Вернул</th>
                            <th class="col-md-2">Одобрение</th>
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
                                    <button class="btn btn-warning btn-md"><i class="fa fa-spinner fa-spin"></i>
                                        Обработка
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </c:if>
                    <c:if test="${isActiveState eq true}">
                        <thead>
                        <tr>
                            <th class="col-md-2">Название книги</th>
                            <th class="col-md-2">Стоимость</th>
                            <th class="col-md-2">Взял</th>
                            <th class="col-md-2">Вернул</th>
                            <th class="col-md-2">Одобрение</th>
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
                                    <button id="button1id" name="button1id" class="btn btn-success">Подтвержденно</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>
                </table>
                <div class="col-md-10">
                    <my:listPages currentPage="${currentPage}" noOfPages="${noOfPages}" books_url="customerBook?page="/>
                </div>
            </div>
        </div>
    </div>
</my:designPattern>
