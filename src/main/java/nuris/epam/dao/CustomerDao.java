package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;

import java.util.List;

/**
 * Created by User on 18.03.2017.
 */
public abstract class CustomerDao extends BaseDao<Customer> {

    public abstract int getCustomerCount() throws DaoException;

    public abstract Customer getCustomer(String login) throws DaoException;

    public abstract Customer getCustomer(String login, String password) throws DaoException;

    public abstract Customer findByManagement(Management management) throws DaoException;

    public abstract List<Customer> getLimitCustomers(int start, int count) throws DaoException;

    public abstract Customer findByFirstNameAndLastName(Customer customer) throws DaoException;

}
