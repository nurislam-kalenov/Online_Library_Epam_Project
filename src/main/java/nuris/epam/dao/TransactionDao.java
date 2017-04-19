package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;

import java.util.List;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы transaction в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class TransactionDao extends BaseDao<Transaction> {

    /**
     * Метод, прелоставляет список сущностей Transaction, найденных с учетом сущности Customer.
     *
     * @param transaction - сущность содержащая данные о сущности Customer
     * @return Возвращаяет список сущностей Transaction
     * @throws DaoException
     */
    public abstract List<Transaction> findByCustomer(Transaction transaction) throws DaoException;

    /**
     * Метод, возвращяет n-ое количество сущностей Transaction.
     *
     * @param start    - начало поле в талице в БД
     * @param count    - количесвто поле в БД, которую необходимо прогрузить.
     * @param isActive - состаяние сущности Transaction
     * @return Возвращает конкретное число Transaction
     * @throws DaoException
     */
    public abstract List<Transaction> getListTransactionByCustomer(Transaction transaction, int start, int count, boolean isActive) throws DaoException;

    /**
     * Метод, прелоставляет список сущностей Transaction, найденных с учетом сущности Management.
     *
     * @param management - сущность содержащая данные о сущности Customer
     * @return Возвращаяет список сущностей Transaction
     * @throws DaoException
     */
    public abstract Transaction findByManagement(Management management) throws DaoException;

    /**
     * Метод, прелоставляет общее количество сущностей Transaction c учетом их состаяние и принадлежности к сущности Customer(активный, неактивный).
     *
     * @param transaction - содержить данные о сущности Customer.
     * @param isActive    - состояние сущности Transaction
     * @return Возвращаяет список сущностей Management
     * @throws DaoException
     */
    public abstract int getTransactionCountByCustomer(Transaction transaction, boolean isActive) throws DaoException;


}
