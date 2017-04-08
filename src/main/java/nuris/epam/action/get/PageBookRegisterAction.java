package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.services.BookService;
import nuris.epam.services.exception.ServiceException;
import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 07.04.2017.
 */
public class PageBookRegisterAction implements Action{
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        BookService bookService = new BookService();
        try {
            request.setAttribute(GENRE_LIST , bookService.getAllGenre());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(REGISTER_BOOK);
    }
}
