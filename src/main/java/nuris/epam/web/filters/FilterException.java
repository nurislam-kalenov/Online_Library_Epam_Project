package nuris.epam.web.filters;
public class FilterException extends Exception {

    public FilterException(String msg, Exception e) {
        super(msg, e);
    }
}
