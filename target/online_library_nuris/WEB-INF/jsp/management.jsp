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
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-1" style="margin-top: 50px">
            <a href="management?active=false" class="btn btn-success btn-circle btn-xl" role="button">Active</a>
            <a href="management?active=true" class="btn btn-warning danger btn-circle btn-xl" role="button">Inactive</a>
            <a href="#" class="btn btn-info danger btn-circle btn-xl" role="button">Поиск</a>
        </div>

        <div class="col-md-8">
            <div class="wrapper">
                <table id="acrylic">
                    <c:if test="${isActiveState eq false}">

                        <thead>
                        <tr>
                            <th class="col-md-2">Название книги</th>
                            <th class="col-md-2">Поступило</th>
                            <th class="col-md-2">Имя</th>
                            <th class="col-md-2">Фамилия</th>
                            <th class="col-md-3">email</th>
                            <th class="col-md-2">Одобрить</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${managements}" var="manage">
                            <tr>
                                <td>${manage.transaction.bookInfo.book.name}</td>
                                <td>${manage.transaction.endDate}</td>
                                <td>${manage.transaction.customer.person.firstName}</td>
                                <td>${manage.transaction.customer.person.lastName}</td>
                                <td>${manage.transaction.customer.email}</td>
                                <td>
                                    <form action="adminReturnBook" method="POST">
                                        <input type="hidden" name="management_id" value="${manage.id}">
                                        <button id="submit" name="submit" class="btn btn-danger">Одобрить</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>

                    <c:if test="${isActiveState eq true}">
                        <thead>
                        <tr>
                            <th class="col-md-2">Название книги</th>
                            <th class="col-md-2">Поступило</th>
                            <th class="col-md-2">Имя</th>
                            <th class="col-md-2">Фамилия</th>
                            <th class="col-md-3">email</th>
                            <th class="col-md-2">Одобренно</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${managements}" var="manage">
                            <tr>
                                <td>${manage.transaction.bookInfo.book.name}</td>
                                <td>${manage.transaction.endDate}</td>
                                <td>${manage.transaction.customer.person.firstName}</td>
                                <td>${manage.transaction.customer.person.lastName}</td>
                                <td>${manage.transaction.customer.email}</td>
                                <td>${manage.returnDate}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>
                </table>
            </div>
            <div class="col-md-10">
                <my:listPages currentPage="${currentPage}" noOfPages="${noOfPages}" books_url="management?page="/>
            </div>
        </div>
    </div>
</my:designPattern>