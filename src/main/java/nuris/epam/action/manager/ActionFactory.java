package nuris.epam.action.manager;

import nuris.epam.action.get.*;
import nuris.epam.action.post.*;

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
        actions.put("GET/books", new PageBookAction());
        actions.put("GET/registerBook", new PageBookRegisterAction());
        actions.put("GET/set-language", new SelectLanguageAction());
        actions.put("GET/account", new PageCustomerAccountAction());
        actions.put("GET/profileEdit", new PageProfileEditAction());
        actions.put("GET/personalDataEdit", new PagePersonalDataEditAction());
        actions.put("GET/aboutBook", new PageAboutBookAction());
        actions.put("GET/bookEdit", new PageBookEditAction());
        actions.put("GET/customerBook", new PageCustomerBookAction());


        //post request
        actions.put("POST/login", new LoginAction());
        actions.put("POST/logout", new LogoutAction());
        actions.put("POST/register", new RegisterAction());
        actions.put("POST/registerBook", new BookRegisterAction());
        actions.put("POST/email-edit", new EmailEditAction());
        actions.put("POST/password-edit", new PasswordEditAction());
        actions.put("POST/personalDataEdit", new PersonalDataEditAction());
        actions.put("POST/bookEdit", new BookEditAction());
        actions.put("POST/deleteProfile", new DeleteProfileAction());
        actions.put("POST/deleteBook", new DeleteBookAction());
        actions.put("POST/takeBook", new CustomerTakeBookAction());
        actions.put("POST/returnBook", new CustomerBookAction());


    }

    public Action getAction(HttpServletRequest request) {
        if (actions == null) {
            init();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
