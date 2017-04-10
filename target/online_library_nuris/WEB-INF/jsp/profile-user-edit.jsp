<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.04.2017
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:design-pattern role="${sessionScope.role}">
    <form class="form-horizontal" action="email-edit" method="POST">
        <fieldset>
            <legend>Изменить почтовый адрес</legend>
            <div class="form-group">
                <label class="col-md-4 control-label" for="fn"></label>
                <div class="col-md-4">
                    <input id="fn" name="email" type="text" placeholder="${email}" class="form-control input-md">
                    <c:if test="${not empty email_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">Ошибочка</p>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit_date" name="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </div>

        </fieldset>
    </form>

    <form class="form-horizontal" action="password-edit" method="POST">
        <fieldset>
            <legend>Изменить пароль от кабинета</legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Введи старый пароль</label>
                <div class="col-md-4">
                    <input type="password" id="password_old" name="old_pass" class="form-control input-md">
                    <c:if test="${not empty password_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">Неверный пароль</p>
                    </c:if>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Новый пароль</label>
                <div class="col-md-4">
                    <input type="password" id="password" name="password" class="form-control input-md">
                    <c:if test="${not empty old_pass_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">Неверный пароль</p>
                    </c:if>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password_confirm">Подтвердить новый пароль</label>
                <div class="col-md-4">
                    <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </div>
        </fieldset>
        </form>

</my:design-pattern>