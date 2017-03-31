<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.03.2017
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:url var="register_url" value="/kz/register"/>

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
                <input id="fn" name="first_name" type="text" placeholder="first name" class="form-control input-md" required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="ln">Last name</label>
            <div class="col-md-4">
                <input id="ln" name="last_name" type="text" placeholder="last name" class="form-control input-md" required="">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mn">Middle name</label>
            <div class="col-md-4">
                <input id="mn" name="middle_name" type="text" placeholder="middle name" class="form-control input-md" required="">
            </div>
        </div>

        <div class="form-group">
            <label for="birthDate" class="col-md-4 control-label">Date of Birth</label>
            <div class="col-md-4">
                <input type="date" id="birthDate"  name="birthday" class="form-control input-md"  required="">
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phone">Text InputPhone</label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" placeholder="Phone#" class="form-control input-md" required="">
            </div>
        </div>

        <div class="form-group">
            <!-- E-mail -->
            <label class="col-md-4 control-label" for="login">E-mail (Login)</label>
            <div class="col-md-4">
                <input type="text" id="login" name="login" placeholder=""  class="form-control input-md">
                <p class="help-block">Please provide your E-mail</p>
            </div>
        </div>

        <div class="form-group">
            <!-- Password-->
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input type="password" id="password" name="password" placeholder=""  class="form-control input-md">
                <p class="help-block">Password should be at least 4 characters</p>
            </div>
        </div>

        <div class="form-group">
            <!-- Password -->
            <label class="col-md-4 control-label"  for="password_confirm">Password (Confirm)</label>
            <div class="col-md-4">
                <input type="password" id="password_confirm" name="password_confirm" placeholder=""  class="form-control input-md">
                <p class="help-block">Please confirm password</p>
            </div>
        </div>
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="city">City</label>
            <div class="col-md-4">
                <select id="city" name="city" class="form-control input-md">
                    <option>Kostanai</option>
                    <option>Karaganda</option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="add">Address</label>
            <div class="col-md-4">
                <input id="add" name="address" type="text" placeholder="address" class="form-control input-md" required="">

            </div>
        </div>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="submit" class="btn btn-primary">SUBMIT</button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
