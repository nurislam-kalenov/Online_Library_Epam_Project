package nuris.epam.action;

import nuris.epam.action.post.LoginAction;
import nuris.epam.action.post.LogoutAction;

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
        actions.put("POST/login", new LoginAction());
        actions.put("POST/logout", new LogoutAction());
        actions.put("GET/welcome", new ShowPageAction("welcome"));

    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            init();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
