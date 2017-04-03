package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;

import java.util.List;

/**
 * Created by User on 14.03.2017.
 */
public abstract class GenreDao extends BaseDao<Genre> {

    public abstract Genre findByBook(Book book) throws DaoException;

    public abstract List<Genre> getAll() throws DaoException;

}
