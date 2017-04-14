package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.BookInfo;
import nuris.epam.services.BookService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.TextParse;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 13.04.2017.
 */
public class DeleteBookAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        BookService bookService = new BookService();
        BookInfo bookInfo = new BookInfo();
        String id = req.getParameter(DELETE_ID);
        bookInfo.setId(TextParse.toInt(id));

        try {
            bookService.deleteBook(bookInfo);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ActionResult(BOOKS, true);
    }
}
