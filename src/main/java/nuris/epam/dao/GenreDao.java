package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;

import java.util.List;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы genre в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class GenreDao extends BaseDao<Genre> {

    /**
     * Метод, ищет жанр с учетом сущности Book.
     *
     * @param book - сущность.
     * @return Возвращяает конкретный жанр.
     * @throws DaoException
     */
    public abstract Genre findByBook(Book book) throws DaoException;

    /**
     * Метод, предоставляет список жанров.
     *
     * @return Возвращяает конкретный жанр.
     * @throws DaoException
     */
    public abstract List<Genre> getAll() throws DaoException;

}
