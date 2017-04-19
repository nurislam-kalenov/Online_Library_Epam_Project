package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.*;

/**
 * Класс служить для иницивлизаций обьектов , в зависимости от типа БД.
 *
 * @author Kalenov Nurislam
 */
public class TypeDao {
    private static TypeDao typeDao;
    /**
     * Свойство , предоставляет наименование(тип) БД.
     * */
    private ConnectionPool connectType;
    /**
     * Наименованте (тип) БД.
     * */
    private static final String MYSQL = "mysql";

    private TypeDao() {
        connectType = ConnectionPool.getInstance();
    }

    public Class getAuthorDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlAuthor.class;
        } else {
            return MySqlAuthor.class;
        }
    }

    public Class getGenreDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlGenre.class;
        } else {
            return MySqlGenre.class;
        }
    }

    public Class getBookDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlBook.class;
        } else {
            return MySqlBook.class;
        }
    }

    public Class getCityDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlCity.class;
        } else {
            return MySqlCity.class;
        }
    }

    public Class getPersonDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlPerson.class;
        } else {
            return MySqlPerson.class;
        }
    }

    public Class getCustomerRoleDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlCustomerRole.class;
        } else {
            return MySqlCustomerRole.class;
        }
    }

    public Class getCustomerDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlCustomer.class;
        } else {
            return MySqlCustomer.class;
        }
    }

    public Class getBookInfoDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlBookInfo.class;
        } else {
            return MySqlBookInfo.class;
        }
    }

    public Class getTransactionDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlTransaction.class;
        } else {
            return MySqlTransaction.class;
        }
    }
    public Class getManagementDao() {
        if (connectType.getType().equalsIgnoreCase(MYSQL)) {
            return MySqlManagement.class;
        } else {
            return MySqlManagement.class;
        }
    }

    public static TypeDao getInstance() {
        if (typeDao == null) {
            typeDao = new TypeDao();
        }
        return typeDao;
    }
}
