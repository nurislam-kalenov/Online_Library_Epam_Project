package nuris.epam.action.get;

import nuris.epam.action.manage.Action;
import nuris.epam.action.manage.ActionResult;
import nuris.epam.connection.ConnectionPool;
import nuris.epam.entity.City;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 01.04.2017.
 */
public class ShowRegisterPageAction implements Action{

    @Override
    public ActionResult execute(HttpServletRequest request) {
        CustomerService customerService = new CustomerService();
        try {
            request.setAttribute("cityList" , customerService.getAllCity());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("register");
    }
}
