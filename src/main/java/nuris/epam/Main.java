package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
import nuris.epam.service.BookService;
import nuris.epam.service.ManagementService;
import nuris.epam.service.TransactionService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());

        Genre genre = new Genre();
        genre.setId(1);
        Author author = new Author();
        author.setFirstName("Nuris");
        author.setLastName("Kalenov");
        author.setMiddleName("Temirovich");
        Book book = new Book();
        book.setGenre(genre);
        book.setAuthor(author);
        book.setDate(SqlDate.currentDateAndTime());
        book.setDescription("Давный давно");
        book.setIsbn("1236644");
        book.setName("Window");

        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(10);


        BookService bookService = new BookService();

        TransactionService transactionService = new TransactionService();

        Customer customer = new Customer();
        customer.setId(7);
        Transaction transaction =  new Transaction();
        transaction.setId(13);
        transaction.setCustomer(customer);
        transaction.setBookInfo(bookInfo);

        Management management = new Management();
        management.setId(7);
        ManagementService managementService = new ManagementService();
        managementService.returnBook(management);
        System.out.println(connectionPool.size());

    }
}
