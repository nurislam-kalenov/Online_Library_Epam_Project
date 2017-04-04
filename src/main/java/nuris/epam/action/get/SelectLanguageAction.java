package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * Created by User on 04.04.2017.
 */
public class SelectLanguageAction implements Action{
    private static final String LANG = "lang";
    private static final String REFERER = "referer";
    private static final String CHARACTER_ENCODING = "UTF-8";


    @Override
    public ActionResult execute(HttpServletRequest req , HttpServletResponse response) throws ActionException {
        String language = req.getParameter(LANG);
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        try {
            req.setCharacterEncoding(CHARACTER_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new ActionException("can't set character encoding", e);
        }
        return new ActionResult(req.getHeader(REFERER), true);

    }
}
