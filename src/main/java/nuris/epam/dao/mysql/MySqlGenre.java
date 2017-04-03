package nuris.epam.dao.mysql;

import nuris.epam.dao.GenreDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.03.2017.
 */
public class MySqlGenre extends GenreDao {
    public static final String GENRE = "genre";
    public static final String NAME = "name";
    public static final String ID_GENRE = "id_genre";

    private static final String BOOK = "book";
    public static final String ID_BOOK = "id_book";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(GENRE).whereQs(ID_GENRE).build();
    private static final String SELECT_ALL = Sql.create().select().allFrom().var(GENRE).build();
    private static final String FIND_BY_BOOK = Sql.create().select().varS(GENRE, ID_GENRE).c().varS(GENRE, NAME).from().var(GENRE).join(BOOK).varS(BOOK, ID_GENRE).eq().varS(GENRE, ID_GENRE).whereQs(BOOK, ID_BOOK).build();


    @Override
    public Genre insert(Genre item) throws DaoException {
        return null;
    }

    @Override
    public Genre findById(int id) throws DaoException {
        Genre genre = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = itemGenre(genre, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return genre;
    }

    @Override
    public void update(Genre item) throws DaoException {

    }

    @Override
    public void delete(Genre item) throws DaoException {

    }


    @Override
    public List<Genre> getAll() throws DaoException {
        List<Genre> list = new ArrayList<>();
        Genre genre = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = itemGenre(genre, resultSet);
                        list.add(genre);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't get all list " + this.getClass().getSimpleName(), e);
        }
        return list;
    }


    @Override
    public Genre findByBook(Book book) throws DaoException {
        Genre genre = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, book.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = itemGenre(genre, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return genre;
    }

    private Genre itemGenre(Genre genre, ResultSet resultSet) throws SQLException {
        genre = new Genre();
        genre.setId(resultSet.getInt(1));
        genre.setName(resultSet.getString(2));
        return genre;
    }
}
