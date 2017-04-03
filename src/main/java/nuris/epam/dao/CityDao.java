package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.City;
import nuris.epam.entity.Person;

import java.util.List;

/**
 * Created by User on 17.03.2017.
 */
public abstract class CityDao extends BaseDao<City>{

    public abstract City findByPerson(Person person) throws DaoException;

    public abstract List<City> getAll() throws DaoException;

}
