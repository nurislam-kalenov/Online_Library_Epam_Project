package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.Sql;
import nuris.epam.entity.*;
import nuris.epam.service.BookService;
import nuris.epam.service.CustomerService;
import nuris.epam.service.ManagementService;
import nuris.epam.service.TransactionService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.SqlDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;


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
        customer.setLogin("nuris");
        customer.setPassword("123456");
        customer.setPerson(person);

        //   customerService.registerCustomer(customer);
        //System.out.println(SqlDate.currentDateAndTime());
        // System.out.println(connectionPool.size());
        CustomerService customerService = new CustomerService();
        System.out.println(customerService.getAllCity());


      System.out.println(connectionPool.size());

    }
}
