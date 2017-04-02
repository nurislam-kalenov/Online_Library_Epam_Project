package nuris.epam;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.*;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.util.SqlDate;
import nuris.epam.util.TextParse;


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
        System.out.println(customerService.getAllCity());
      System.out.println(connectionPool.size());

      String betterIdea, userIdea;
      userIdea = "Welfnwfwfewfwfeflbwiebi rgere erg erger ergerge Srgreg 56654 ";
      System.out.println(TextParse.upperCaseFirstLetter(userIdea));
    }
}
