package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.mysql.MySqlBook;
import nuris.epam.dao.mysql.MySqlBookInfo;
import nuris.epam.dao.mysql.MySqlTransaction;
import nuris.epam.entity.*;
import nuris.epam.services.BookService;
import nuris.epam.services.CustomerService;
import nuris.epam.services.ManagementService;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.Encoder;
import nuris.epam.utils.SqlDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());
        BookService bookService = new BookService();
        TransactionService transactionService = new TransactionService();
        ManagementService managementService = new ManagementService();
        Management management = new Management();
        management.setId(13);
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(11);
        Customer customer = new Customer();
        customer.setId(7);
        Transaction transaction = new Transaction();
        transaction.setId(43);
        transaction.setBookInfo(bookInfo);
        transaction.setCustomer(customer);

        transactionService.returnBook(transaction , customer);
        managementService.returnBook(management);
        System.out.println(connectionPool.size());

    }
}
