package nuris.epam.dao.mysql;

import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlAuthor extends AuthorDao {

    private static final String AUTHOR = "author";
    private static final String ID_AUTHOR = "id_author";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String MIDDLE_NAME = "middle_name";

    private static final String BOOK = "book";
    public static final String ID_BOOK = "id_book";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(AUTHOR).whereQs(ID_AUTHOR).build();
    private static final String INSERT = Sql.create().insert().var(AUTHOR).values(ID_AUTHOR, 3).build();
    private static final String UPDATE = Sql.create().update().var(AUTHOR).set().varQs(FIRST_NAME).c().varQs(LAST_NAME).c().varQs(MIDDLE_NAME).whereQs(ID_AUTHOR).build();
    private static final String DELETE = Sql.create().delete().var(AUTHOR).whereQs(ID_AUTHOR).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(AUTHOR).build();
    private static final String FIND_BY_BOOK = Sql.create().select().varS(AUTHOR, ID_AUTHOR).c().varS(AUTHOR, FIRST_NAME).c().varS(AUTHOR, LAST_NAME).c().varS(AUTHOR, MIDDLE_NAME).from().var(AUTHOR).join(BOOK).varS(BOOK, ID_AUTHOR).eq().varS(AUTHOR, ID_AUTHOR).whereQs(BOOK, ID_BOOK).build();

    @Override
    public Author insert(Author item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement(statement, item).executeUpdate();
                try(ResultSet resultSet = statement.getGeneratedKeys()){
                resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public Author findById(int id) throws DaoException {
        Author author = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        author = itemAuthor(author, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by id  " + this.getClass().getSimpleName(), e);
        }
        return author;
    }

    @Override
    public void update(Author item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement(statement, item);
                statement.setInt(4, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public List<Author> getAll() throws DaoException {
        List<Author> list = new ArrayList<>();
        Author author = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(itemAuthor(author, resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get all author " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    @Override
    public void delete(Author item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't delete author " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public Author findByBook(Book book) throws DaoException {
        Author author = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, book.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        author = itemAuthor(author, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return author;
    }

    private PreparedStatement statement(PreparedStatement statement, Author item) throws SQLException {
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setString(3, item.getMiddleName());
        return statement;
    }

    private Author itemAuthor(Author author, ResultSet resultSet) throws SQLException {
        author = new Author();
        author.setId(resultSet.getInt(1));
        author.setFirstName(resultSet.getString(2));
        author.setLastName(resultSet.getString(3));
        author.setMiddleName(resultSet.getString(4));
        return author;
    }


}
