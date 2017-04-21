package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.get.AbstractBasket;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Basket;
import nuris.epam.entity.BookInfo;
import nuris.epam.services.BookService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 21.04.2017.
 */
public class PutBookIntoBasket extends AbstractBasket implements Action{
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        BookService bookService = new BookService();
        HttpSession session = req.getSession();
        Basket basket = getBasket(session);
        try {
        int bookId = Integer.parseInt(req.getParameter("book_id"));

            basket.add(bookService.findByBook(bookId));
            session.setAttribute("basket", basket);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ActionResult("books" , true);
    }
}
