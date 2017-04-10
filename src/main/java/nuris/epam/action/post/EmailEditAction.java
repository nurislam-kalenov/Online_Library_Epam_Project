package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 09.04.2017.
 */
public class EmailEditAction implements Action {
    private boolean wrong;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        Properties properties = new Properties();

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }

        try {
            if (availableParam(EMAIL, request)) {

                customer = customerService.findCustomerById(id);
                String email = request.getParameter(EMAIL);

                if (!customerService.isLoginAvalible(email)) {
                    wrong = true;
                    request.setAttribute(EMAIL_ERROR, TRUE);

                } else {
                    checkParamValid(EMAIL, email, properties.getProperty(EMAIL_VALID), request);
                }
                customer.setEmail(email);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (wrong) {
            wrong = false;
            return new ActionResult("profile-user-edit");
        } else {
            try {
                customerService.updateCustomer(customer);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        return new ActionResult("account", true);
    }


    private boolean availableParam(String param, HttpServletRequest request) {
        if (request.getParameter(param) != null && !request.getParameter(param).isEmpty()) {
            return true;
        }
        return false;
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
