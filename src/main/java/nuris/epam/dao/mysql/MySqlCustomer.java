package nuris.epam.dao.mysql;

import nuris.epam.dao.CustomerDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18.03.2017.
 */
public class MySqlCustomer extends CustomerDao{

    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";
    private static final String REGISTER_DATE = "register_date";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ID_ROLE = "id_role";

    private static final String MANAGEMENT = "management";
    private static final String ID_MANAGEMENT = "id_management";
    private static final String TRANSACTION = "transaction";
    private static final String ID_TRANSACTION = "id_transaction";

    private static final String PERSON = "person";
    private static final String ID_PERSON = "id_person";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(CUSTOMER).whereQs(ID_CUSTOMER).build();
    private static final String INSERT = Sql.create().insert().var(CUSTOMER).values(ID_CUSTOMER, 5).build();
    private static final String UPDATE = Sql.create().update().var(CUSTOMER).set().varQs(REGISTER_DATE).c().varQs(PASSWORD).c().varQs(EMAIL).c().varQs(ID_PERSON).c().varQs(ID_ROLE).whereQs(ID_CUSTOMER).build();
    private static final String DELETE = Sql.create().delete().var(CUSTOMER).whereQs(ID_CUSTOMER).build();
    private static final String COUNT_CUSTOMER = Sql.create().select().count().from().var(CUSTOMER).build();
    private static final String FIND_BY_LOGIN = Sql.create().select().allFrom().var(CUSTOMER).whereQs(EMAIL).build();
    private static final String FIND_BY_FIRST_NAME_AND_LAST_NAME =  Sql.create().select().varS(CUSTOMER, ID_CUSTOMER).c().varS(CUSTOMER, EMAIL).from().var(CUSTOMER).join(PERSON).varS(PERSON, ID_PERSON).eq().varS(CUSTOMER, ID_PERSON).whereQs(PERSON, FIRST_NAME).and().varQs(PERSON,LAST_NAME).build();
    private static final String FIND_BY_LOGIN_PASSWORD = Sql.create().select().allFrom().var(CUSTOMER).whereQs(EMAIL).and().varQs(PASSWORD).build();
    private static final String FIND_BY_MANAGEMENT = Sql.create().select().varS(CUSTOMER, ID_CUSTOMER).c().varS(CUSTOMER, EMAIL).from().var(CUSTOMER).join(TRANSACTION).varS(CUSTOMER, ID_CUSTOMER).eq().varS(TRANSACTION, ID_CUSTOMER).join(MANAGEMENT).varS(MANAGEMENT, ID_TRANSACTION).eq().varS(TRANSACTION, ID_TRANSACTION).whereQs(MANAGEMENT, ID_MANAGEMENT).build();
    private static final String LIMIT_CUSTOMER = Sql.create().select().allFrom().var(CUSTOMER).limit().build();

    public void sql(){
        System.out.println(FIND_BY_FIRST_NAME_AND_LAST_NAME);
    }
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

    @Override
    public Customer findByManagement(Management management) throws DaoException {
        Customer customer = new Customer();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_MANAGEMENT)) {
                statement.setInt(1, management.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customer.setId(resultSet.getInt(1));
                        customer.setEmail(resultSet.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by management " + this.getClass().getSimpleName(), e);
        }
        return customer;
    }

    @Override
    public List<Customer> getLimitCustomers(int start, int count) throws DaoException {
        List<Customer> list = new ArrayList<>();
        Customer customer = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(LIMIT_CUSTOMER)) {
                statement.setInt(1, ((start - 1) * count));
                statement.setInt(2, count);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customer = itemCustomer(customer, resultSet);
                        list.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get list of customer " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public Customer findByFirstNameAndLastName(Customer customer) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_FIRST_NAME_AND_LAST_NAME)) {
                statement.setString(1 , customer.getPerson().getFirstName());
                statement.setString(2,  customer.getPerson().getLastName());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customer.setId(resultSet.getInt(1));
                        customer.setEmail(resultSet.getString(2));

                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get list of customer by first name and second name " + this.getClass().getSimpleName(), e);
        }
        return customer;
    }

    private PreparedStatement statementCustomer(PreparedStatement statement, Customer item) throws SQLException {
        statement.setDate(1, item.getRegisterDate());
        statement.setString(2, item.getPassword());
        statement.setString(3, item.getEmail());
        statement.setInt(4 , item.getPerson().getId());
        statement.setInt(5 , item.getCustomerRole().getId());
        return statement;
    }

    private Customer itemCustomer(Customer customer, ResultSet resultSet) throws SQLException {
        customer = new Customer();
        customer.setId(resultSet.getInt(1));
        customer.setRegisterDate(resultSet.getDate(2));
        customer.setPassword(resultSet.getString(3));
        customer.setEmail(resultSet.getString(4));
        return customer;
    }

}
