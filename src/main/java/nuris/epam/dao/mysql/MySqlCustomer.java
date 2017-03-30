package nuris.epam.dao.mysql;

import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 18.03.2017.
 */
public class MySqlCustomer extends CustomerDao{

    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";
    private static final String REGISTER_DATE = "register_date";
    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String ID_PERSON = "id_person";
    private static final String ID_ROLE = "id_role";
    private static final String ID_AVATAR = "id_avatar";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(CUSTOMER).whereQs(ID_CUSTOMER).build();
    private static final String INSERT = Sql.create().insert().var(CUSTOMER).valuesNull(ID_CUSTOMER, 5).build();
    private static final String UPDATE = Sql.create().update().var(CUSTOMER).set().varQs(REGISTER_DATE).c().varQs(PASSWORD).c().varQs(LOGIN).c().varQs(ID_PERSON).c().varQs(ID_ROLE).whereQs(ID_CUSTOMER).build();
    private static final String DELETE = Sql.create().delete().var(CUSTOMER).whereQs(ID_CUSTOMER).build();
    private static final String COUNT_CUSTOMER = Sql.create().select().count().from().var(CUSTOMER).build();
    private static final String UPDATE_AVATAR = Sql.create().update().var(CUSTOMER).set().varQs(ID_AVATAR).whereQs(ID_CUSTOMER).build();
    private static final String FIND_BY_LOGIN = Sql.create().select().allFrom().var(CUSTOMER).whereQs(LOGIN).build();
    private static final String FIND_BY_LOGIN_PASSWORD = Sql.create().select().allFrom().var(CUSTOMER).whereQs(LOGIN).and().varQs(PASSWORD).build();


    @Override
    public Customer insert(Customer item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementCustomer(statement, item);
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
    public Customer findById(int id) throws DaoException {
        Customer customer = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customer = itemCustomer(customer , resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return customer;
    }

    @Override
    public void update(Customer item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementCustomer(statement, item);
                statement.setInt(6, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update  " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Customer item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't delete customer " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public int getCustomerCount() throws DaoException {
        int count = 0;
        try (Statement statement = getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(COUNT_CUSTOMER)) {
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get count customer "+ this.getClass().getSimpleName() , e);
        }
        return count;
    }

    @Override
    public void updateAvatar(Customer item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_AVATAR)) {
                statement.setInt(1, item.getAvatar().getId());
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update avatar " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public Customer getCustomer(String login) throws DaoException {
        Customer customer = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_LOGIN)) {
                statement.setString(1, login);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customer = itemCustomer(customer , resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get by login " + this.getClass().getSimpleName(), e);
        }
        return customer;
    }

    @Override
    public Customer getCustomer(String login, String password) throws DaoException {
        Customer customer = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_LOGIN_PASSWORD)) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customer = itemCustomer(customer , resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get by login and pasdword " + this.getClass().getSimpleName(), e);
        }
        return customer;
    }

    private PreparedStatement statementCustomer(PreparedStatement statement, Customer item) throws SQLException {
        statement.setDate(1, item.getRegisterDate());
        statement.setString(2, item.getPassword());
        statement.setString(3, item.getLogin());
        statement.setInt(4 , item.getPerson().getId());
        statement.setInt(5 , item.getCustomerRole().getId());
        return statement;
    }

    private Customer itemCustomer(Customer customer, ResultSet resultSet) throws SQLException {
        customer = new Customer();
        customer.setId(resultSet.getInt(1));
        customer.setRegisterDate(resultSet.getDate(2));
        customer.setPassword(resultSet.getString(3));
        customer.setLogin(resultSet.getString(4));
        return customer;
    }

}
