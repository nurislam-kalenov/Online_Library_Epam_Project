package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.mysql.MySqlBook;
import nuris.epam.dao.mysql.MySqlBookInfo;
import nuris.epam.entity.*;
import nuris.epam.services.BookService;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.Encoder;
import nuris.epam.utils.SqlDate;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());
        BookService bookService = new BookService();

        BookInfo bookInfo1 = bookService.findByBook(80);

        Author author = bookInfo1.getBook().getAuthor();
        author.setFirstName("Pushkin");

        System.out.println(bookInfo1);

        System.out.println(connectionPool.size());

        MySqlBook mySqlBook = new MySqlBook();
        mySqlBook.sql();
    }
}
