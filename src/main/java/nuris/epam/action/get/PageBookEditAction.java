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
import static nuris.epam.action.constants.Constants.*;


/**
 * Created by User on 11.04.2017.
 */
public class PageBookEditAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String id = req.getParameter(BOOK_ID);
        BookService bookService = new BookService();
        try {
            BookInfo bookInfo = bookService.findByBook(TextParse.toInt(id));
            req.setAttribute(BOOK_INFO,bookInfo);
            req.setAttribute(GENRE_LIST , bookService.getAllGenre());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(BOOK_EDIT);
    }
}
