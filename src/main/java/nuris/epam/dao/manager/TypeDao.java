package nuris.epam.dao.manager;

import nuris.epam.connection.ConnectionPool;
import nuris.epam.dao.mysql.*;

/**
 * Created by User on 11.03.2017.
 */
public class TypeDao {
    private static TypeDao typeDao;
    private ConnectionPool connectType;

    private TypeDao() {
        connectType = ConnectionPool.getInstance();
    }

    public Class getAuthorDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlAuthor.class;
        } else {
            return MySqlAuthor.class;
        }
    }
    public Class getGenreDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlGenre.class;
        } else {
            return MySqlGenre.class;
        }
    }

    public Class getBookDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlBook.class;
        } else {
            return MySqlBook.class;
        }
    }

    public Class getCityDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlCity.class;
        } else {
            return MySqlCity.class;
        }
    }

    public Class getPersonDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlPerson.class;
        } else {
            return MySqlPerson.class;
        }
    }
    public Class getCustomerRoleDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlCustomerRole.class;
        } else {
            return MySqlCustomerRole.class;
        }
    }

    public Class getAvatarDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlAvatar.class;
        } else {
            return MySqlAvatar.class;
        }
    }

    public Class getCustomerDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlCustomer.class;
        } else {
            return MySqlCustomer.class;
        }
    }

    public Class getBookInfoDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlBookInfo.class;
        } else {
            return MySqlBookInfo.class;
        }
    }

    public Class getTransactionDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
            return MySqlTransaction.class;
        } else {
            return MySqlTransaction.class;
        }
    }
    public Class getManagementDao() {
        if (connectType.getType().equalsIgnoreCase("mysql")) {
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
