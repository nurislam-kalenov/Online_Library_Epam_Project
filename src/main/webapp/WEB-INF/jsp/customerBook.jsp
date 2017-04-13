<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.04.2017
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<my:designPattern role="user">
    <div class="wrapper">
        <table id="acrylic">
            <thead>
            <tr>
                <th>Название книги</th>
                <th>Жанр</th>
                <th>Стоимость</th>
                <th>Взял</th>
                <th>Прошло времени</th>
                <th>Вернуть</th>
                <th>Одобрение</th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Шантарам</td>
                <td>Приключение</td>
                <td>2500 тг</td>
                <td>2017-08-02</td>
                <td>6 дн</td>
                <td>
                    <a href="#" class="btn btn-danger" role="button">Вернуть</a>
                </td>
                <td>Подтверждение</td>
            </tr>
            </tbody>
        </table>
    </div>
</my:designPattern>
