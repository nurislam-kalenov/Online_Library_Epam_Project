package nuris.epam.services;

import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.ManagementDao;
import nuris.epam.dao.TransactionDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;
import nuris.epam.services.exceptions.ServiceException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
                    management.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
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

    public List<Management> getListManagement(int start, int end, boolean isActive) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                List<Management> list = managementDao.getListManagement(start, end, isActive);
                for (Management management : list) {
                    fillManagement(management);
                }
                return list;
            } catch (DaoException e) {
                throw new ServiceException("can't get list management", e);
            }
        }
    }

    public List<Management> getListManagementByDate(String startDate, String endDate, int start, int end, boolean isActive) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                List<Management> list = managementDao.getListManagementByDateRange(startDate, endDate, start, end, isActive);
                for (Management management : list) {
                    fillManagement(management);
                }
                return list;
            } catch (DaoException e) {
                throw new ServiceException("can't get list management by date", e);
            }
        }
    }

    public Management findByTransaction(Transaction transaction) throws ServiceException {
        Management management = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                management = managementDao.findByTransaction(transaction);
                return management;
            } catch (DaoException e) {
                e.printStackTrace();
            }
            return management;
        }
    }

    public List<Management> findByCustomer(int id) throws ServiceException {
        List<Management> list = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                list = managementDao.findByCustomer(id);
                for (Management management : list) {
                    fillManagement(management);
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int getManagementCount(boolean isActive) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                ManagementDao managementDao = (ManagementDao) daoFactory.getDao(daoFactory.typeDao().getManagementDao());
                int count = managementDao.getManagementCount(isActive);
                return count;
            } catch (DaoException e) {
                throw new ServiceException("can't get count book", e);
            }
        }
    }

    private void fillManagement(Management management) throws ServiceException {
        TransactionService transactionService = new TransactionService();
        CustomerService customerService = new CustomerService();
        Customer customer = null;
        if (management != null) {
            try (DaoFactory daoFactory = new DaoFactory()) {
                try {
                    TransactionDao transactionDao = (TransactionDao) daoFactory.getDao(daoFactory.typeDao().getTransactionDao());
                    customer = customerService.findByManagement(management);
                    Transaction transaction = transactionDao.findByManagement(management);
                    transactionService.fillTransaction(transaction);
                    transaction.setCustomer(customer);
                    management.setTransaction(transaction);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
