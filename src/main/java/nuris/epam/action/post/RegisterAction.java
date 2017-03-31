package nuris.epam.action.post;

import nuris.epam.action.Action;
import nuris.epam.action.ActionResult;
import nuris.epam.entity.City;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.service.util.ConvertString;
import nuris.epam.service.util.SqlDate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 30.03.2017.
 */
public class RegisterAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) {
        CustomerService customerService = new CustomerService();

        Customer customer = new Customer();
        Person person = new Person();
        City city = new City();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String middleName = request.getParameter("middle_name");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String cityName = request.getParameter("city");

        city.setId(ConvertString.toInt(cityName));
        person.setCity(city);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirthday(SqlDate.stringToDate(birthday));
        person.setPhone(phone);
        person.setAdreess(address);
        customer.setPerson(person);
        customer.setLogin(login);
        customer.setPassword(password);

        try {
            customerService.registerCustomer(customer);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("welcome");
    }
}
