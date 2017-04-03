package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;

import java.util.List;

/**
 * Created by User on 27.03.2017.
 */
public abstract class TransactionDao extends BaseDao<Transaction> {

    public abstract List<Transaction> findByCustomer(Transaction transaction) throws DaoException;

    public abstract Transaction findByManagement(Management management) throws DaoException;




}
