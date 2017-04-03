package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Transaction;

/**
 * Created by User on 26.03.2017.
 */
public  abstract class BookInfoDao extends BaseDao<BookInfo>{
    public abstract BookInfo findByBook(Book book) throws DaoException;
    public abstract BookInfo findByTransaction(Transaction transaction) throws DaoException;
}
