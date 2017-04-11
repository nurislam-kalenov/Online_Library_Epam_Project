package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.BookInfoDao;
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
        BookInfo bookInfo = new BookInfo();
        Author author = new Author();
        Book book = new Book();
        Genre genre = new Genre();
        genre.setId(4);
        author.setFirstName("Kalenov");
        author.setLastName("Nurislam");
        author.setMiddleName("Temirovich");
        book.setIsbn("123332221");
        book.setDescription("Есть на свете такой человек");
        book.setName("Мститель");
        book.setDate(SqlDate.stringToDate("1906-02-18"));
        book.setGenre(genre);
        book.setAuthor(author);
        bookInfo.setAmount(5);
        bookInfo.setPrice(100);
        bookInfo.setBook(book);

        MySqlBookInfo mySqlBookInfo = new MySqlBookInfo();

        Customer customer = new Customer();
        customer.setId(391);
       BookInfo bookInfo1 = bookService.findByBook(67);
        System.out.println(bookInfo1);
        System.out.println(connectionPool.size());

    }
}
