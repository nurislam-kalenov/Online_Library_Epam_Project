package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static nuris.epam.action.constants.Constants.*;


/**
 * Created by User on 09.04.2017.
 */
public class PageProfileEditAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        CustomerService customerService = new CustomerService();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);
        try {
            Customer customer = customerService.findCustomerById(id);
            request.setAttribute(EMAIL , customer.getEmail());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(PROFILE_EDIT);
    }
}
