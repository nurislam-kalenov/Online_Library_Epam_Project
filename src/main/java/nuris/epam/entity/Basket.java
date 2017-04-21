package nuris.epam.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.04.2017.
 */
public class Basket {

    public List<BookInfo> books = new ArrayList<>();

    public void add(BookInfo book) {
        books.add(book);
    }

    public void delete(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.remove(i);
                return;
            }
        }
    }

    public boolean isAvailable(int id) {
        for (BookInfo book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List<BookInfo> getBooks() {
        return books;
    }
}
