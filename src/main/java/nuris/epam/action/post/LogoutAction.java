package nuris.epam.action.post;


import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 29.03.2017.
 */
public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        return new ActionResult("welcome");
    }
}
