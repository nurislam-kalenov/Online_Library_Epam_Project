package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 18.04.2017.
 */
public class PageReadersAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        CustomerService customerService = new CustomerService();
        int page = 1;
        int recordPerPage = 10;

        if (req.getParameter(PAGE) != null) {
            page = Integer.parseInt(req.getParameter(PAGE));
        }
        try {
            List<Customer> readers = customerService.getListCustomers(page , recordPerPage);

            int noOfRecords = customerService.customerCount();
            int noOfPages = (int) Math.ceil(noOfRecords *CONVERT_TO_DOUBLE / recordPerPage);

            req.setAttribute(ATT_READERS, readers);
            req.setAttribute(ATT_NO_PAGES, noOfPages);
            req.setAttribute(ATT_CURRENT_PAGE, page);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ActionResult(READERS);
    }
}
