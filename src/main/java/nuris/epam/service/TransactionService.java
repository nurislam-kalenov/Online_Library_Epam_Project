package nuris.epam.service;

import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.ManagementDao;
import nuris.epam.dao.TransactionDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;

import java.util.List;


/**
 * Created by User on 27.03.2017.
 */
public class TransactionService {

    public Transaction takeBook(Transaction transaction) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookService bookService = new BookService();
                BookInfo bookInfo = bookService.findById(transaction.getBookInfo().getId());
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());

                if (bookInfo.getAmount() > 0) {
                    bookInfo.setAmount(bookInfo.getAmount() - 1);
                    daoFactory.startTransaction();
                    bookService.updateBookInfo(bookInfo);
                    transaction.setStartDate(SqlDate.currentDateAndTime());
                    transaction = transactionDao.insert(transaction);
                    daoFactory.commitTransaction();
                } else {
                    System.out.println("Книг нет");
                }
                return transaction;
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    e1.printStackTrace();
                }
                throw new ServiceException("can't take book ", e);
            }
        }
    }

    public Transaction returnBook(Transaction transaction, Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                Management management = new Management();

                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());

                BookInfo bookInfo = bookInfoDao.findByTransaction(transaction);
                transaction = transactionDao.findById(transaction.getId());
                management.setTransaction(transaction);
                transaction.setBookInfo(bookInfo);
                transaction.setCustomer(customer);

                if (transaction.getEndDate() == null) {
                    transaction.setEndDate(SqlDate.currentDateAndTime());
                    daoFactory.startTransaction();
                    transactionDao.update(transaction);
                    managementDao.insert(management);
                    daoFactory.commitTransaction();

                } else {
                    System.out.println("Operation already executed");
                }
                return transaction;
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    e1.printStackTrace();
                }
                throw new ServiceException("can't return book ", e);
            }
        }
    }

    public List<Transaction> findByCustomer(Transaction transaction) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                List<Transaction> list = transactionDao.findByCustomer(transaction);
                return list;
            } catch (DaoException e) {
                throw new ServiceException("can't get list of customer", e);
            }
        }
    }

    public void deleteTransaction(Transaction transaction) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                transactionDao.delete(transaction);
            } catch (DaoException e) {
                throw new ServiceException("can't delete transaction", e);
            }
        }
    }

    //проверка на наличие книг
    public boolean isAlreadyTaken(Transaction transaction) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                return true;
            } catch (DaoException e) {
                throw new ServiceException("can't find similar value", e);
            }
        }
    }

    // статистика активыных
    public int getActiveTransaction() {
        return 0;
    }

    //что бы выводить название книг
    public int findByBookInfo() {
        return 0;
    }
}
