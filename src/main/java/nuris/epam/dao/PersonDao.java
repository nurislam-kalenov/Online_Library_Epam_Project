package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы person в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class PersonDao extends BaseDao<Person> {

    /**
     * Метод, ищет сущность Person с учетом сущности Book.
     *
     * @param customer - сущность.
     * @return Возвращяает конкретную сущность.
     * @throws DaoException
     */
    public abstract Person findByCustomer(Customer customer) throws DaoException;
}
