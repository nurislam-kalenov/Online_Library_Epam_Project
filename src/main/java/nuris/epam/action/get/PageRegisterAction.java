package nuris.epam.action.get;

import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.services.CustomerService;
import nuris.epam.services.exceptions.ServiceException;
import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 01.04.2017.
 */
public class PageRegisterAction implements Action{

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        CustomerService customerService = new CustomerService();
        try {
            req.setAttribute(CITY_LIST , customerService.getAllCity());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(REGISTER);
    }
}
