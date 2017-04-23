package nuris.epam.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by User on 26.03.2017.
 */
public class Transaction extends BaseEntity {

    private Date startDate;

    private Timestamp endDate;

    private BookInfo bookInfo;

    private Customer customer;

    public Transaction() {
        bookInfo = new BookInfo();
        customer = new Customer();
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return getId() + "/" + startDate + "/" + endDate + "/" + bookInfo + "/" + customer;
    }
}
