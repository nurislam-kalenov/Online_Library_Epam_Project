package nuris.epam.dao.mysql;

import nuris.epam.dao.TransactionDao;
import nuris.epam.dao.exception.DaoException;
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
    private static final String RETURN_DATE = "return_date";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(TRANSACTION).whereQs(ID_TRANSACTION).build();
    private static final String INSERT = Sql.create().insert().var(TRANSACTION).value(ID_BOOK_INFO, ID_CUSTOMER).values(2).build();
    private static final String UPDATE = Sql.create().update().var(TRANSACTION).set().varQs(END_DATE).c().varQs(ID_BOOK_INFO).c().varQs(ID_CUSTOMER).whereQs(ID_TRANSACTION).build();
    private static final String DELETE = Sql.create().delete().var(TRANSACTION).whereQs(ID_TRANSACTION).build();
    private static final String FIND_BY_CUSTOMER = Sql.create().select().allFrom().var(TRANSACTION).whereQs(ID_CUSTOMER).build();
    private static final String FIND_BY_MANAGEMENT = Sql.create().select().varS(TRANSACTION, ID_TRANSACTION).c().varS(TRANSACTION, START_DATE).c().varS(TRANSACTION, END_DATE).from().var(TRANSACTION).join(MANAGEMENT).varS(MANAGEMENT, ID_TRANSACTION).eq().varS(TRANSACTION, ID_TRANSACTION).whereQs(MANAGEMENT, ID_MANAGEMENT).build();
    private static final String ACTIVE_CUSTOMER = Sql.create().select().varS(TRANSACTION, ID_TRANSACTION).c().varS(TRANSACTION, START_DATE).c().varS(TRANSACTION, END_DATE).c().varS(TRANSACTION, ID_BOOK_INFO).c().varS(TRANSACTION, ID_CUSTOMER).from().var(TRANSACTION).join(MANAGEMENT).varS(TRANSACTION, ID_TRANSACTION).eq().varS(MANAGEMENT, ID_TRANSACTION).whereIsNull(MANAGEMENT, RETURN_DATE, false).and().varQs(TRANSACTION, ID_CUSTOMER).limit().build();
    private static final String INACTIVE_CUSTOMER = Sql.create().select().varS(TRANSACTION, ID_TRANSACTION).c().varS(TRANSACTION, START_DATE).c().varS(TRANSACTION, END_DATE).c().varS(TRANSACTION, ID_BOOK_INFO).c().varS(TRANSACTION, ID_CUSTOMER).from().var(TRANSACTION).join(MANAGEMENT).varS(TRANSACTION, ID_TRANSACTION).eq().varS(MANAGEMENT, ID_TRANSACTION).whereIsNull(MANAGEMENT, RETURN_DATE, true).and().varQs(TRANSACTION, ID_CUSTOMER).limit().build();
    private static final String ACTIVE_CUSTOMER_COUNT = Sql.create().select().count().from().var(MANAGEMENT).join(TRANSACTION).varS(TRANSACTION, ID_TRANSACTION).eq().varS(MANAGEMENT, ID_TRANSACTION).whereIsNull(MANAGEMENT, RETURN_DATE, false).and().varQs(TRANSACTION, ID_CUSTOMER).build();
    private static final String INACTIVE_CUSTOMER_COUNT = Sql.create().select().count().from().var(MANAGEMENT).join(TRANSACTION).varS(TRANSACTION, ID_TRANSACTION).eq().varS(MANAGEMENT, ID_TRANSACTION).whereIsNull(MANAGEMENT, RETURN_DATE, true).and().varQs(TRANSACTION, ID_CUSTOMER).build();

    @Override
    public Transaction insert(Transaction item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, item.getBookInfo().getId());
                statement.setInt(2, item.getCustomer().getId());
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
                statement.setInt(4, item.getId());
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
    public List<Transaction> getListTransactionByCustomer(Transaction transaction, int start, int count, boolean isActive) throws DaoException {
        List<Transaction> list = new ArrayList<>();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(isActive ? ACTIVE_CUSTOMER : INACTIVE_CUSTOMER)) {
                statement.setInt(1, transaction.getCustomer().getId());
                statement.setInt(2, ((start - 1) * count));
                statement.setInt(3, count);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        transaction = itemTransaction(transaction, resultSet);
                        list.add(transaction);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get list of transaction by customer " + this.getClass().getSimpleName(), e);
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

    @Override
    public int getTransactionCountByCustomer(Transaction transaction, boolean isActive) throws DaoException {
        int count = 0;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(isActive ? ACTIVE_CUSTOMER_COUNT : INACTIVE_CUSTOMER_COUNT)) {
                statement.setInt(1, transaction.getCustomer().getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        count = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't count by active/inactive " + this.getClass().getSimpleName(), e);
        }
        return count;
    }

    private Transaction itemTransaction(Transaction transaction, ResultSet resultSet) throws SQLException {
        transaction = new Transaction();
        transaction.setId(resultSet.getInt(1));
        transaction.setStartDate(resultSet.getDate(2));
        transaction.setEndDate(resultSet.getTimestamp(3));
        return transaction;
    }

    private PreparedStatement statementTransaction(PreparedStatement statement, Transaction item) throws SQLException {
        if (item.getEndDate() == null) {
            statement.setNull(1, Types.DATE);
        } else {
            statement.setTimestamp(1, item.getEndDate());
        }
        statement.setInt(2, item.getBookInfo().getId());
        statement.setInt(3, item.getCustomer().getId());
        return statement;
    }


}
