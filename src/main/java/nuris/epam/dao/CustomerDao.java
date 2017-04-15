package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;

/**
 * Created by User on 18.03.2017.
 */
public abstract class CustomerDao extends BaseDao<Customer> {

    public abstract int getCustomerCount() throws DaoException;

    public abstract void updateAvatar(Customer customer) throws DaoException;

    public abstract Customer getCustomer(String login) throws DaoException;

    public abstract Customer getCustomer(String login, String password) throws DaoException;

    public abstract Customer findByManagement(Management management) throws DaoException;

}
