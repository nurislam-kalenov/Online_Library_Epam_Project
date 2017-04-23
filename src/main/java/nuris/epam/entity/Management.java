package nuris.epam.entity;

import java.sql.Timestamp;

/**
 * Created by User on 28.03.2017.
 */
public class Management extends BaseEntity {

    private Transaction transaction;
    private Timestamp returnDate;

    public Management() {
        transaction = new Transaction();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return getId()+ "/"+returnDate+"/"+transaction;
    }
}
