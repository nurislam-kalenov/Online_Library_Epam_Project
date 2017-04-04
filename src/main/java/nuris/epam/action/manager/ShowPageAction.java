package nuris.epam.action.manager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 30.03.2017.
 */
public class ShowPageAction implements Action {

    private ActionResult result;

    public ShowPageAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest request) {
        return result;
    }
}
