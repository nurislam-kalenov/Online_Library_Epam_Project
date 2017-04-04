<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="role" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<c:url var="register_url" value="/kz/register"/>

<html>
<head>
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css"/>
        <jsp:directive.include file="/WEB-INF/css/header.css"/>
    </style>
    <title>Online Library</title>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <c:if test="${role.equals('admin')}">
        <div class="container">
            <div class="navbar-header">
                <div class="navbar-brand navbar-brand-centered">Онлайн LIB</div>
            </div>
            <div class="collapse navbar-collapse" id="navbar-brand-admin">
                <ul class="nav navbar-nav">
                    <li><a href="#">Книги</a></li>
                    <li><a href="#">Читатели</a></li>
                    <li><a href="#">Менеджмент</a></li>
                    <li><a href="#">Личный кабинет</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">English</a></li>
                    <li><a href="#">Руский</a></li>
                    <li><a href="#">Выйти</a></li>
                </ul>
            </div>

        </div>
    </c:if>
    <c:if test="${role.equals('customer')}">
        <div class="container">
            <div class="navbar-header">
                <div class="navbar-brand navbar-brand-centered">Онлайн LIB</div>
            </div>
            <div class="collapse navbar-collapse" id="navbar-brand-customer">

                <ul class="nav navbar-nav">
                    <li><a href="#">Книги</a></li>
                    <li><a href="#">Личный кабинет</a></li>
                    <li><a href="#">Мои книги</a></li>

                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">English</a></li>
                    <li><a href="#">Руский</a></li>
                    <li><a href="#">Выйти</a></li>
                </ul>
            </div>

        </div>
    </c:if>
    <c:if test="${role.equals('guest')}">
        <div class="container">
            <div class="navbar-header">
                <div class="navbar-brand navbar-brand-centered">Онлайн LIB</div>
            </div>
            <div class="collapse navbar-collapse" id="navbar-brand-centered">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">English</a></li>
                    <li><a href="#">Руский</a></li>
                </ul>
            </div>

        </div>
    </c:if>
</nav>

<div id="body" style="height: 100%">
    <jsp:doBody/>
</div>

<div id="footer" style="flex: 0 0 auto">
    <c:if test="${role.equals('guest')}">
        <footer class="container-fluid text-center bg-lightgray"style="margin-top:90px;">
            <div class="copyrights" style="margin-top:25px;">
                <p>Epam System Lab21 © 2017, All Rights Reserved
                    <br>
                    <span>Web Design By: Kalenov Nurislam</span></p>
                <p><a href="https://vk.com/chaknuris" target="_blank">VKontakte<i class="fa fa-linkedin-square" aria-hidden="true"></i> </a></p>
            </div>
        </footer>
    </c:if>
    <c:if test="${role.equals('admin') || role.equals('customer')}">
        <footer class="container-fluid text-center bg-lightgray" style="margin-top:-150px;">

            <div class="copyrights" style="margin-top:25px;">
                <p>Epam System Lab21 © 2017, All Rights Reserved
                    <br>
                    <span>Web Design By: Kalenov Nurislam</span></p>
                <p><a href="https://vk.com/chaknuris" target="_blank">VKontakte<i class="fa fa-linkedin-square" aria-hidden="true"></i> </a></p>
            </div>
        </footer>
    </c:if>
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>