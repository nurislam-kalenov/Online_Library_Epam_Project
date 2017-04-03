package nuris.epam.dao.mysql;

import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 26.03.2017.
 */
public class MySqlBookInfo extends BookInfoDao {

    private static final String BOOK_INFO = "book_info";
    private static final String ID_BOOK_INFO = "id_book_info";
    private static final String AMOUNT = "amount";
    private static final String PRICE = "price";

    private static final String ID_BOOK = "id_book";
    private static final String BOOK = "book";
    private static final String TRANSACTION = "transaction";
    private static final String ID_TRANSACTION = "id_transaction";

    private static final String FIND_BY_ID = Sql.create().select().allFrom().var(BOOK_INFO).whereQs(ID_BOOK_INFO).build();
    private static final String INSERT = Sql.create().insert().var(BOOK_INFO).values(ID_BOOK_INFO, 3).build();
    private static final String UPDATE = Sql.create().update().var(BOOK_INFO).set().varQs(AMOUNT).c().varQs(PRICE).c().varQs(ID_BOOK).whereQs(ID_BOOK_INFO).build();
    private static final String DELETE = Sql.create().delete().var(BOOK_INFO).whereQs(ID_BOOK_INFO).build();
    private static final String FIND_BY_BOOK = Sql.create().select().varS(BOOK_INFO, ID_BOOK_INFO).c().varS(BOOK_INFO, AMOUNT).c().varS(BOOK_INFO, PRICE).c().varS(BOOK_INFO, ID_BOOK).from().var(BOOK_INFO).join(BOOK).varS(BOOK, ID_BOOK_INFO).eq().varS(BOOK_INFO, ID_BOOK_INFO).whereQs(BOOK, ID_BOOK).build();
    private static final String FIND_BY_TRANSACTION = Sql.create().select().varS(BOOK_INFO, ID_BOOK_INFO).c().varS(BOOK_INFO, AMOUNT).c().varS(BOOK_INFO, PRICE).from().var(BOOK_INFO).join(TRANSACTION).varS(TRANSACTION, ID_BOOK_INFO).eq().varS(BOOK_INFO, ID_BOOK_INFO).whereQs(TRANSACTION, ID_TRANSACTION).build();

    public void sql(){
        System.out.println(FIND_BY_TRANSACTION);
    }

    @Override
    public BookInfo insert(BookInfo item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementBookInfo(statement, item);
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
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
    public BookInfo findById(int id) throws DaoException {
        BookInfo bookInfo = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bookInfo = itemBookInfo(bookInfo, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return bookInfo;
    }

    @Override
    public void update(BookInfo item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementBookInfo(statement, item);
                statement.setInt(4, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't update " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(BookInfo item) throws DaoException {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("can't delete " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    private BookInfo itemBookInfo(BookInfo bookInfo, ResultSet resultSet) throws SQLException {
        bookInfo = new BookInfo();
        bookInfo.setId(resultSet.getInt(1));
        bookInfo.setAmount(resultSet.getInt(2));
        bookInfo.setPrice(resultSet.getInt(3));
        return bookInfo;
    }

    private PreparedStatement statementBookInfo(PreparedStatement statement, BookInfo item) throws SQLException {
        statement.setInt(1, item.getAmount());
        statement.setInt(2, item.getPrice());
        statement.setInt(3, item.getBook().getId());
        return statement;
    }

    @Override
    public BookInfo findByBook(Book book) throws DaoException {
        BookInfo bookInfo = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, book.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bookInfo = itemBookInfo(bookInfo, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return bookInfo;
    }

    @Override
    public BookInfo findByTransaction(Transaction transaction) throws DaoException {
        BookInfo bookInfo = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_TRANSACTION)) {
                statement.setInt(1, transaction.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bookInfo = itemBookInfo(bookInfo, resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("can't find by transaction " + this.getClass().getSimpleName(), e);
        }
        return bookInfo;
    }
}
