package nuris.epam.services;

import nuris.epam.dao.AuthorDao;
import nuris.epam.dao.BookDao;
import nuris.epam.dao.BookInfoDao;
import nuris.epam.dao.GenreDao;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Genre;
import nuris.epam.services.exceptions.ServiceException;

import java.util.List;

/**
 * Created by User on 20.03.2017.
 */
public class BookService {

    public void registerBook(BookInfo bookInfo) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());

                daoFactory.startTransaction();
                authorDao.insert(bookInfo.getBook().getAuthor());
                bookDao.insert(bookInfo.getBook());
                bookInfoDao.insert(bookInfo);
                daoFactory.commitTransaction();

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("can't rollback transaction", e);
                }
                throw new ServiceException("can't register book", e);
            }
        }

    }

    public BookInfo findById(int id) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                BookInfo book = bookInfoDao.findById(id);
                return book;
            } catch (DaoException e) {
                throw new ServiceException("can't find by id book", e);
            }
        }
    }

    public BookInfo findByBook(int id) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());

                BookInfo bookInfo = bookInfoDao.findByBook(id);
                Book book = bookDao.findByBookInfo(bookInfo);

                fillBook(book);
                bookInfo.setBook(book);
                return bookInfo;
            } catch (DaoException e) {
                throw new ServiceException("can't find by id book", e);
            }
        }
    }

    public void deleteBook(BookInfo bookinfo) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                Book book = bookDao.findByBookInfo(bookinfo);
                Author author = authorDao.findByBook(book);

                daoFactory.startTransaction();
                bookInfoDao.delete(bookinfo);
                bookDao.delete(book);
                authorDao.delete(author);

                daoFactory.commitTransaction();
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("can't rollback transaction", e);
                }
                throw new ServiceException("can't delete book", e);
            }
        }
    }

    public void updateBook(BookInfo bookInfo) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());

                Author author = bookInfo.getBook().getAuthor();
                Book book  = bookInfo.getBook();

                daoFactory.startTransaction();
                authorDao.update(author);
                bookDao.update(book);
                bookInfoDao.update(bookInfo);
                daoFactory.commitTransaction();
            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    e1.printStackTrace();
                }
                throw new ServiceException("can't update book", e);
            }
        }

    }

    public void updateBookInfo(BookInfo bookInfo) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookInfoDao bookInfoDao = (BookInfoDao) daoFactory.getDao(daoFactory.typeDao().getBookInfoDao());
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                Book book = bookDao.findByBookInfo(bookInfo);
                bookInfo.setBook(book);
                bookInfoDao.update(bookInfo);
            } catch (DaoException e) {
                throw new ServiceException("can't update book", e);
            }
        }
    }

    private void fillBook(Book book) throws ServiceException {
        try {
            if (book != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {
                    AuthorDao authorDao = (AuthorDao) daoFactory.getDao(daoFactory.typeDao().getAuthorDao());
                    GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
                    book.setAuthor(authorDao.findByBook(book));
                    book.setGenre(genreDao.findByBook(book));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("can't fill book", e);
        }
    }

    public List<Genre> getAllGenre() throws ServiceException {
        List<Genre> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                GenreDao genreDao = (GenreDao) daoFactory.getDao(daoFactory.typeDao().getGenreDao());
                list = genreDao.getAll();
                return list;
            }
        } catch (DaoException e) {
            throw new ServiceException("can't get all genre", e);
        }
    }

    public int getBookCountByGenre(Genre genre) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                int count = bookDao.getBookCountByGenre(genre);
                return count;
            } catch (DaoException e) {
                throw new ServiceException("can't get count book", e);
            }
        }
    }

    public List<Book> getBookByName(String name) throws ServiceException {
        List<Book> books;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                books = bookDao.findByName(name);
                return books;
            } catch (DaoException e) {
                throw new ServiceException("can't find book by name", e);
            }
        }
    }

    public List<Book> getListBook(Genre genre, int start, int end) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                List<Book> list = bookDao.getLimitBookByGenre(genre, start, end);
                for (Book book : list) {
                    fillBook(book);
                }
                return list;
            } catch (DaoException e) {
                throw new ServiceException("can't get list by genre book", e);
            }
        }
    }

    private Book getBookByIsbn(String isbn) throws ServiceException {
        Book book;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                BookDao bookDao = (BookDao) daoFactory.getDao(daoFactory.typeDao().getBookDao());
                book = bookDao.findByIsbn(isbn);
                return book;
            } catch (DaoException e) {
                throw new ServiceException("can't find book by name", e);
            }
        }
    }

    public boolean isBookIsbnAvailable(String isbn) throws ServiceException {
        return getBookByIsbn(isbn) != null;
    }
}
