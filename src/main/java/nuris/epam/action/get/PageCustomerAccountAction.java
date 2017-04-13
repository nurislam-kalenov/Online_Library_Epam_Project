package nuris.epam.action.get;
import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exception.ServiceException;
import static nuris.epam.action.constants.Constants.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 09.04.2017.
 */
public class PageCustomerAccountAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        CustomerService customerService = new CustomerService();
        Customer customer = null;
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);
        try {
            customer = customerService.findCustomerById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute(ATT_CUSTOMER_INFO , customer);
        return new ActionResult(ACCOUNT);
    }
}
