package nuris.epam.action;

/**
 * Created by User on 29.03.2017.
 */
public class ActionResult {
    private final String view;
    private final boolean redirect;

    public ActionResult(String page, boolean redirect) {
        this.view = page;
        this.redirect = redirect;
    }

    public ActionResult(String page) {
        this.view = page;
        this.redirect = false;
    }

    public String getView() {
        return view;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
