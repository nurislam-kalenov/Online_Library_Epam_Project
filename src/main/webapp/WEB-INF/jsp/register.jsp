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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="register_url" value="/kz/register"/>
<c:url var="home_url" value="/kz/welcome"/>

<fmt:bundle basename="i18n">
    <fmt:message key="register.firstname" var="first_name"/>
    <fmt:message key="register.lastname" var="last_name"/>
    <fmt:message key="register.middlename" var="middle_name"/>
    <fmt:message key="register.birthday" var="birthday"/>
    <fmt:message key="register.address" var="input_address"/>
    <fmt:message key="register.email" var ="email"/>
    <fmt:message key="register.password" var="password"/>
    <fmt:message key="register.confirm.password" var="confirm_password"/>
    <fmt:message key="register.city" var="choise_city"/>
    <fmt:message key="register.phone" var ="phone"/>
    <fmt:message key="register.pholder.phone" var="pholder_phone"/>
    <fmt:message key="register.pholder.address" var="pholder_address"/>
    <fmt:message key="register.pholder.email" var="pholder_email"/>
    <fmt:message key="register.pholder.firstname" var ="pholder_first_name"/>
    <fmt:message key="register.pholder.lastname" var ="pholder_last_name"/>
    <fmt:message key="register.pholder.middlename" var ="pholder_middle_name"/>
    <fmt:message key="register.button.registor" var="button_registor"/>
    <fmt:message key="register.button.home" var ="button_home"/>
    <fmt:message key="register.error.data" var ="error_data"/>
    <fmt:message key="register.head" var ="registration"/>
    <fmt:message key="register.hblock.password" var ="hblock_password"/>
    <fmt:message key="register.hblock.confirm.password" var ="hblock_confirm_password"/>
</fmt:bundle>

<my:designPattern role="guest">
    <form class="form-horizontal" action="${register_url}" method="POST">
    <fieldset>

        <legend>${registration}</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="fn">${first_name}</label>
            <div class="col-md-4">
                <input id="fn" name="first_name" type="text" placeholder="${pholder_first_name}" class="form-control input-md">
                <c:if test="${not empty first_name_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="ln">${last_name}</label>
            <div class="col-md-4">
                <input id="ln" name="last_name" type="text" placeholder="${pholder_last_name}" class="form-control input-md">
                <c:if test="${not empty last_name_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="mn">${middle_name}</label>
            <div class="col-md-4">
                <input id="mn" name="middle_name" type="text" placeholder="${pholder_middle_name}" class="form-control input-md">
                <c:if test="${not empty middle_name_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label for="birthDate" class="col-md-4 control-label">${birthday}</label>
            <div class="col-md-4">
                <input id="birthDate" name="birthday" type="text" placeholder="1995-12-31"
                       class="form-control input-md">
                <c:if test="${not empty birthday_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="phone">${phone}</label>
            <div class="col-md-4">
                <input id="phone" name="phone" type="text" placeholder="${pholder_phone}" class="form-control input-md">
                <c:if test="${not empty phone_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="email">${email}</label>
            <div class="col-md-4">
                <input type="text" id="email" name="email" placeholder="${pholder_email}" class="form-control input-md">
                <c:if test="${not empty email_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="password">${password}</label>
            <div class="col-md-4">
                <input type="password" id="password" name="password" class="form-control input-md">
                <p class="help-block">${hblock_password}</p>
                <c:if test="${ empty password_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="password_confirm">${confirm_password}</label>
            <div class="col-md-4">
                <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                       class="form-control input-md">
                <p class="help-block">${hblock_confirm_password}</p>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="city">${choise_city}</label>
            <div class="col-md-4">
                <select id="city" name="city" class="form-control input-md">
                    <option disabled></option>
                    <c:forEach items="${cityList}" var="city">
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="add">${input_address}</label>
            <div class="col-md-4">
                <input id="add" name="address" type="text" placeholder="${pholder_address}" class="form-control input-md">
                <c:if test="${not empty address_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${error_data}</p>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="submit" class="btn btn-primary">${button_registor}</button>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <a href="${home_url}" class="btn btn-info" role="button">${button_home}</a>
            </div>
        </div>

    </fieldset>
</form>
    </my:designPattern>

