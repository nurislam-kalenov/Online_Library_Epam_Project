package nuris.epam.dao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;

import java.util.List;


public abstract class AuthorDao extends BaseDao<Author>{
    public abstract Author findByBook(Book book) throws DaoException;

    public abstract List<Author> getAll() throws DaoException;

}