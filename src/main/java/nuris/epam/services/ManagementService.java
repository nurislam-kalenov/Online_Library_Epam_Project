package nuris.epam.services;

import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.ManagementDao;
import nuris.epam.dao.TransactionDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.SqlDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by User on 28.03.2017.
 */
public class ManagementService {

    public void returnBook(Management management) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookService bookService = new BookService();

                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());

                Transaction transaction = transactionDao.findByManagement(management);
                BookInfo bookInfo = bookInfoDao.findByTransaction(transaction);
                management = managementDao.findById(management.getId());
                bookInfo.setAmount(bookInfo.getAmount() + 1);
                transaction.setBookInfo(bookInfo);
                management.setTransaction(transaction);

                if (management.getReturnDate() == null) {
                    management.setReturnDate( Timestamp.valueOf(LocalDateTime.now()));
                    daoFactory.startTransaction();
                    bookService.updateBookInfo(bookInfo);
                    managementDao.update(management);
                    daoFactory.commitTransaction();
                } else {
                    System.out.println("Operation already executed");
                }
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    e1.printStackTrace();
                }
                throw new ServiceException("can't insert transaction ", e);
            }
        }
    }

    public Management findByCustomer() {
        return null;
    }

    // статистика активыных
    public int getActiveTransaction() {
        return 0;
    }
}
