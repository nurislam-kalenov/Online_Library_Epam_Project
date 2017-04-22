<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2017
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:designPattern role="user">
    <style>
        <jsp:directive.include file="/WEB-INF/css/tables.css"/>
    </style>
    <div class="row">
        <div class="table-title">
            <h3>Корзина</h3>
        </div>
        <table class="table-fill">
            <thead>
            <tr>
                <th class="text-left">Название книги</th>
                <th class="text-left">Жанр</th>
                <th class="text-left">Действие</th>
            </tr>
            </thead>
            <tbody class="table-hover">
            <c:forEach items="${basket.books}" var="bookInfo">
                <tr>
                    <td class="text-left">${bookInfo.book.name}</td>
                    <td class="text-left">${bookInfo.book.genre.name}</td>
                    <td class="text-left">
                        <div class="row">
                            <div class="col-md-4 column ">
                                <form action="takeBook" method="POST">
                                    <input type="hidden" name="book_id" value="${bookInfo.id}">
                                    <input type="hidden" name="book_id_take" value="${bookInfo.id}">
                                    <button name="submit" class="btn btn-warning ">Взять</button>
                                </form>
                            </div>
                            <div class="col-md-4 column ">
                                <form action="basket-delete" method="POST">
                                    <input type="hidden" name="book_id_delete" value="${bookInfo.id}">

                                    <button name="submit" class="btn btn-danger ">Удалить</button>
                                </form>
                            </div>
                        </div>

                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="row" style="padding-top: 20px">
        <div class="col-md-7">
            </div>
        <form action="basket-delete" method="POST">
            <input type="hidden" name="book_id_delete_all" value="${bookInfo.id}">
            <button name="submit" class="btn btn-danger">Удалить все</button>
        </form>
        </div>

</my:designPattern>