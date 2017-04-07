package nuris.epam.action.post;

import nuris.epam.action.manager.Action;
import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.City;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.Encoder;
import nuris.epam.utils.SqlDate;
import nuris.epam.utils.TextParse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 30.03.2017.
 */
public class RegisterAction implements Action {

    private boolean wrong;

    @Override
    public ActionResult execute(HttpServletRequest request , HttpServletResponse resp) throws ActionException {

        CustomerService customerService = new CustomerService();
        Properties properties = new Properties();
        Customer customer = new Customer();
        Person person = new Person();
        City city = new City();

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream("validation.properties"));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }

        try {
            request.setAttribute("cityList", customerService.getAllCity());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("password_confirm");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String middleName = request.getParameter("middle_name");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String cityName = request.getParameter("city");

        try {
            if (!customerService.isLoginAvalible(email)) {
                request.setAttribute("email_error", "true");
                wrong = true;
            } else {
                checkParamValid("email", email, properties.getProperty("email.valid"), request);
            }
        } catch (ServiceException e) {
            throw new ActionException("can't check login available", e);
        }

        if (!password.equals(passwordConfirm)) {
            wrong = true;
            request.setAttribute("password_error", "true");
        } else {
            checkParamValid("password", password, properties.getProperty("password.valid"), request);
        }

        checkParamValid("first_name", firstName, properties.getProperty("name.valid"), request);
        checkParamValid("last_name", lastName, properties.getProperty("name.valid"), request);
        checkParamValid("middle_name", middleName, properties.getProperty("name.valid"), request);
        checkParamValid("phone", phone, properties.getProperty("limit.number.valid"), request);
        checkParamValid("birthday", birthday, properties.getProperty("date.valid"), request);
        checkParamValid("address", address, properties.getProperty("address.valid"), request);

        city.setId(TextParse.toInt(cityName));
        person.setCity(city);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirthday(SqlDate.stringToDate(birthday));
        person.setPhone(phone);
        person.setAdreess(address);
        customer.setPerson(person);
        customer.setEmail(email);
        customer.setPassword(Encoder.toEncode(password));

        if (wrong) {
            wrong = false;
            return new ActionResult("register");
        } else {
            try {
                customerService.registerCustomer(customer);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        return new ActionResult("welcome");
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + "_error", "true");
            wrong = true;
        }
    }
}
