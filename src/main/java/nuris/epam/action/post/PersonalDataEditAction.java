package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.City;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.SqlDate;
import nuris.epam.utils.TextParse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 10.04.2017.
 */
public class PersonalDataEditAction implements Action {
    private boolean wrong;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        Person person = new Person();
        City city = new City();

        Properties properties = new Properties();

        HttpSession session = req.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }
        try {
            customer = customerService.findCustomerById(id);
            person = customer.getPerson();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (availableParam(FIRST_NAME, req)) {
            String firstName = req.getParameter(FIRST_NAME);
            checkParamValid(FIRST_NAME, firstName, properties.getProperty(NAME_VALID), req);
            person.setFirstName(firstName);
        }
        if (availableParam(LAST_NAME, req)) {
            String lastName = req.getParameter(LAST_NAME);
            checkParamValid(LAST_NAME, lastName, properties.getProperty(NAME_VALID), req);
            person.setLastName(lastName);
        }
        if (availableParam(MIDDLE_NAME, req)) {
            String middleName = req.getParameter(MIDDLE_NAME);
            checkParamValid(MIDDLE_NAME, middleName, properties.getProperty(NAME_VALID), req);
            person.setPhone(middleName);
        }
        if (availableParam(PHONE, req)) {
            String phone = req.getParameter(PHONE);
            checkParamValid(PHONE, phone, properties.getProperty(LIMIT_NUMBER_VALID), req);
            person.setPhone(phone);
        }
        if (availableParam(BIRTHDAY, req)) {
            String birthday = req.getParameter(BIRTHDAY);
            checkParamValid(BIRTHDAY, birthday, properties.getProperty(DATE_VALID), req);
            person.setBirthday(SqlDate.stringToDate(birthday));
        }
        if (availableParam(ADDRESS, req)) {
            String address = req.getParameter(ADDRESS);
            checkParamValid(ADDRESS, address, properties.getProperty(ADDRESS_VALID), req);
            person.setAddress(address);
        }

        String cityName = req.getParameter(CITY);
        city.setId(TextParse.toInt(cityName));
        person.setCity(city);

        if (wrong) {
            wrong = false;
            return new ActionResult(PERSONAL_DATA_EDIT, true);
        } else {
            try {
                customer.setPerson(person);
                customerService.updatePerson(customer);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new ActionResult(ACCOUNT, true);
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }

    private boolean availableParam(String param, HttpServletRequest request) {
        if (request.getParameter(param) != null && !request.getParameter(param).isEmpty()) {
            return true;
        }
        return false;
    }
}
