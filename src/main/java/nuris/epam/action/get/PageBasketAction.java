package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 21.04.2017.
 */
public class PageBasketAction extends AbstractBasket implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Basket basket = getBasket(req.getSession());
        req.setAttribute("basket" , basket);
        System.out.println(basket.getBooks().size());
        return new ActionResult("basket");
    }
}
