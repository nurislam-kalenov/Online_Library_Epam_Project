package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.Encoder;

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
public class PasswordEditAction implements Action {
    private boolean wrong;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        Properties properties = new Properties();

        HttpSession session = req.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }

        String oldPassword = req.getParameter("old_pass");
        String password = req.getParameter(PASSWORD);
        String passwordConfirm = req.getParameter(PASSWORD_CONFIRM);

        try {
            customer = customerService.findCustomerById(id);

            if (!customer.getPassword().equals(Encoder.toEncode(oldPassword))) {
                wrong = true;
                req.setAttribute("old_pass_error", TRUE);
            }
            if (!password.equals(passwordConfirm)) {
                wrong = true;
                req.setAttribute(PASSWORD_ERROR, TRUE);
            } else {
                checkParamValid(PASSWORD, password, properties.getProperty(PASSWORD_VALID), req);
            }
            if (customer.getPassword().equals(Encoder.toEncode(password))) {
                wrong = true;
                req.setAttribute(PASSWORD_ERROR, TRUE);
            }
            customer.setPassword(Encoder.toEncode(password));
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


    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }
}
