package nuris.epam.dao.mysql;

import nuris.epam.dao.ManagementDao;
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
 * Created by User on 28.03.2017.
 */
public class MySqlManagement extends ManagementDao {

    private static final String MANAGEMENT = "management";
    private static final String ID_MANAGEMENT = "id_management";

    private static final String TRANSACTION = "transaction";
    private static final String ID_TRANSACTION = "id_transaction";
    private static final String RETURN_DATE = "return_date";
    private static final String END_DATE = "end_date";

    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(MANAGEMENT).whereQs(ID_MANAGEMENT).build();
    private static final String INSERT = Sql.create().insert().var(MANAGEMENT).values(ID_MANAGEMENT, 2).build();
    private static final String UPDATE = Sql.create().update().var(MANAGEMENT).set().varQs(RETURN_DATE).c().varQs(ID_TRANSACTION).whereQs(ID_MANAGEMENT).build();
    private static final String ACTIVE_LIMIT_MANAGEMENT = Sql.create().select().allFrom().var(MANAGEMENT).whereIsNull(RETURN_DATE, true).limit().build();
    private static final String INACTIVE_LIMIT_MANAGEMENT = Sql.create().select().allFrom().var(MANAGEMENT).whereIsNull(RETURN_DATE, false).limit().build();
    private static final String RANGE_DATE_INACTIVE = Sql.create().select().allFrom().var(MANAGEMENT).where(RETURN_DATE).between().limit().build();
    private static final String RANGE_DATE_ACTIVE = Sql.create().select().varS(MANAGEMENT, ID_MANAGEMENT).c().varS(MANAGEMENT, RETURN_DATE).c().varS(MANAGEMENT, ID_TRANSACTION).from().var(MANAGEMENT).join(TRANSACTION).varS(MANAGEMENT, ID_TRANSACTION).eq().varS(TRANSACTION, ID_TRANSACTION).whereIsNull(TRANSACTION, END_DATE, false).and().IsNull(MANAGEMENT, RETURN_DATE, true).and().varS(TRANSACTION, END_DATE).between().limit().build();
    private static final String FIND_BY_CUSTOMER = Sql.create().select().varS(MANAGEMENT, ID_MANAGEMENT).c().varS(MANAGEMENT, RETURN_DATE).c().varS(MANAGEMENT, ID_TRANSACTION).from().var(MANAGEMENT).join(TRANSACTION).varS(MANAGEMENT, ID_TRANSACTION).eq().varS(TRANSACTION, ID_TRANSACTION).join(CUSTOMER).varS(CUSTOMER, ID_CUSTOMER).eq().varS(TRANSACTION, ID_CUSTOMER).whereQs(CUSTOMER, ID_CUSTOMER).build();
    private static final String FIND_BY_TRANSACTION = Sql.create().select().allFrom().var(MANAGEMENT).whereQs(ID_TRANSACTION).build();
    private static final String MANAGEMENT_ACTIVE_COUNT = Sql.create().select().count().from().var(MANAGEMENT).whereIsNull(RETURN_DATE , true).build();
    private static final String MANAGEMENT_INACTIVE_COUNT = Sql.create().select().count().from().var(MANAGEMENT).whereIsNull(RETURN_DATE , false).build();

    public void sql(){
        System.out.println(MANAGEMENT_ACTIVE_COUNT);
    }
    @Override
    public Management insert(Management item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementManagement(statement, item);
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
    public Management findById(int id) throws DaoException {
        Management management = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        management = itemManagement(management, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can find by id " + this.getClass().getSimpleName(), e);
        }
        return management;
    }

    @Override
    public void update(Management item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementManagement(statement, item);
                statement.setInt(3, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update  " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Management item) throws DaoException {
    }

    @Override
    public List<Management> getListManagement(int start, int count, boolean isActive) throws DaoException {
        List<Management> list = new ArrayList<>();
        Management management = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(isActive ? INACTIVE_LIMIT_MANAGEMENT : ACTIVE_LIMIT_MANAGEMENT)) {
                statement.setInt(1, ((start - 1) * count));
                statement.setInt(2, count);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        management = itemManagement(management, resultSet);
                        list.add(management);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get list of management " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public List<Management> findByCustomer(int id) throws DaoException {
        List<Management> list = new ArrayList<>();
        Management management = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_CUSTOMER)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        management = itemManagement(management, resultSet);
                        list.add(management);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can find by id " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public Management findByTransaction(Transaction transaction) throws DaoException {
        Management management = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_TRANSACTION)) {
                statement.setInt(1, transaction.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        management = itemManagement(management, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can find by transaction " + this.getClass().getSimpleName(), e);
        }
        return management;
    }

    @Override
    public List<Management> getListManagementByDateRange(String startDate, String endDate, int start, int count, boolean isActive) throws DaoException {
        List<Management> list = new ArrayList<>();
        Management management = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(isActive ? RANGE_DATE_ACTIVE : RANGE_DATE_INACTIVE)) {
                statement.setString(1, startDate);
                statement.setString(2, endDate);
                statement.setInt(3, ((start - 1) * count));
                statement.setInt(4, count);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        management = itemManagement(management, resultSet);
                        list.add(management);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get list of management by date " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public int getManagementCount(boolean isActive) throws DaoException {
        int count = 0;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(isActive?MANAGEMENT_INACTIVE_COUNT:MANAGEMENT_ACTIVE_COUNT)) {
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

    private Management itemManagement(Management management, ResultSet resultSet) throws SQLException {
        management = new Management();
        management.setId(resultSet.getInt(1));
        management.setReturnDate(resultSet.getTimestamp(2));
        return management;
    }

    private PreparedStatement statementManagement(PreparedStatement statement, Management item) throws SQLException {
        if (item.getReturnDate() == null) {
            statement.setNull(1, Types.DATE);
        } else {
            statement.setTimestamp(1, item.getReturnDate());
        }
        statement.setInt(2, item.getTransaction().getId());
        return statement;
    }
}
