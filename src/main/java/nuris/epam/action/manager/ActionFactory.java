package nuris.epam.action.manager;

import nuris.epam.action.get.PageBookAction;
import nuris.epam.action.get.PageBookRegisterAction;
import nuris.epam.action.get.PageRegisterAction;
import nuris.epam.action.get.SelectLanguageAction;
import nuris.epam.action.post.BookRegisterAction;
import nuris.epam.action.post.LoginAction;
import nuris.epam.action.post.LogoutAction;
import nuris.epam.action.post.RegisterAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 29.03.2017.
 */
public class ActionFactory {
    private Map<String, Action> actions;

    public void init() {
        actions = new HashMap<>();
        //get request
        actions.put("GET/welcome", new ShowPageAction("welcome"));
        actions.put("GET/register", new PageRegisterAction());
        actions.put("GET/books",new PageBookAction());
        actions.put("GET/register-book",new PageBookRegisterAction());
        actions.put("GET/set-language", new SelectLanguageAction());

        //post request
        actions.put("POST/login", new LoginAction());
        actions.put("POST/logout", new LogoutAction());
        actions.put("POST/register",new RegisterAction());
        actions.put("POST/register-book",new BookRegisterAction());

    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            init();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
