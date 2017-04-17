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
public class PageReturnCustomerBookAction implements Action {
    private boolean isActive;
    private boolean isActiveState = false;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        int page = 1;
        int recordPerPage = 3;

        if (req.getParameter(PAGE) != null) {
            page = Integer.parseInt(req.getParameter(PAGE));
        }

        if(req.getParameter(ACTIVE)!=null){
            isActive = Boolean.valueOf(req.getParameter(ACTIVE));
            isActiveState = isActive;
        }

        HttpSession session = req.getSession();
        int id = (int) session.getAttribute(CUSTOMER_ID);

        TransactionService transactionService = new TransactionService();
        Transaction transaction = new Transaction();
        Customer customer = new Customer();
        customer.setId(id);
        transaction.setCustomer(customer);

        try {
            List<Transaction> list = transactionService.getListTransaction(transaction , page , recordPerPage , isActiveState);
            int noOfRecords = transactionService.getTransactionCount(transaction ,isActiveState);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordPerPage);

            req.setAttribute(ATT_NO_PAGES, noOfPages);
            req.setAttribute(ATT_CURRENT_PAGE, page);
            req.setAttribute(ATT_CUSTOMER_BOOK, list);
            req.setAttribute(ATT_IS_ACTIVE_STATE, isActiveState);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(CUSTOMER_BOOK);
    }

}
