package nuris.epam.dao.mysql;

import nuris.epam.dao.CityDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.City;
import nuris.epam.entity.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17.03.2017.
 */
public class MySqlCity extends CityDao {
    public static final String CITY = "city";
    public static final String NAME = "name";
    public static final String ID_CITY = "id_city";

    public static final String PERSON = "person";
    public static final String ID_PERSON = "id_person";

    private static final String SELECT_ALL = Sql.create().select().allFrom().var(CITY).build();
    private static final String FIND_BY_PERSON = Sql.create().select().varS(CITY, ID_CITY).c()
            .varS(CITY, NAME).from().var(CITY).join(PERSON).varS(PERSON, ID_CITY).eq()
            .varS(CITY, ID_CITY).whereQs(PERSON, ID_PERSON).build();


    @Override
    public City insert(City item) throws DaoException {
        return null;
    }

    @Override
    public void update(City item) throws DaoException {

    }

    @Override
    public void delete(City item) throws DaoException {

    }

    @Override
    public City findById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<City> getAll() throws DaoException {
        List<City> list = new ArrayList<>();
        City city = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        city = itemCity(city, resultSet);
                        list.add(city);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get all city " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public City findByPerson(Person person) throws DaoException {
        City city = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_PERSON)) {
                statement.setInt(1, person.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        city = itemCity(city, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find city by person " + this.getClass().getSimpleName(), e);
        }
        return city;
    }

    private City itemCity(City city, ResultSet resultSet) throws SQLException {
        city = new City();
        city.setId(resultSet.getInt(1));
        city.setName(resultSet.getString(2));
        return city;
    }
}
