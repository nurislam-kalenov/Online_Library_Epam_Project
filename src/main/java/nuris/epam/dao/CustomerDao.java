package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;

import java.util.List;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы customer в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class CustomerDao extends BaseDao<Customer> {

    /**
     * Метод, определяет количество пользователей в таблице.
     *
     * @return Возвращает конкретное число польователей
     * @throws DaoException
     */
    public abstract int getCustomerCount() throws DaoException;

    /**
     * Метод, ищет пользователя по уникальному имени(логину).
     *
     * @param login - уникальное имя(логин) пользователя.
     * @return Возвращяает конкретного пользователя.
     * @throws DaoException
     */
    public abstract Customer getCustomer(String login) throws DaoException;

    /**
     * Метод, ищет пользователя по уникальному имени(логину) и паролю.
     *
     * @param login    - уникальное имя(логин) пользователя.
     * @param password - пароль пользователя.
     * @return Возвращяает конкретного пользователя.
     * @throws DaoException
     */
    public abstract Customer getCustomer(String login, String password) throws DaoException;

    /**
     * Метод, ищет пользователя с учетом сущности Management.
     *
     * @param management - сущность.
     * @return Возвращяает конкретного пользователя.
     * @throws DaoException
     */
    public abstract Customer findByManagement(Management management) throws DaoException;

    /**
     * Метод, возвращяет n-ое количество пользователей.
     *
     * @param start - начало поле в талице в БД
     * @param count - количесвто поле в БД, которую необходимо прогрузить.
     * @return Возвращает конкретное число пользователей
     * @throws DaoException
     */
    public abstract List<Customer> getLimitCustomers(int start, int count) throws DaoException;

}
