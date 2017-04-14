package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Management;

import java.util.List;

/**
 * Created by User on 28.03.2017.
 */
public abstract class ManagementDao extends BaseDao<Management> {

    public abstract List<Management> getLimitManagement(int start, int count) throws DaoException;

    public abstract List<Management> findByCustomer(int id) throws DaoException;

}
