package nuris.epam.action.get;

import javafx.collections.transformation.TransformationList;
import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Transaction;
import nuris.epam.services.CustomerService;
import nuris.epam.services.TransactionService;
import nuris.epam.services.exceptions.ServiceException;
import nuris.epam.utils.TextParse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static nuris.epam.action.constants.Constants.*;

/**
 * Created by User on 18.04.2017.
 */
public class PageAboutReaderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        CustomerService customerService = new CustomerService();
        TransactionService transactionService = new TransactionService();
        List<Transaction> addTransaction = new ArrayList<>();
        Transaction transaction = new Transaction();
        Customer customer = null;
        String id = req.getParameter(READER_ID);

        try {
            customer = customerService.findCustomerById(TextParse.toInt(id));
            transaction.setCustomer(customer);
            List<Transaction> transactions = transactionService.findByCustomer(transaction);
            for(Transaction tran : transactions){
                if(tran.getEndDate()==null){
                    addTransaction.add(tran);
                }
            }} catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute(TRANSACTIONS, addTransaction);
        req.setAttribute(ATT_CUSTOMER_INFO, customer);
        return new ActionResult(ABOUT_READER);
    }
}

