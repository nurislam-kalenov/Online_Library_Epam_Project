package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exceptions.ServiceException;
import nuris.epam.utils.Encoder;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 29.03.2017.
 */
public class LoginAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        CustomerService customerService = new CustomerService();
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        try {
            Customer customer = customerService.findByLoginPassword(login, Encoder.toEncode(password));

            if (customer != null) {
                HttpSession session = req.getSession();
                session.setAttribute(CUSTOMER_ID, customer.getId());
                session.setAttribute(ROLE, customer.getCustomerRole().getName());
                session.setAttribute(NAME, customer.getPerson().getFirstName());
                return new ActionResult(BOOKS, true);
            } else {
                req.setAttribute(LOGIN_ERROR ,true);
                return new ActionResult(WELCOME);
            }
        } catch (ServiceException e) {
            new ActionException("can't find customer by login and password", e);
        }
        return null;
    }
}
