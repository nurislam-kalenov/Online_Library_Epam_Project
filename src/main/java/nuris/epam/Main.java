package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.*;
import nuris.epam.entity.*;
import nuris.epam.services.BookService;
import nuris.epam.services.ManagementService;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exceptions.ServiceException;

import java.util.List;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size() + " размер пула до");
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
        transaction.setId(48);
        transaction.setBookInfo(bookInfo);
        transaction.setCustomer(customer);
        MySqlManagement mySqlManagement = new MySqlManagement();
        List<Management> managements = managementService.getListManagement(1 ,1,true);
        managementService.findByTransaction(managements.get(0).getTransaction());
        managements.add( managementService.findByTransaction(managements.get(0).getTransaction()));

        MySqlCustomer mySqlCustomer = new MySqlCustomer();



        System.out.println(connectionPool.size() + " размер пула после");


    }


}
