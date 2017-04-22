package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exceptions.ServiceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 10.04.2017.
 */
public class PagePersonalDataEditAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        CustomerService customerService = new CustomerService();

        HttpSession session = req.getSession();
        int id = (int) session.getAttribute(ATT_CUSTOMER_ID);

        try {
            Customer customer = customerService.findCustomerById(id);
            Person person = customer.getPerson();

            req.setAttribute(FIRST_NAME, person.getFirstName());
            req.setAttribute(LAST_NAME, person.getLastName());
            req.setAttribute(MIDDLE_NAME, person.getMiddleName());
            req.setAttribute(PHONE, person.getPhone());
            req.setAttribute(BIRTHDAY, person.getBirthday());
            req.setAttribute(ADDRESS, person.getAddress());
            req.setAttribute(CITY_LIST, customerService.getAllCity());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(PERSONAL_DATA_EDIT);
    }
}
