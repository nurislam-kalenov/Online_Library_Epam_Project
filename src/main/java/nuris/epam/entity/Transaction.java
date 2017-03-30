package nuris.epam.entity;

import java.sql.Date;

/**
 * Created by User on 26.03.2017.
 */
public class Transaction extends BaseEntity {

    private Date startDate;

    private Date endDate;

    private BookInfo bookInfo;

    private Customer customer;

    public Transaction() {
        bookInfo = new BookInfo();
        customer = new Customer();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
        return getId() + "/"+startDate+ "/"+endDate+"/"+bookInfo+"/"+customer;
    }
}
