package nuris.epam.action.get;
import nuris.epam.entity.Basket;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 21.04.2017.
 */
public class AbstractBasket {
    protected Basket getBasket(HttpSession session) {
        Basket basket;

        if (session.getAttribute("basket") == null) {
            basket = new Basket();
        } else {
            basket = (Basket) session.getAttribute("basket");
        }

        return basket;
    }

}
