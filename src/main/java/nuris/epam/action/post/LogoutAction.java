package nuris.epam.action.post;


import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 29.03.2017.
 */
public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ActionResult(WELCOME, true);
    }
}
