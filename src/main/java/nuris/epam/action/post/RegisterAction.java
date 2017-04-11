package nuris.epam.action.post;

import nuris.epam.action.manager.Action;
import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.ActionResult;
import static nuris.epam.action.constants.Constants.*;
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
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) throws ActionException {

        CustomerService customerService = new CustomerService();
        Properties properties = new Properties();
        Customer customer = new Customer();
        Person person = new Person();
        City city = new City();

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }

        try {
            request.setAttribute(CITY_LIST, customerService.getAllCity());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String middleName = request.getParameter(MIDDLE_NAME);
        String phone = request.getParameter(PHONE);
        String birthday = request.getParameter(BIRTHDAY);
        String address = request.getParameter(ADDRESS);
        String cityName = request.getParameter(CITY);

        try {
            if (!customerService.isLoginAvailable(email)) {
                request.setAttribute(EMAIL_ERROR, TRUE);
                wrong = true;
            } else {
                checkParamValid(EMAIL, email, properties.getProperty(EMAIL_VALID), request);
            }
        } catch (ServiceException e) {
            throw new ActionException("can't check login available", e);
        }

        if (!password.equals(passwordConfirm)) {
            wrong = true;
            request.setAttribute(PASSWORD_ERROR, TRUE);
        } else {
            checkParamValid(PASSWORD, password, properties.getProperty(PASSWORD_VALID), request);
        }

        checkParamValid(FIRST_NAME, firstName, properties.getProperty(NAME_VALID), request);
        checkParamValid(LAST_NAME, lastName, properties.getProperty(NAME_VALID), request);
        checkParamValid(MIDDLE_NAME, middleName, properties.getProperty(NAME_VALID), request);
        checkParamValid(PHONE, phone, properties.getProperty(LIMIT_NUMBER_VALID), request);
        checkParamValid(BIRTHDAY, birthday, properties.getProperty(DATE_VALID), request);
        checkParamValid(ADDRESS, address, properties.getProperty(ADDRESS_VALID), request);

        city.setId(TextParse.toInt(cityName));
        person.setCity(city);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setBirthday(SqlDate.stringToDate(birthday));
        person.setPhone(phone);
        person.setAddress(address);
        customer.setPerson(person);
        customer.setEmail(email);
        customer.setPassword(Encoder.toEncode(password));

        if (wrong) {
            wrong = false;
            return new ActionResult(REGISTER);
        } else {
            try {
                customerService.registerCustomer(customer);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new ActionResult(WELCOME);
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }
}
