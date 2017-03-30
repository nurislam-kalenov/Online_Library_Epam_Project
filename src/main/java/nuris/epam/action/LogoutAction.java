package nuris.epam.action;

import nuris.epam.action.manage.Action;
import nuris.epam.action.manage.ActionResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 29.03.2017.
 */
public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) {
        System.out.println("logout");
        return new ActionResult("welcom");
    }
}
