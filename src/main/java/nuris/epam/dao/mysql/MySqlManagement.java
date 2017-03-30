package nuris.epam.dao.mysql;

import nuris.epam.dao.ManagementDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by User on 28.03.2017.
 */
public class MySqlManagement extends ManagementDao {

    private static final String MANAGEMENT = "management";
    private static final String ID_MANAGEMENT = "id_management";
    private static final String ID_TRANSACTION = "id_transaction";
    private static final String RETURN_DATE = "return_date";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(MANAGEMENT).whereQs(ID_MANAGEMENT).build();
    private static final String INSERT = Sql.create().insert().var(MANAGEMENT).values(ID_MANAGEMENT, 2).build();
    private static final String UPDATE = Sql.create().update().var(MANAGEMENT).set().varQs(RETURN_DATE).c().varQs(ID_TRANSACTION).whereQs(ID_MANAGEMENT).build();
  ///  private static final String FIND_BY_CUSTOMER = Sql.create().select().allFrom().var(TRANSACTION).whereQs(ID_CUSTOMER).build();


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
            throw new DaoException("Can find by id " + this.getClass().getSimpleName(), e);
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

    private Management itemManagement(Management management, ResultSet resultSet) throws SQLException {
        management = new Management();
        management.setId(resultSet.getInt(1));
        management.setReturnDate(resultSet.getDate(2));
        return management;
    }

    private PreparedStatement statementManagement(PreparedStatement statement,Management item) throws SQLException {
        if (item.getReturnDate() == null) {
            statement.setNull(1, Types.DATE);
        } else {
            statement.setDate(1, item.getReturnDate());
        }
        statement.setInt(2, item.getTransaction().getId());
        return statement;
    }
}
