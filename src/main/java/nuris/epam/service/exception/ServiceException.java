package nuris.epam.service.exception;

/**
 * Created by User on 20.03.2017.
 */
public class ServiceException extends Exception {

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
