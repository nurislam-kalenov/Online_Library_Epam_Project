package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Transaction;
import nuris.epam.services.BookService;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exceptions.ServiceException;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 11.04.2017.
 */
public class PageAboutBookAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        TransactionService transactionService = new TransactionService();
        BookService bookService = new BookService();
        Transaction transaction = new Transaction();
        Customer customer = new Customer();
        BookInfo bookInfo = null;

        String id = req.getParameter(BOOK_ID);

        HttpSession session = req.getSession();
        int customerId = (int) session.getAttribute(ATT_CUSTOMER_ID);
        customer.setId(customerId);
        transaction.setCustomer(customer);
        try {
            bookInfo = bookService.findByBook(Integer.parseInt(id));
            transaction.setBookInfo(bookInfo);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (transactionService.countActiveTransaction(transaction) > MAX_COUNT_BOOKS) {
            req.setAttribute(ATT_COUNT_ERROR, true);
        }
        if (bookInfo.getAmount() <= MIN_COUNT_BOOKS) {
            req.setAttribute(ATT_OVER_ERROR, true);

        }

        if (transactionService.isAlreadyTaken(transaction)) {
            req.setAttribute(ATT_TAKE_ERROR, true);
        }

        req.setAttribute(BOOK_INFO, bookInfo);

        return new ActionResult(ABOUT_BOOK);
    }
}
