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
            properties.load(RegisterAction.class.getClassLoader().getResourceAsStream("validation.properties"));
        } catch (IOException e) {
            throw new ActionException("Can't load properties", e);
        }

        try {
            request.setAttribute("genreList", bookService.getAllGenre());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String middleName = request.getParameter("middle_name");
        String isbn = request.getParameter("isbn");
        String description = request.getParameter("description");
        String name = request.getParameter("book_name");
        String year = request.getParameter("year");
        String genreName = request.getParameter("genre_name");
        String amount = request.getParameter("book_amount");
        String price = request.getParameter("book_price");

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

        checkParamValid("first_name", firstName, properties.getProperty("name.valid"), request);
        checkParamValid("last_name", lastName, properties.getProperty("name.valid"), request);
        checkParamValid("middle_name", middleName, properties.getProperty("name.valid"), request);
        checkParamValid("isbn", isbn, properties.getProperty("isbn.valid"), request);
        checkParamValid("description", isbn, properties.getProperty("description.book.valid"), request);
        checkParamValid("book_name", name, properties.getProperty("name.valid"), request);
        checkParamValid("year", year, properties.getProperty("date.valid"), request);
        checkParamValid("book_amount", amount, properties.getProperty("count.book.valid"), request);
        checkParamValid("book_price", price, properties.getProperty("price.book.valid"), request);

        if (wrong) {
            wrong = false;
            return new ActionResult("register-book");
        } else {
            try {
                bookService.registerBook(bookInfo);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return new ActionResult("welcome");
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + "_error", "true");
            System.out.println(paramName + "_error");
            wrong = true;
        }
    }
}
