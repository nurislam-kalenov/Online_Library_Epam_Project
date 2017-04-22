package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Book;
import nuris.epam.entity.Genre;
import nuris.epam.services.BookService;
import nuris.epam.services.exceptions.ServiceException;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 06.04.2017.
 */
public class PageBookAction implements Action {
    private int genreId = 0;
    private int genreState = 1;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        BookService bookService = new BookService();
        Genre genre = new Genre();

        String name = null;
        int page = 1;
        int recordPerPage = 3;

        try {
            if (request.getParameter(SEARCH) != null) {
                name = request.getParameter(SEARCH);
            }
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            }
            if (request.getParameter(GENRE_ID) != null) {
                genreId = Integer.parseInt(request.getParameter(GENRE_ID));
                genre.setId(genreId);
                genreState = genreId;
            } else {
                genre.setId(genreState);
            }

            List<Book> books = bookService.getListBook(genre, page, recordPerPage);
            List<Genre> genres = bookService.getAllGenre();
            List<Book> book = bookService.getBookByName(name);

            int noOfRecords = bookService.getBookCountByGenre(genre);
            int noOfPages = (int) Math.ceil(noOfRecords * CONVERT_TO_DOUBLE / recordPerPage);

            if (book.size() > MIN_COUNT_BOOKS) {
                request.setAttribute(ATT_BOOKS, book);
                request.setAttribute(ATT_GENRES, genres);
            } else {
                request.setAttribute(ATT_BOOKS, books);
                request.setAttribute(ATT_GENRES, genres);
                request.setAttribute(ATT_NO_PAGES, noOfPages);
                request.setAttribute(ATT_CURRENT_PAGE, page);

            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult(BOOKS);
    }


}
