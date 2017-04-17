package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Transaction;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static nuris.epam.action.constants.Constants.ATT_CUSTOMER_BOOK;
import static nuris.epam.action.constants.Constants.CUSTOMER_ID;
import static nuris.epam.action.constants.Constants.DEPT_CUSTOMER_BOOK;

/**
 * Created by User on 17.04.2017.
 */
public class PageDeptCustomerBook implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);

        TransactionService transactionService = new TransactionService();
        Transaction transaction = new Transaction();
        Customer customer = new Customer();
        customer.setId(id);
        transaction.setCustomer(customer);

        try {
            List<Transaction> transactions = transactionService.getActiveCustomerTransaction(transaction);
            req.setAttribute(ATT_CUSTOMER_BOOK, transactions);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(DEPT_CUSTOMER_BOOK);
    }

}