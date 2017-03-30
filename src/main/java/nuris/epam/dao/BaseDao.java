package nuris.epam.dao;

import nuris.epam.entity.BaseEntity;

import java.sql.Connection;

/**
 * Created by User on 10.03.2017.
 */
public abstract class BaseDao<T extends BaseEntity> implements Dao<T> {

    private  Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
