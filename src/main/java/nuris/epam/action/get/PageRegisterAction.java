package nuris.epam.action.get;

import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 01.04.2017.
 */
public class PageRegisterAction implements Action{

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        CustomerService customerService = new CustomerService();
        try {
            request.setAttribute("cityList" , customerService.getAllCity());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("register");
    }
}
