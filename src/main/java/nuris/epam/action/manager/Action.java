package nuris.epam.action.manager;

import nuris.epam.action.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 29.03.2017.
 */
public interface Action {
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException;
}
