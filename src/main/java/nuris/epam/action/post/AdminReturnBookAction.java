package nuris.epam.action.post;
import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Management;
import nuris.epam.services.ManagementService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 16.04.2017.
 */

public class AdminReturnBookAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int id = Integer.parseInt(req.getParameter("management_id"));
        Management management = new Management();
        management.setId(id);
        ManagementService managementService = new ManagementService();
        try {
            managementService.returnBook(management);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("management" ,true);
    }
}
