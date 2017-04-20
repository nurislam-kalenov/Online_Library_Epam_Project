package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;

import java.util.List;

/**
 * Абстрактый класс , описывает дополнительные запросы для таблицы author в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class AuthorDao extends BaseDao<Author> {

    /**
     * Метод ищет при помощи сущности Book и возвращает сущность Author.
     *
     * @param book - сущность.
     * @return Возвращает конкрную сущность.
     * @throws DaoException
     */
    public abstract Author findByBook(Book book) throws DaoException;

    /**
     * Метод для получение списка сущностей
     *
     * @return Возвращает список сущностей.
     * @throws DaoException
     */
    public abstract List<Author> getAll() throws DaoException;

}