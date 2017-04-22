package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.get.AbstractBasket;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 21.04.2017.
 */
public class DeleteBookFromBasketAction extends AbstractBasket implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        Basket basket = getBasket(session);

        if (req.getParameter(ATT_DELETE_BOOK_ID) != null) {
            int bookId = Integer.parseInt(req.getParameter(ATT_DELETE_BOOK_ID));
            basket.delete(bookId);
        }
        if (req.getParameter(ATT_ALL_DELETE_BOOK_ID) != null) {
            basket.deleteAll();
        }

        return new ActionResult(BASKET, true);
    }
}
