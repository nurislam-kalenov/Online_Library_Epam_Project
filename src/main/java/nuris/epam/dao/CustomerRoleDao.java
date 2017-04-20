package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы role в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class CustomerRoleDao extends BaseDao<CustomerRole>{
    /**
     * Метод, ищет роль с учетом сущности Customer.
     *
     * @param customer - сущность.
     * @return Возвращает конкретную роль.
     * @throws DaoException
     */
    public abstract CustomerRole findByCustomer(Customer customer) throws DaoException;
    /**
     * Метод, ищет роль по наименованю роли.
     *
     * @param nameRole - наименование роли.
     * @return Возвращает конкретную роль.
     * @throws DaoException
     */
    public abstract CustomerRole findRoleByName(String nameRole) throws DaoException;

}
