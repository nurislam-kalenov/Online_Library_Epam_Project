package nuris.epam.entity;

import java.sql.Date;

/**
 * Created by User on 28.03.2017.
 */
public class Management extends BaseEntity {

    private Transaction transaction;
    private Date returnDate;

    public Management() {
        transaction = new Transaction();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return getId()+ "/"+returnDate+"/"+transaction;
    }
}
