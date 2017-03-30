<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.03.2017
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home page</title>
</head>
<body>
<form name="loginForm" method="post" action="do/login">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password: <br/>
    <input type="password" name="password" value=""/>

    <input type="submit"  value="log in">
</form>
</body>
</html>
