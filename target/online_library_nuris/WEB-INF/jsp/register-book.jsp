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

                    <legend>О книге</legend>

                    <div class="form-group">
                        <div class="col-sm-10">
                            <label>Название книги</label>
                            <input type="text" name="fistName" placeholder="Название" class="form-control">
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                        <label>ISBN</label>
                        <input type="text" name="middleName" placeholder="ISBN" class="form-control">
                    </div>
                        <div class="col-sm-3">
                            <label>Год</label>
                            <input type="text" name="fistName" placeholder="Год публикаций" class="form-control">
                        </div>
                        <div class="col-sm-3">
                            <label>Жанр</label>
                            <select id="city" name="city" class="form-control input-md">
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
                        <textarea placeholder="О книге" rows="4" class="form-control"></textarea>
                            </div>
                    </div>

                    <legend>Экономическая часть</legend>

                    <div class="form-group">
                        <div class="col-sm-5">
                            <label>Количесвто книг</label>
                            <input type="text" name="middleName" placeholder="Количесвто книг" class="form-control">
                        </div>
                        <div class="col-sm-5">
                            <label>Цена за штуку</label>
                            <input type="text" name="fistName" placeholder="Цена за штуку" class="form-control">
                        </div>

                    </div>
                    <legend>Об авторе</legend>
                    <div class="form-group">
                        <div class="col-sm-3">
                            <label>Имя</label>
                            <input type="text" name="middleName" placeholder="Имя" class="form-control">
                        </div>
                        <div class="col-sm-4">
                            <label>Фамилия</label>
                            <input type="text" name="fistName" placeholder="Фамилия" class="form-control">
                        </div>
                        <div class="col-sm-3">
                            <label>Отчество</label>
                            <input type="text" name="middleName" placeholder="Отчество" class="form-control">
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
    </div><!-- /.row -->





</my:design-pattern>
