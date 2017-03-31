package nuris.epam.dao.mysql;

import nuris.epam.dao.TransactionDao;
import nuris.epam.dao.DaoException;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 27.03.2017.
 */
public class MySqlTransaction extends TransactionDao {

    private static final String TRANSACTION = "transaction";
    private static final String ID_TRANSACTION = "id_transaction";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String ID_BOOK_INFO = "id_book_info";
    private static final String ID_CUSTOMER = "id_customer";

    private static final String MANAGEMENT = "management";
    private static final String ID_MANAGEMENT = "id_management";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(TRANSACTION).whereQs(ID_TRANSACTION).build();
    private static final String INSERT = Sql.create().insert().var(TRANSACTION).values(ID_TRANSACTION, 4).build();
    private static final String UPDATE = Sql.create().update().var(TRANSACTION).set().varQs(START_DATE).c().varQs(END_DATE).c().varQs(ID_BOOK_INFO).c().varQs(ID_CUSTOMER).whereQs(ID_TRANSACTION).build();
    private static final String DELETE = Sql.create().delete().var(TRANSACTION).whereQs(ID_TRANSACTION).build();
    private static final String FIND_BY_CUSTOMER = Sql.create().select().allFrom().var(TRANSACTION).whereQs(ID_CUSTOMER).build();
    private static final String FIND_BY_MANAGEMENT = Sql.create().select().varS(TRANSACTION, ID_TRANSACTION).c().varS(TRANSACTION, START_DATE).c().varS(TRANSACTION, END_DATE).from().var(TRANSACTION).join(MANAGEMENT).varS(MANAGEMENT, ID_TRANSACTION).eq().varS(TRANSACTION, ID_TRANSACTION).whereQs(MANAGEMENT, ID_MANAGEMENT).build();

    public void sql() {
        System.out.println(FIND_BY_MANAGEMENT);
    }

    @Override
    public Transaction insert(Transaction item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementTransaction(statement, item);
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public Transaction findById(int id) throws DaoException {
        Transaction transaction = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        transaction = itemTransaction(transaction, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return transaction;
    }

    @Override
    public void update(Transaction item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementTransaction(statement, item);
                statement.setInt(5, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update" + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Transaction item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't delete " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public List<Transaction> findByCustomer(Transaction transaction) throws DaoException {
        List<Transaction> list = new ArrayList<>();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_CUSTOMER)) {
                statement.setInt(1, transaction.getCustomer().getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        transaction = itemTransaction(transaction, resultSet);
                        list.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by customer" + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public Transaction findByManagement(Management management) throws DaoException {
        Transaction transaction = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_MANAGEMENT)) {
                statement.setInt(1, management.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        transaction = itemTransaction(transaction, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by management " + this.getClass().getSimpleName(), e);
        }
        return transaction;
    }

    private Transaction itemTransaction(Transaction transaction, ResultSet resultSet) throws SQLException {
        transaction = new Transaction();
        transaction.setId(resultSet.getInt(1));
        transaction.setStartDate(resultSet.getDate(2));
        transaction.setEndDate(resultSet.getDate(3));
        return transaction;
    }

    private PreparedStatement statementTransaction(PreparedStatement statement, Transaction item) throws SQLException {
        statement.setDate(1, item.getStartDate());
        if (item.getEndDate() == null) {
            statement.setNull(2, Types.DATE);
        } else {
            statement.setDate(2, item.getEndDate());
        }
        statement.setInt(3, item.getBookInfo().getId());
        statement.setInt(4, item.getCustomer().getId());
        return statement;
    }


}
