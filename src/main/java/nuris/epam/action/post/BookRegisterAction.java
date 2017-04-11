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

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 07.04.2017.
 */
public class BookRegisterAction implements Action {
    private boolean wrong;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

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
            request.setAttribute(GENRE_LIST, bookService.getAllGenre());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String middleName = request.getParameter(MIDDLE_NAME);
        String isbn = request.getParameter(ISBN);
        String description = request.getParameter(DESCRIPTION);
        String name = request.getParameter(BOOK_NAME);
        String year = request.getParameter(YEAR);
        String genreName = request.getParameter(GENRE_NAME);
        String amount = request.getParameter(BOOK_AMOUNT);
        String price = request.getParameter(BOOK_PRICE);

        try {
            if (bookService.isBookIsbnAvailable(isbn)) {
                request.setAttribute(ISBN_ERROR, TRUE);
                wrong = true;
            }else {
                checkParamValid(ISBN, isbn, properties.getProperty(ISBN_VALID), request);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        genre.setId(TextParse.toInt(genreName));
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setMiddleName(middleName);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setIsbn(isbn);
        book.setDate(SqlDate.stringToDate(year));
        book.setDescription(description);
        book.setName(name);
        bookInfo.setBook(book);
        bookInfo.setAmount(TextParse.toInt(amount));
        bookInfo.setPrice(TextParse.toInt(price));

        checkParamValid(FIRST_NAME, firstName, properties.getProperty(NAME_VALID), request);
        checkParamValid(LAST_NAME, lastName, properties.getProperty(NAME_VALID), request);
        checkParamValid(MIDDLE_NAME, middleName, properties.getProperty(NAME_VALID), request);
        checkParamValid(DESCRIPTION, isbn, properties.getProperty(DESCRIPTION_VALID), request);
        checkParamValid(BOOK_NAME, name, properties.getProperty(BOOK_NAME_VALID), request);
        checkParamValid(YEAR, year, properties.getProperty(DATE_VALID), request);
        checkParamValid(BOOK_AMOUNT, amount, properties.getProperty(BOOK_COUNT_VALID), request);
        checkParamValid(BOOK_PRICE, price, properties.getProperty(BOOK_PRICE_VALID), request);

        if (wrong) {
            wrong = false;
            return new ActionResult(REGISTER_BOOK);
        } else {
            try {
                bookService.registerBook(bookInfo);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new ActionResult(WELCOME);
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }
}
