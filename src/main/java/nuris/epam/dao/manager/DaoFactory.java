package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.connection.exception.ResourcesException;
import nuris.epam.dao.BaseDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс служит для управленеи коннектами, а именно раздача и закрытие коннектов.
 * Также создает экземпляры Dao обектов и  кладет в них коннект.
 *
 * @author Kalenov Nurislam
 */
public class DaoFactory implements AutoCloseable {
    /**
     * Поле  - содержит пул коннектов
     */
    private ConnectionPool connectionPool;
    /**
     * Поле  - Соеденение с БД.
     */
    private Connection connection;
    /**
     * Поле  - Возвращяет обект дао в зависимости от типа БД.
     */
    private TypeDao typeDao;

    public DaoFactory() {
        connectionPool = ConnectionPool.getInstance();
        typeDao = TypeDao.getInstance();
        try {
            connection = connectionPool.getConnection();
        } catch (ResourcesException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает новый объект Dao , также дает ему коннект к базе данных.
     *
     * @param clazz - Тип обекта. (Рефлексия)
     * @return Dao обьект.
     */
    public <T extends BaseDao<BaseEntity>> T getDao(Class<T> clazz) throws DaoException {
        T t;
        try {
            t = clazz.newInstance();
            t.setConnection(connection);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new DaoException("Cant to create or give new DAO object", e);
        }
        return t;
    }

    /**
     * Кладет коннект в пул коннектов.
     */
    public void returnConnect() {
        connectionPool.returnConnection(connection);
    }

    /**
     * @return Возвращяет Дао обект в зависимости от типа БД.
     */
    public TypeDao typeDao() {
        return typeDao;
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Cannot starting date transaction", e);
        }
    }

    public void commitTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Cannot committing date transaction", e);
        }
    }

    public void rollbackTransaction() throws DaoException {
        try {
            connection.rollback();
            System.out.println("this is roolBack");
        } catch (SQLException e) {
            throw new DaoException("Cannot rollback date transaction", e);
        }
    }

    @Override
    public void close() {
        returnConnect();
        System.out.println("Return Connect");
    }
}
