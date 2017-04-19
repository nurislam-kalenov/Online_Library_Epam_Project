package nuris.epam.action.constants;

/**
 * Created by User on 08.04.2017.
 */
public final class Constants {
    //Expressions for ActionResults redirect
    public static final String REGISTER = "register";
    public static final String WELCOME = "welcome";
    public static final String BOOKS = "books";
    public static final String REGISTER_BOOK = "registerBook";
    public static final String ACCOUNT = "account";
    public static final String PROFILE_EDIT = "profileEdit";
    public static final String PERSONAL_DATA_EDIT = "personalDataEdit";
    public static final String ABOUT_BOOK = "aboutBook";
    public static final String BOOK_EDIT = "bookEdit";
    public static final String CUSTOMER_BOOK = "returnCustomerBook";
    public static final String DEPT_CUSTOMER_BOOK = "deptCustomerBook";
    public static final String MANAGEMENT = "management";
    public static final String ABOUT_READER = "aboutReader";
    public static final String READERS = "readers";

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
    public static final String BOOK_NAME_VALID = "book.name.valid";
    public static final String BOOK_COUNT_VALID = "book.count.valid";
    public static final String BOOK_PRICE_VALID = "book.price.valid";

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
    public static final String LOGIN_ERROR = "login_error";

    //BookRegisterAction
    public static final String ISBN = "isbn";
    public static final String DESCRIPTION = "description";
    public static final String BOOK_NAME = "book_name";
    public static final String YEAR = "year";
    public static final String GENRE_NAME = "genre_name";
    public static final String BOOK_AMOUNT = "book_amount";
    public static final String BOOK_PRICE = "book_price";
    public static final String GENRE_LIST = "genreList";
    public static final String ISBN_ERROR = "isbn_error";

    //SelectLanguageAction
    public static final String LANG = "lang";
    public static final String REFERER = "referer";
    public static final String CHARACTER_ENCODING = "UTF-8";

    //PageBookAction
    public static final String PAGE = "page";
    public static final String GENRE_ID = "genre_id";
    public static final String ATT_BOOKS = "books";
    public static final String ATT_NO_PAGES = "noOfPages";
    public static final String ATT_CURRENT_PAGE = "currentPage";
    public static final String ATT_GENRES = "genres";
    public static final String SEARCH = "search";

    //PageCustomerAccountAction
    public static final String ATT_CUSTOMER_INFO = "customer_info";

    //PageAboutBookAction
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_INFO = "book_info";
    public static final String ATT_COUNT_ERROR = "count_error";
    public static final String ATT_OVER_ERROR = "over_error";

    //PasswordEditAction
    public static final String OLD_PASSWORD = "old_pass";
    public static final String OLD_PASSWORD_ERROR = "old_pass_error";

    //PageReturnCustomerBookAction
    public static final String ATT_CUSTOMER_BOOK = "customer_book";

    //CustomerAction
    public static final String RETURN_BOOK = "return_book";

    //DeleteBookAction
    public static final String DELETE_ID = "delete_id";

    //PageManagementAction
    public static final String ACTIVE = "active";
    public static final String ATT_MANAGEMENTS = "managements";
    public static final String ATT_IS_ACTIVE_STATE = "isActiveState";

    //AdminReturnBookAction
    public static final String MANAGEMENT_ID= "management_id";

    //PageAboutReaderAction
    public static final String READER_ID = "customer_id";
    public static final String TRANSACTIONS = "transactions";

    //PageReadersAction
    public static final String ATT_READERS = "readers";

    //DeleteProfileAction
    public static final String ADMIN = "admin";

    //Session
    public static final String CUSTOMER_ID = "customerId";
    public static final String ROLE = "role";
    public static final String NAME = "name";

    //View
    public static final String PATH_TO_JSP = "/WEB-INF/jsp/";
    public static final String JSP_FORMAT = ".jsp";


    private Constants() {
    }
}
