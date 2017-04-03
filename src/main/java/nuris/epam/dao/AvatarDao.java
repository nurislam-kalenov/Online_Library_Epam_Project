package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Avatar;
import nuris.epam.entity.Customer;

/**
 * Created by User on 18.03.2017.
 */
public abstract class AvatarDao extends BaseDao<Avatar> {
    public abstract Avatar findByCustomer(Customer customer) throws DaoException;
}
