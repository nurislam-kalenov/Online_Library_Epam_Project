<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.04.2017
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:design-pattern role="user">
    <div class="col-md-12 column ">
        <div class="row clearfix ">
            <div class="col-md-3 column">
                <div class="panel panel-default">
                    <div class="panel-heading libreStatsPanelHeading">
                        <div class="panel-title">
                            <span>Паспортные данные пользователя</span>
                            <a class="fa fa-caret-down pull-right libreStatsPanelHeadingIcon" href="#uniqueVisitor"
                               data-toggle="collapse"></a>
                        </div>
                    </div>
                    <div class="panel-body libreStatsPanelBody collapse in" id="uniqueVisitor">
                        <div class="row clearfix libreStatsPanelRow">
                            <div class="col-md-12 column libreStatsPanelValueColumn">
                                <p>Имя : ${customer_info.person.firstName}</p>
                                <p>Фамилия : ${customer_info.person.lastName} </p>
                                <p>Отчество : ${customer_info.person.middleName}</p>
                                <p>Телефон : ${customer_info.person.phone}</p>
                                <p>Адрес : ${customer_info.person.address}</p>
                                <p>Город : ${customer_info.person.city.name}</p>
                                <p>День рождения : ${customer_info.person.birthday}</p>

                            </div>
                            <div class="col-md-4 column">
                                <span class="pull-right fa fa-user fa-5x libreStatsIcon"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 column">
                <div class="panel panel-default">
                    <div class="panel-heading libreStatsPanelHeading">
                        <div class="panel-title">
                            <span>Данные о пользователе</span>
                            <a class="fa fa-caret-down pull-right libreStatsPanelHeadingIcon" href="#activity"
                               data-toggle="collapse"></a>
                        </div>
                    </div>
                    <div class="panel-body libreStatsPanelBody collapse in" id="activity">
                        <div class="row clearfix libreStatsPanelRow">
                            <div class="col-md-12 column libreStatsPanelValueColumn">
                                <p>Email(Login) : ${customer_info.email}</p>
                                <p>Дата регистраций : ${customer_info.registerDate} </p>
                                <p>Статус пользователя : ${customer_info.customerRole.name}</p>
                            </div>

                        </div>

                    </div>

                </div>
                <button type="button" class="btn btn-warning col-md-6 pull-right">Изменить данные</button>
            </div>
        </div>
    </div>
</my:design-pattern>
