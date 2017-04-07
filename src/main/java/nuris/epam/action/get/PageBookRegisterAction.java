package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.service.BookService;
import nuris.epam.service.exception.ServiceException;

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
            request.setAttribute("genreList" , bookService.getAllGenre());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("register-book");
    }
}
