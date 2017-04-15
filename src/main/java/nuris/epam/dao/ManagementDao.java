package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;

import java.util.List;

/**
 * Created by User on 28.03.2017.
 */
public abstract class ManagementDao extends BaseDao<Management> {

    public abstract List<Management> getListManagement(int start, int count, boolean isActive) throws DaoException;

    public abstract List<Management> findByCustomer(int id) throws DaoException;

    public abstract Management findByTransaction(Transaction transaction) throws DaoException;

    public abstract List<Management> getListManagementByDateRange(String startDate , String endDate,int start, int count,boolean isActive ) throws DaoException;

    public abstract int getManagementCount(boolean isActive) throws DaoException;
}
