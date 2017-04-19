package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Genre;

import java.util.List;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы book в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class BookDao extends BaseDao<Book> {
    /**
     * Метод, определяет количество книг в таблице.
     *
     * @return Возвращает конкретное число книг
     * @throws DaoException
     */
    public abstract int getBookCount() throws DaoException;

    /**
     * Метод, определяет количество книг с учетоа жанра в таблице.
     *
     * @param genre - сущность
     * @return Возвращая конкретное число книг
     * @throws DaoException
     */
    public abstract int getBookCountByGenre(Genre genre) throws DaoException;

    /**
     * Метод, возвращяет n-ое количество книг.
     *
     * @param start - начало поле в талице в БД
     * @param count - количесвто поле в БД, которую необходимо прогрузить.
     * @return Возвращает конкретное число книг
     * @throws DaoException
     */
    public abstract List<Book> getLimitBooks(int start, int count) throws DaoException;

    /**
     * Метод, возвращяет n-ое количество с учетом жанра книг.
     *
     * @param genre - сущность
     * @param start - начало поле в талице в БД
     * @param count - количесвто поле в БД, которую нелюходимо прогрузить.
     * @return Возвращает конкретное число книг
     * @throws DaoException
     */
    public abstract List<Book> getLimitBookByGenre(Genre genre, int start, int count) throws DaoException;

    /**
     * Метод, предоставляет список книг с соответствуюшим названием.
     *
     * @param name - наименование книги
     * @return Возвращает конкретное число книг
     * @throws DaoException
     */
    public abstract List<Book> findByName(String name) throws DaoException;

    /**
     * Метод, для поиск книги с учетом сущности BookInfo.
     *
     * @param bookInfo - сущность
     * @return Возвращает конкретное число книг
     * @throws DaoException
     */
    public abstract Book findByBookInfo(BookInfo bookInfo) throws DaoException;

    /**
     * Метод, для поиск книги по инкальному коду книги.
     *
     * @param isbn - уникальный код книги
     * @return Возвращает конкретную книгу
     * @throws DaoException
     */
    public abstract Book findByIsbn(String isbn) throws DaoException;


}
