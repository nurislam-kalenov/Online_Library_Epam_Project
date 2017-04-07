<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.04.2017
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Picture</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description"/>
    <input type="file" name="file"/>
    <input type="submit"/>
</form>

<img src="<c:url value="/image/avatar/${avatar_pic}"/>"
     style="width: auto;height: 80px"
     class="img-responsive"/>
</body>
</html>
