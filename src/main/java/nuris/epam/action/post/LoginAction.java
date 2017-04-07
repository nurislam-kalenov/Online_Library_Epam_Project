package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;
import nuris.epam.util.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 29.03.2017.
 */
public class LoginAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse resp) {
        CustomerService customerService = new CustomerService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
             Customer customer = customerService.findByLoginPassword(login, Encoder.toEncode(password));

            if(customer != null){
                HttpSession session = request.getSession();
                session.setAttribute("customerId" , customer.getId());
                session.setAttribute("role" , customer.getCustomerRole().getName());
                session.setAttribute("name" , customer.getPerson().getFirstName());
                return new ActionResult("books" , true);
            }else{
                return new ActionResult("welcome", true);
            }
        } catch (ServiceException e) {
            new ActionException("can't find customer by login and password", e);
        }
        return null;
    }
}
