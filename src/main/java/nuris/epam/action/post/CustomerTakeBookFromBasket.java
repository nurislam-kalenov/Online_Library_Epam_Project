package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.get.AbstractBasket;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Basket;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Transaction;
import nuris.epam.services.BookService;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 21.04.2017.
 */
public class CustomerTakeBookFromBasket extends AbstractBasket implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        TransactionService transactionService = new TransactionService();
        BookService bookService = new BookService();
        Transaction transaction = new Transaction();
        BookInfo bookInfo ;
        Customer customer = new Customer();

        HttpSession session = req.getSession();
        Basket basket = getBasket(session);
        int id = (int) session.getAttribute(ATT_CUSTOMER_ID);
        int bookInfoId = Integer.parseInt(req.getParameter(ATT_BOOK_INFO_ID));
        int bookId = Integer.parseInt(req.getParameter(BOOK_ID));
        try {

            bookInfo = bookService.findByBook(bookId);
            customer.setId(id);
            transaction.setCustomer(customer);
            transaction.setBookInfo(bookInfo);


            if (transactionService.isAlreadyTaken(transaction) || transactionService.countActiveTransaction(transaction) > MAX_COUNT_BOOKS) {
                req.setAttribute(ATT_TAKE_ERROR , true);
                return new ActionResult(BASKET);

            } else {
                basket.delete(bookInfoId);
                transactionService.takeBook(transaction);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(BASKET, true);

    }
}
