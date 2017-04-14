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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


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
        customer.setId(395);
        Transaction transaction = new Transaction();
        transaction.setId(43);
        transaction.setBookInfo(bookInfo);
        transaction.setCustomer(customer);



        List<Transaction> transactions =  transactionService.getActiveCustomerTransaction(transaction , true);

        System.out.println(transactions);


    }





}
