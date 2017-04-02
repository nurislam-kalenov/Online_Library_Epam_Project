package nuris.epam.action.manage;

/**
 * Created by User on 02.04.2017.
 */
public class ActionException extends Exception{

    public ActionException(Exception e) {
        super(e);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }

}
