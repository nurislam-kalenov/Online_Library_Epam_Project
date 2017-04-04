<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.03.2017
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<c:url var="register_url" value="/kz/register"/>
<c:url var="home_url" value="/kz/welcome"/>

<html>
<head>
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css"/>
    </style>
    <title>Registration customer</title>
</head>
<body>
<form class="form-horizontal" action="${register_url}" method="POST">
    <fieldset>
        <!-- Form Name -->
        <legend>Регистрация</legend>
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="fn">First name</label>
            <div class="col-md-4">
                <input id="fn" name="first_name" type="text" placeholder="first name" class="form-control input-md">
                <c:if test="${not empty first_name_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="ln">Last name</label>
            <div class="col-md-4">
                <input id="ln" name="last_name" type="text" placeholder="last name" class="form-control input-md">
                <c:if test="${not empty last_name_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mn">Middle name</label>
            <div class="col-md-4">
                <input id="mn" name="middle_name" type="text" placeholder="middle name" class="form-control input-md">
                <c:if test="${not empty middle_name_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label for="birthDate" class="col-md-4 control-label">Date of Birth</label>
            <div class="col-md-4">
                <input id="birthDate" name="birthday" type="text" placeholder="1995-12-31"
                       class="form-control input-md">
                <c:if test="${not empty birthday_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phone">Text InputPhone</label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" placeholder="Phone#" class="form-control input-md">
                <c:if test="${not empty phone_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <!-- E-mail -->
            <label class="col-md-4 control-label" for="email">E-mail (Login)</label>
            <div class="col-md-4">
                <input type="text" id="email" name="email" placeholder="email" class="form-control input-md">
                <c:if test="${not empty email_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <!-- Password-->
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input type="password" id="password" name="password" placeholder="" class="form-control input-md">
                <p class="help-block">Password should be at least 6 characters</p>
                <c:if test="${not empty password_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректное имя</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <!-- Password -->
            <label class="col-md-4 control-label" for="password_confirm">Password (Confirm)</label>
            <div class="col-md-4">
                <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                       class="form-control input-md">
                <p class="help-block">Please confirm password</p>
            </div>
        </div>
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="city">City</label>
            <div class="col-md-4">
                <select id="city" name="city" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${cityList}" var="city">
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="add">Address</label>
            <div class="col-md-4">
                <input id="add" name="address" type="text" placeholder="address" class="form-control input-md">
                <c:if test="${not empty address_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">Введите коректный адрес</p>
                </c:if>
            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="submit" class="btn btn-primary">Регистрация</button>
            </div>
        </div>

        <div class="form-group">

            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <a href="${home_url}" class="btn btn-info" role="button">Home</a>
            </div>
        </div>

    </fieldset>
</form>
</body>
</html>
