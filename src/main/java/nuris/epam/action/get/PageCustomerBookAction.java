package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;
import nuris.epam.services.ManagementService;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 13.04.2017.
 */
public class PageCustomerBookAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);

        TransactionService transactionService = new TransactionService();
        ManagementService managementService = new ManagementService();
        Transaction transaction = new Transaction();
        Management management = null;
        Customer customer = new Customer();
        List<Transaction> transactions = new ArrayList<>();
        customer.setId(id);
        transaction.setCustomer(customer);

        try {
            List<Transaction> list = transactionService.getActiveCustomerTransaction(transaction, false);

            for (Transaction trans : list) {
                management = managementService.findByTransaction(trans);
                if (trans.getEndDate() != null && management.getReturnDate() == null) {
                    trans.setReturned(true);
                    transactions.add(trans);
                } else if (trans.getEndDate() == null) {
                    trans.setReturned(false);
                    transactions.add(trans);
                }
            }

            req.setAttribute(ATT_CUSTOMER_BOOK, transactions);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(CUSTOMER_BOOK);
    }

}
