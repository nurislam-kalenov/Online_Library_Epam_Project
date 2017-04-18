package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Genre;

import java.util.List;

/**
 * Created by User on 15.03.2017.
 */
public abstract class BookDao extends BaseDao<Book>{

    public abstract int getBookCount() throws DaoException;

    public abstract int getBookCountByGenre(Genre genre) throws DaoException;

    public abstract List<Book> getLimitBooks(int start , int count) throws DaoException;

    public abstract List<Book> getLimitBookByGenre(Genre genre , int start , int count) throws DaoException;

    public abstract List<Book> findByName(String name) throws  DaoException;

    public abstract Book findByBookInfo(BookInfo bookInfo) throws  DaoException;

    public abstract Book findByIsbn(String isbn) throws  DaoException;


}
