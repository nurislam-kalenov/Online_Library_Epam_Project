<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.04.2017
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<my:design-pattern role="admin">
    <!-- Name Section -->
    <div class="row">
        <div class="col-md-8 col-md-offset-1">
            <form class="form-horizontal" action="register-book" method="POST">
                <fieldset>
                    <legend><font color="teal"> О книге</font></legend>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <label>Название книги</label>
                            <input type="text" name="book_name" placeholder="Название" class="form-control">
                            <c:if test="${not empty book_name_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>ISBN</label>
                            <input type="text" name="isbn" placeholder="ISBN" class="form-control">
                            <c:if test="${not empty isbn_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-3">
                            <label>Год</label>
                            <input type="text" name="year" placeholder="Год публикаций" class="form-control">
                            <c:if test="${not empty year_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-3">
                            <label>Жанр</label>
                            <select id="city" name="genre_name" class="form-control input-md">
                                <option disabled></option>
                                <c:forEach items="${genreList}" var="genre">
                                    <option value="${genre.id}">${genre.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <label>Описание</label>
                            <textarea placeholder="О книге" name="description" rows="4" class="form-control"></textarea>
                            <c:if test="${not empty description_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                    </div>

                    <legend><font color="teal">Экономическая часть</font></legend>

                    <div class="form-group">
                        <div class="col-sm-5">
                            <label>Количесвто книг</label>
                            <input type="text" name="book_amount" placeholder="Количесвто книг" class="form-control">
                            <c:if test="${not empty book_amount_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-5">
                            <label>Цена за штуку</label>
                            <input type="text" name="book_price" placeholder="Цена за штуку" class="form-control">
                            <c:if test="${not empty book_price_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>

                    </div>
                    <legend><font color="teal"> Об авторе</font></legend>
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label>Имя</label>
                            <input type="text" name="first_name" placeholder="Имя" class="form-control">
                            <c:if test="${not empty first_name_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-4">
                            <label>Фамилия</label>
                            <input type="text" name="last_name" placeholder="Фамилия" class="form-control">
                            <c:if test="${not empty last_name_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-3">
                            <label>Отчество</label>
                            <input type="text" name="middle_name" placeholder="Отчество" class="form-control">
                            <c:if test="${not empty middle_name_error}">
                                <p class="alert alert-warning"
                                   style="height: 30px;padding: 5px">${error_data}</p>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-5 col-sm-offset-1">
                            <div class="pull-right">
                                <button type="submit" class="btn btn-default">Cancel</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div><!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->


</my:design-pattern>
