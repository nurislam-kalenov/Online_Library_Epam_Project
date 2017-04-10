<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="role" required="true" rtexprvalue="true" type="java.lang.String" %>
<c:url var="profile_edit_url" value="/kz/profileEdit"/>
<c:url var="personal_date_edit_url" value="/kz/personalDataEdit"/>
<c:if test="${role.equals('user')}">
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
            <a href="${profile_edit_url}" class="btn btn-warning col-md-6 pull-right" role="button">Изменить данные</a>
        </div>
    </div>
</div>
</c:if>

<c:if test="${role.equals('admin')}">
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
                    <div class="panel-body libreStatsPanelBody collapse in" id="uniqueVisitor_admin">
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
                <a href="${personal_date_edit_url}" class="btn btn-warning col-md-6 pull-right" role="button">Изменить данные</a>

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
                    <div class="panel-body libreStatsPanelBody collapse in" id="activity_admin">
                        <div class="row clearfix libreStatsPanelRow">
                            <div class="col-md-12 column libreStatsPanelValueColumn">
                                <p>Email(Login) : ${customer_info.email}</p>
                                <p>Дата регистраций : ${customer_info.registerDate} </p>
                                <p>Статус пользователя : ${customer_info.customerRole.name}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn-danger col-md-6 pull-right">Удалить Профиль</button>
                <a href="${profile_edit_url}" class="btn btn-warning col-md-6 pull-right" role="button">Изменить данные</a>

            </div>
        </div>
    </div>
</c:if>