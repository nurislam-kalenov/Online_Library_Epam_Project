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

            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Жанр</h3>
                            </div>
                            <ul class="list-group">
                                <c:forEach items="${genres}" var="genre">
                                    <a href="books?genre_id=${genre.id}"  class="list-group-item">${genre.name}</a>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <table>
                            <c:forEach items="${books}" var="book">
                                <tr>
                                    <td><c:out value="${book.id}"/></td>
                                    <td><c:out value="${book.name}"/></td>
                                    <td><c:out value="${book.date}"/></td>
                                    <td><c:out value="${book.description}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-offset-5">
                        <my:list-pages currentPage="${currentPage}" noOfPages="${noOfPages}" books_url="books?page="/>
                    </div>
                </div>
            </div>










</my:design-pattern>
