package nuris.epam.action.manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 29.03.2017.
 */
public class View {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public View(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void navigate(ActionResult result) {
        try {
            if (result.isRedirect()) {
                response.sendRedirect(result.getView());
            } else {
                String path =  String.format(PATH_TO_JSP+ result.getView() + JSP_FORMAT);
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
