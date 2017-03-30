package nuris.epam.action;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 29.03.2017.
 */
public interface Action {
    ActionResult execute(HttpServletRequest request);
}
