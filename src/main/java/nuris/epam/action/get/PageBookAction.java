package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Book;
import nuris.epam.service.BookService;
import nuris.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 06.04.2017.
 */
public class PageBookAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        BookService bookService = new BookService();

        int page = 1;
        int recordPerPage = 3;

        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                System.out.println(page + "Number Page");
            }

            List<Book> books = bookService.getListBook(page, recordPerPage);
            int noOfRecords = bookService.getBookCount();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordPerPage);
            request.setAttribute("books", books);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("books");
    }
}
