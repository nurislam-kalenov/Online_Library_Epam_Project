package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.BookInfo;
import nuris.epam.services.BookService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.TextParse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 11.04.2017.
 */
public class PageAboutBookAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String id = req.getParameter("book_id");
        BookService bookService = new BookService();
        BookInfo bookInfo = null;
        try {
            bookInfo = bookService.findByBook(TextParse.toInt(id));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("book_info" , bookInfo);

        return new ActionResult("aboutBook");
    }
}
