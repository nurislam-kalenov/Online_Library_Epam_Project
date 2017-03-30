package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;

/**
 * Created by User on 17.03.2017.
 */
public abstract class PersonDao extends BaseDao<Person> {
    public abstract Person findByCustomer(Customer customer) throws DaoException;
}
