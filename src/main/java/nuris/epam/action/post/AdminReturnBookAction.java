package nuris.epam.action.post;
import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Management;
import nuris.epam.services.ManagementService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 16.04.2017.
 */

public class AdminReturnBookAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ManagementService managementService = new ManagementService();
        Management management = new Management();

        int id = Integer.parseInt(req.getParameter(MANAGEMENT_ID));
        management.setId(id);
        try {
            managementService.returnBook(management);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(MANAGEMENT,true);
    }
}
