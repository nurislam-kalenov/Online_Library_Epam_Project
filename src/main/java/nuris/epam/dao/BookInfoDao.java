package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Transaction;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы book_info в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class BookInfoDao extends BaseDao<BookInfo> {

    /**
     * Метод, для поиск книги по уикальному номеру в таблице.
     *
     * @param id - уникальный номер
     * @return Возвращает конкрутную сущность BookInfo.
     * @throws DaoException
     */
    public abstract BookInfo findByBook(int id) throws DaoException;

    /**
     * Метод, для поиск книги с учетом сущности Transaction.
     *
     * @param transaction - сущность
     * @return Возвращает конкрутную сущность BookInfo.
     * @throws DaoException
     */
    public abstract BookInfo findByTransaction(Transaction transaction) throws DaoException;
}
