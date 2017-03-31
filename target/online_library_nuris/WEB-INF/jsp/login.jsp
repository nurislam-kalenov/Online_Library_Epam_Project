<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.03.2017
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="register_url" value="/kz/register"/>

<html>
<head>
    <title>Login</title>
</head>
<body>
login login login login
<a class="btn btn-default" role="button"
   href="${register_url}">Регистрация</a>
<br>
${login} is login
</br>
${pass} is pass
</p>

<form name="loginForm" method="post" action="${register_url}">
    <input type="submit"  value="log out">
</form>
</body>
</html>
