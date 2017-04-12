package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Author;
import nuris.epam.entity.Book;
import nuris.epam.entity.BookInfo;
import nuris.epam.entity.Genre;
import nuris.epam.services.BookService;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.SqlDate;
import nuris.epam.utils.TextParse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nuris.epam.action.constants.Constants.*;


/**
 * Created by User on 12.04.2017.
 */
public class BookEditAction implements Action {
    private boolean wrong;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String id = req.getParameter("book_id");

        BookService bookService = new BookService();
        Properties properties = new Properties();

        BookInfo bookInfo = new BookInfo();
        Author author = new Author();
        Genre genre = new Genre();
        Book book = new Book();

        try {
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }

        try {
            req.setAttribute(GENRE_LIST, bookService.getAllGenre());
            bookInfo = bookService.findByBook(TextParse.toInt(id));
            book = bookInfo.getBook();
            author = book.getAuthor();

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String genreName = req.getParameter(GENRE_NAME);
        genre.setId(TextParse.toInt(genreName));

        if (availableParam(FIRST_NAME, req)) {
            String firstName = req.getParameter(FIRST_NAME);
            checkParamValid(FIRST_NAME, firstName, properties.getProperty(NAME_VALID), req);
            author.setFirstName(firstName);
        }
        if (availableParam(LAST_NAME, req)) {
            String lastName = req.getParameter(LAST_NAME);
            checkParamValid(LAST_NAME, lastName, properties.getProperty(NAME_VALID), req);
            author.setLastName(lastName);
        }
        if (availableParam(MIDDLE_NAME, req)) {
            String middleName = req.getParameter(MIDDLE_NAME);
            checkParamValid(MIDDLE_NAME, middleName, properties.getProperty(NAME_VALID), req);
            author.setMiddleName(middleName);
        }
        if (availableParam(DESCRIPTION, req)) {
            String descript = req.getParameter(DESCRIPTION);
            checkParamValid(DESCRIPTION, descript, properties.getProperty(DESCRIPTION_VALID), req);
            book.setDescription(descript);
        }
        if (availableParam(BOOK_NAME, req)) {
            String name = req.getParameter(BOOK_NAME);
            checkParamValid(BOOK_NAME, name, properties.getProperty(BOOK_NAME_VALID), req);
            book.setName(name);
        }
        if (availableParam(ISBN, req)) {
            String name = req.getParameter(ISBN);
            checkParamValid(ISBN, name, properties.getProperty(ISBN_VALID), req);
            book.setIsbn(name);
        }
        if (availableParam(YEAR, req)) {
            String year = req.getParameter(YEAR);
            checkParamValid(YEAR, year, properties.getProperty(DATE_VALID), req);
            book.setDate(SqlDate.stringToDate(year));
        }
        if (availableParam(BOOK_AMOUNT, req)) {
            String amount = req.getParameter(BOOK_AMOUNT);
            checkParamValid(BOOK_AMOUNT, amount, properties.getProperty(BOOK_COUNT_VALID), req);
            bookInfo.setAmount(TextParse.toInt(amount));
        }
        if (availableParam(BOOK_PRICE, req)) {
            String price = req.getParameter(BOOK_PRICE);
            checkParamValid(BOOK_PRICE, price, properties.getProperty(BOOK_PRICE_VALID), req);
            bookInfo.setPrice(TextParse.toInt(price));
        }

        if (wrong) {
            wrong = false;
            return new ActionResult(req.getHeader(REFERER), true);
        } else {
            try {
                book.setGenre(genre);
                book.setAuthor(author);
                bookService.updateBook(bookInfo);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new ActionResult(req.getHeader(REFERER), true);
    }


    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }

    private boolean availableParam(String param, HttpServletRequest request) {
        if (request.getParameter(param) != null && !request.getParameter(param).isEmpty()) {
            return true;
        }
        return false;
    }
}
