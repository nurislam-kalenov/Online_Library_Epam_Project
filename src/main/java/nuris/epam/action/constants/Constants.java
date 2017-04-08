package nuris.epam.action.constants;

/**
 * Created by User on 08.04.2017.
 */
public final class Constants {
    //Expressions for ActionResults redirect
    public static final String REGISTER = "register";
    public static final String WELCOME = "welcome";
    public static final String BOOKS = "books";
    public static final String REGISTER_BOOK = "register-book";

    //Validation regular expressions
    public static final String VALIDATION_PROPERTIES = "validation.properties";

    public static final String NAME_VALID = "name.valid";
    public static final String LIMIT_NUMBER_VALID = "limit.number.valid";
    public static final String DATE_VALID = "date.valid";
    public static final String ADDRESS_VALID = "address.valid";
    public static final String PASSWORD_VALID = "password.valid";
    public static final String EMAIL_VALID = "email.valid";
    public static final String ISBN_VALID = "book.isbn.valid";
    public static final String DESCRIPTION_VALID = "book.description.valid";
    public static final String BOOK_NAME_VALID="book.name.valid";
    public static final String BOOK_COUNT_VALID="book.count.valid";
    public static final String BOOK_PRICE_VALID="book.price.valid";

    //RegisterAction
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRM = "password_confirm";
    public static final String PHONE = "phone";
    public static final String BIRTHDAY = "birthday";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String CITY_LIST = "cityList";
    public static final String EMAIL_ERROR = "email_error";
    public static final String PASSWORD_ERROR = "password_error";
    public static final String TRUE = "true";
    public static final String ERROR = "_error";

    //LoginAction
    public static final String LOGIN = "login";

    //BookRegisterAction
    public static final String ISBN = "isbn";
    public static final String DESCRIPTION ="description";
    public static final String BOOK_NAME ="book_name";
    public static final String YEAR ="year";
    public static final String GENRE_NAME="genre_name";
    public static final String BOOK_AMOUNT="book_amount";
    public static final String BOOK_PRICE="book_price";
    public static final String GENRE_LIST="genreList";

    //SelectLanguageAction
    public static final String LANG = "lang";
    public static final String REFERER = "referer";
    public static final String CHARACTER_ENCODING = "UTF-8";

    //PageBookAction
    public static final String PAGE = "page";
    public static final String GENRE_ID ="genre_id";
    public static final String ATT_BOOKS="books";
    public static final String ATT_NO_PAGES="noOfPages";
    public static final String ATT_CURRENT_PAGE = "currentPage";
    public static final String ATT_GENRES="genres";

    //Session
    public static final String CUSTOMER_ID = "customerId";
    public static final String ROLE = "role";
    public static final String NAME = "name";
    private Constants() {}
}
