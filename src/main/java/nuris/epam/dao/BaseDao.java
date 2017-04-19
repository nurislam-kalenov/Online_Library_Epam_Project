package nuris.epam.dao;

import nuris.epam.entity.BaseEntity;

import java.sql.Connection;

/**
 * Абстрактый класс, позволяющяет конкретным Дао обьектам соединяться с БД, для последующей работы с БД.
 *
 * @author Kalenov Nurislam
 */
public abstract class BaseDao<T extends BaseEntity> implements Dao<T> {

    private Connection connection;

    /**
     * Метод предоставляет  соедение с БД.
     *
     * @return Возвращаят соедение с БД.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Метод, которая позволяет Дао соединиться с БД, для дальнейшей работы с БД.
     *
     * @param connection - соеденение с БД.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
