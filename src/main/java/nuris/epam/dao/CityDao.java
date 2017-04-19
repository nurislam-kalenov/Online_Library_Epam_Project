package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.City;
import nuris.epam.entity.Person;

import java.util.List;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы city в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class CityDao extends BaseDao<City> {
    /**
     * Метод, для поиск книги с учетом сущности Person.
     *
     * @param person - сущность
     * @return Возвращает конкрутную сущность City.
     * @throws DaoException
     */
    public abstract City findByPerson(Person person) throws DaoException;
    /**
     * Метод, предоставляет список всег городов.
     *
     * @return Возвращает список городов.
     * @throws DaoException
     */
    public abstract List<City> getAll() throws DaoException;

}
