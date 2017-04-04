<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.03.2017
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="register_url" value="/kz/register"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<my:design-pattern role="admin">
    <div class="login-box">
        <form id="loginForm" action="#" method="POST">
            <div class="panel panel-default">
                <div class="panel-heading"><b>Sign in</b></div>
                <div class="panel-body">
                    <div class="form-group has-primary has-feedback">
                        <label class="control-label" for="login">Login <span class="regForm text-danger">*</span></label>
                        <input type="text" class="form-control" name="login" id="login" aria-describedby="login" required>
                    </div>
                    <div class="form-group has-primary has-feedback">
                        <label class="control-label" for="password">Password <span class="regForm text-danger">*</span></label>
                        <input type="password" class="form-control" name="password" id="password" aria-describedby="password" required>
                    </div>
                </div>
                <div class="panel-footer">
                    <input type="submit" class="btn btn-success" id="goToChat" value="Sign in" />
                    <a class="btn btn-warning pull-right" href="${register_url}" >Registration</a>
                </div>
            </div>
        </form>
    </div>

</my:design-pattern>

