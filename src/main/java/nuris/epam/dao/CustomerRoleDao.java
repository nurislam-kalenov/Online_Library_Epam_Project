package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.CustomerRole;

/**
 * Created by User on 18.03.2017.
 */
public abstract class CustomerRoleDao extends BaseDao<CustomerRole>{

    public abstract CustomerRole findByCustomer(Customer customer) throws DaoException;

    public abstract CustomerRole findRoleByName(String nameRole) throws DaoException;

}
