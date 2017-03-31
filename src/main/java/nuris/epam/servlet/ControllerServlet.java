package nuris.epam.servlet;


import nuris.epam.action.Action;
import nuris.epam.action.ActionFactory;
import nuris.epam.action.ActionResult;
import nuris.epam.action.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 29.03.2017.
 */
@WebServlet(name = "controller", urlPatterns = "/kz/*")
public class ControllerServlet extends HttpServlet {
    ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = actionFactory.getAction(req);
        ActionResult result = action.execute(req);
        View view = new View(req, resp);
        view.navigate(result);
    }
}