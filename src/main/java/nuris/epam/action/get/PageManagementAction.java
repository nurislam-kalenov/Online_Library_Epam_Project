package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Management;
import nuris.epam.services.ManagementService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 15.04.2017.
 */
public class PageManagementAction implements Action {
    private boolean isActive;
    private boolean isActiveState = false;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ManagementService managementService = new ManagementService();
        int page = 1;
        int recordPerPage = 3;

        if (req.getParameter(PAGE) != null) {
            page = Integer.parseInt(req.getParameter(PAGE));
        }
        if (req.getParameter(ACTIVE) != null) {
            isActive = Boolean.valueOf(req.getParameter(ACTIVE));
            isActiveState = isActive;
        }

        try {
            List<Management> managements = managementService.getListManagement(page, recordPerPage, isActiveState);

            int noOfRecords = managementService.getManagementCount(isActiveState);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordPerPage);

            req.setAttribute(ATT_MANAGEMENTS, managements);
            req.setAttribute(ATT_NO_PAGES, noOfPages);
            req.setAttribute(ATT_CURRENT_PAGE, page);
            req.setAttribute(ATT_IS_ACTIVE_STATE, isActiveState);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(MANAGEMENT);
    }
}
