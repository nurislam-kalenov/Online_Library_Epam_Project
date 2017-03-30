package nuris.epam.dao.mysql;

import nuris.epam.dao.AvatarDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Avatar;
import nuris.epam.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 18.03.2017.
 */
public class MySqlAvatar extends AvatarDao {

    public static final String AVATAR = "avatar";
    public static final String PICTURE = "picture";
    public static final String ID_AVATAR = "id_avatar";

    private static final String CUSTOMER = "customer";
    private static final String ID_CUSTOMER = "id_customer";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(AVATAR).whereQs(ID_AVATAR).build();
    private static final String INSERT = Sql.create().insert().var(AVATAR).values(ID_AVATAR, 1).build();
    private static final String UPDATE = Sql.create().update().var(AVATAR).set().varQs(PICTURE).whereQs(ID_AVATAR).build();
    private static final String DELETE = Sql.create().delete().var(AVATAR).whereQs(ID_AVATAR).build();
    private static final String FIND_BY_BOOK = Sql.create().select().varS(AVATAR, ID_AVATAR).c().varS(AVATAR, PICTURE).from().var(AVATAR).join(CUSTOMER).varS(CUSTOMER, ID_AVATAR).eq().varS(AVATAR, ID_AVATAR).whereQs(CUSTOMER, ID_CUSTOMER).build();

    @Override
    public Avatar insert(Avatar item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setBlob(1, item.getPicture());
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public Avatar findById(int id) throws DaoException {
        Avatar avatar = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        avatar = itemAvatar(avatar, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by id from " + this.getClass().getSimpleName(), e);
        }
        return avatar;
    }

    @Override
    public void update(Avatar item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement.setBlob(1, item.getPicture());
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Can not update by entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Avatar item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot delete Avatar entity from " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public Avatar findByCustomer(Customer customer) throws DaoException {
        Avatar avatar = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, customer.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        avatar = itemAvatar(avatar, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can not insert by Book from " + this.getClass().getSimpleName(), e);
        }
        return avatar;
    }

    private Avatar itemAvatar(Avatar avatar, ResultSet resultSet) throws SQLException {
        avatar = new Avatar();
        avatar.setId(resultSet.getInt(1));
        avatar.setPicture(resultSet.getBinaryStream(2));
        return avatar;
    }
}
