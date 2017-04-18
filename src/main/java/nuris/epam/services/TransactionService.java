package nuris.epam.services;

import nuris.epam.dao.*;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.*;
import nuris.epam.services.exceptions.ServiceException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                Book book = bookDao.findByBookInfo(bookInfo);
                bookInfo.setBook(book);

                if (bookInfo.getAmount() > 0 && !isAlreadyTaken(transaction) && countActiveTransaction(transaction) < 5) {
                    bookInfo.setAmount(bookInfo.getAmount() - 1);
                    daoFactory.startTransaction();
                    bookInfoDao.update(bookInfo);
                    transaction = transactionDao.insert(transaction);
                    daoFactory.commitTransaction();
                } else {
                    System.out.println("Книг нет или уже он у пользователя");
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
                    transaction.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
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
                for (Transaction tran : list) {
                    fillTransaction(tran);
                }
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

    public List<Transaction> getActiveCustomerTransaction(Transaction transaction) throws ServiceException {
        List<Transaction> transactions = new ArrayList<>();
        List<Transaction> list = findByCustomer(transaction);
        for (Transaction tran : list) {
            if (tran.getEndDate() == null) {
                transactions.add(tran);
            }
        }
        return transactions;
    }

    public List<Transaction> getListTransaction(Transaction transaction, int start, int end, boolean isActive) throws ServiceException {
        List<Transaction> list;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                list = transactionDao.getListTransactionByCustomer(transaction, start, end, isActive);
                for (Transaction trans : list) {
                    fillTransaction(trans);
                }
                return list;
            } catch (DaoException e) {
                throw new ServiceException("can't get list management", e);
            }
        }
    }

    public int getTransactionCount(Transaction transaction, boolean isActive) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                int count = transactionDao.getTransactionCountByCustomer(transaction, isActive);
                return count;
            } catch (DaoException e) {
                throw new ServiceException("can't get count transaction by customer", e);
            }
        }
    }

    public void fillTransaction(Transaction transaction) throws ServiceException {
        BookInfo bookInfo;
        Book book;
        try {
            if (transaction != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {
                    BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                    BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                    bookInfo = bookInfoDao.findByTransaction(transaction);
                    book = bookDao.findByBookInfo(bookInfo);
                    bookInfo.setBook(book);
                    transaction.setBookInfo(bookInfo);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("can't fill transaction", e);
        }
    }

    public boolean isAlreadyTaken(Transaction transaction) {
        List<Transaction> transactions;
        try {
            transactions = getActiveCustomerTransaction(transaction);
            for (Transaction tran : transactions) {
                if (tran.getBookInfo().getId() == transaction.getBookInfo().getId()) {
                    return true;
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countActiveTransaction(Transaction transaction) {
        int size = 0;
        try {
            size = getActiveCustomerTransaction(transaction).size();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return size;
    }
}
