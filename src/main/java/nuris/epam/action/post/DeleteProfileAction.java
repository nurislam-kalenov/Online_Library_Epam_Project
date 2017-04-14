package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.TextParse;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 10.04.2017.
 */
public class DeleteProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        String id = req.getParameter(DELETE_ID);
        customer.setId(TextParse.toInt(id));

        try {
            customerService.deleteCustomer(customer);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ActionResult(WELCOME);
    }
}
