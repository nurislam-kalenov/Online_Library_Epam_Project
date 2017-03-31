package nuris.epam.dao;

import nuris.epam.entity.BaseEntity;

public interface Dao<T extends BaseEntity> {
    T insert(T item) throws DaoException;

    T findById(int id) throws DaoException;

    void update(T item) throws DaoException;

    void delete(T item) throws DaoException;

}
