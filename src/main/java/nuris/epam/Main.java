package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.MySqlBook;
import nuris.epam.entity.*;
import nuris.epam.service.BookService;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.util.SqlDate;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws ServiceException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(connectionPool.size());
        City city = new City();
        Person person = new Person();
        Customer customer = new Customer();

        city.setId(1);
        person.setFirstName("Nurislam");
        person.setLastName("Kalenov");
        person.setMiddleName("Temirovich");
        person.setAdreess("Lugavia");
        person.setBirthday(SqlDate.stringToDate("1995-02-08"));
        person.setPhone("87023876353");
        person.setCity(city);
        customer.setEmail("nuris@ergreg");
        customer.setPassword("123456");
        customer.setPerson(person);

        CustomerService customerService = new CustomerService();

        //   customerService.registerCustomer(customer);
        //System.out.println(SqlDate.currentDateAndTime());
        // System.out.println(connectionPool.size());

        Customer customer1 = customerService.findByLoginPassword("kl@gmail.com", "");
        Genre genre = new Genre();
        BookService bookService = new BookService();
        int i = bookService.getBookCountByGenre(genre);
        System.out.println(i + "количество книжек");
        System.out.println(connectionPool.size());
        MySqlBook mySqlBook = new MySqlBook();
        mySqlBook.sql();

    }
}
