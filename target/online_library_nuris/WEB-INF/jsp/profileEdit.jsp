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
<c:url var="account_url" value="/kz/account"/>

<fmt:bundle basename="i18n">
    <fmt:message key="profile.edit.address" var="address_edit"/>
    <fmt:message key="profile.edit.old.password" var="change_pass"/>
    <fmt:message key="profile.edit.new.password" var="new_pass"/>
    <fmt:message key="profile.edit.old.password" var="old_pass"/>
    <fmt:message key="profile.edit.confirm.password" var="confirm_pass"/>
    <fmt:message key="register.book.button.save" var="passport_save"/>
    <fmt:message key="register.button.home" var ="button_home"/>

    <fmt:message key="register.error.data" var="data_error"/>
</fmt:bundle>
<my:designPattern role="${sessionScope.role}">
    <form class="form-horizontal" action="email-edit" method="POST">
        <fieldset>
            <legend>${address_edit}</legend>
            <div class="form-group">
                <label class="col-md-4 control-label" for="fn"></label>
                <div class="col-md-4">
                    <input id="fn" name="email" type="text" placeholder="${email}" class="form-control input-md">
                    <c:if test="${not empty email_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${data_error}</p>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit_date" name="submit" class="btn btn-primary">${passport_save}</button>
                </div>
            </div>
        </fieldset>
    </form>

    <form class="form-horizontal" action="password-edit" method="POST">
        <fieldset>
            <legend>${change_pass}</legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">${old_pass}</label>
                <div class="col-md-4">
                    <input type="password" id="password_old" name="old_pass" class="form-control input-md">
                    <c:if test="${not empty password_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${data_error}</p>
                    </c:if>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">${new_pass}</label>
                <div class="col-md-4">
                    <input type="password" id="password" name="password" class="form-control input-md">
                    <c:if test="${not empty old_pass_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${data_error}</p>
                    </c:if>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password_confirm">${change_pass}</label>
                <div class="col-md-4">
                    <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                           class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="submit" class="btn btn-primary">${passport_save}</button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <a href="${account_url}" class="btn btn-info" role="button">${button_home}</a>
                </div>
            </div>
        </fieldset>
    </form>

</my:designPattern>