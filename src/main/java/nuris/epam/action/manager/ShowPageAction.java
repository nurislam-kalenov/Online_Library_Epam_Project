package nuris.epam.action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 30.03.2017.
 */
public class ShowPageAction implements Action {

    private ActionResult result;

    public ShowPageAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest request , HttpServletResponse resp) {
        return result;
    }
}
