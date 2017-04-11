<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-12 column ">
    <div class="row clearfix ">
        <div class="col-md-3 column">
            <div class="panel panel-default">
                <div class="panel-heading libreStatsPanelHeading">
                    <div class="panel-title">
                        <span>Данные о книге</span>
                        <a class="fa fa-caret-down pull-right libreStatsPanelHeadingIcon" href="#uniqueVisitor"
                           data-toggle="collapse"></a>
                    </div>
                </div>
                <div class="panel-body libreStatsPanelBody collapse in" id="uniqueVisitor">
                    <div class="row clearfix libreStatsPanelRow">
                        <div class="col-md-12 column libreStatsPanelValueColumn">
                            <p>Название: ${book_info.book.name}</p>
                            <p>Жанр: ${book_info.book.genre.name}</p>
                            <p>Год: ${book_info.book.date}</p>
                            <p>Isbn:${book_info.book.isbn} </p>
                            <p>Описание: ${book_info.book.description}</p>
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
                        <span>Данные об авторе</span>
                        <a class="fa fa-caret-down pull-right libreStatsPanelHeadingIcon" href="#activity"
                           data-toggle="collapse"></a>
                    </div>
                </div>
                <div class="panel-body libreStatsPanelBody collapse in" id="activity">
                    <div class="row clearfix libreStatsPanelRow">
                        <div class="col-md-12 column libreStatsPanelValueColumn">
                            <p>Имя:  ${book_info.book.author.firstName}</p>
                            <p>Фамилия:${book_info.book.author.lastName} </p>
                            <p>Отчесвто: ${book_info.book.author.middleName}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3 column">
            <div class="panel panel-default">
                <div class="panel-heading libreStatsPanelHeading">
                    <div class="panel-title">
                        <span>Экономическая часть</span>
                        <a class="fa fa-caret-down pull-right libreStatsPanelHeadingIcon" href="#activity"
                           data-toggle="collapse"></a>
                    </div>
                </div>
                <div class="panel-body libreStatsPanelBody collapse in" id="activit">
                    <div class="row clearfix libreStatsPanelRow">
                        <div class="col-md-12 column libreStatsPanelValueColumn">
                            <p>Цена: ${book_info.price}</p>
                            <p>Количество:${book_info.amount} </p>
                        </div>
                    </div>
                </div>

            </div>
            <a href="bookEdit?book_id=${book_info.book.id}" class="btn btn-warning col-md-6 pull-right" role="button">Изменеить</a>
        </div>
    </div>
</div>

