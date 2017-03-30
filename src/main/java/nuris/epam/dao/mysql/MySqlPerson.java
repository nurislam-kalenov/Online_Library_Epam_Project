package nuris.epam.dao.mysql;

import nuris.epam.dao.PersonDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Customer;
import nuris.epam.entity.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 17.03.2017.
 */
public class MySqlPerson extends PersonDao {

    private static final String PERSON = "person";
    private static final String ID_PERSON = "id_person";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String PHONE = "phone";
    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String ID_CITY = "id_city";

    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(PERSON).whereQs(ID_PERSON).build();
    private static final String INSERT = Sql.create().insert().var(PERSON).values(ID_PERSON, 7).build();
    private static final String UPDATE = Sql.create().update().var(PERSON).set().varQs(FIRST_NAME).c().varQs(LAST_NAME).c().varQs(MIDDLE_NAME).c().varQs(PHONE).c().varQs(BIRTHDAY).c().varQs(ADDRESS).c().varQs(ID_CITY).whereQs(ID_PERSON).build();
    private static final String DELETE = Sql.create().delete().var(PERSON).whereQs(ID_PERSON).build();
    private static final String FIND_BY_CUSTOMER = Sql.create().select().varS(PERSON, ID_PERSON).c().varS(PERSON, FIRST_NAME).c().varS(PERSON, LAST_NAME).c().varS(PERSON, MIDDLE_NAME).c().varS(PERSON, PHONE).c().varS(PERSON, BIRTHDAY).c().varS(PERSON, ADDRESS).from().var(PERSON).join(CUSTOMER).varS(CUSTOMER, ID_PERSON).eq().varS(PERSON, ID_PERSON).whereQs(CUSTOMER, ID_CUSTOMER).build();

    @Override
    public Person insert(Person item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementPerson(statement, item).executeUpdate();
                try(ResultSet resultSet = statement.getGeneratedKeys()){
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
    public Person findById(int id) throws DaoException {
        Person person = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        person = itemPerson(person, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return person;
    }

    @Override
    public void update(Person item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementPerson(statement, item);
                statement.setInt(8, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Person item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't delete  " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public Person findByCustomer(Customer customer) throws DaoException {
        Person person = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_CUSTOMER)) {
                statement.setInt(1, customer.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        person = itemPerson(person, resultSet);

                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by customer " + this.getClass().getSimpleName(), e);
        }
        return person;
    }

    private PreparedStatement statementPerson(PreparedStatement statement, Person item) throws SQLException {
        statement.setString(1, item.getFirstName());
        statement.setString(2, item.getLastName());
        statement.setString(3, item.getMiddleName());
        statement.setString(4, item.getPhone());
        statement.setDate(5, item.getBirthday());
        statement.setString(6, item.getAdreess());
        statement.setInt(7 , item.getCity().getId());
        return statement;
    }
    private Person itemPerson(Person person, ResultSet resultSet) throws SQLException {
        person = new Person();
        person.setId(resultSet.getInt(1));
        person.setFirstName(resultSet.getString(2));
        person.setLastName(resultSet.getString(3));
        person.setMiddleName(resultSet.getString(4));
        person.setPhone(resultSet.getString(5));
        person.setBirthday(resultSet.getDate(6));
        person.setAdreess(resultSet.getNString(7));
        return person;
    }
}
