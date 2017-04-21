package nuris.epam.web.filters;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/kz/*")

public class SecurityFilter implements Filter {

    private static final String ADMIN = "admin";
    private static final String USER = "user";

    private List<String> guestAccess = new ArrayList<>();
    private List<String> userAccess = new ArrayList<>();
    private List<String> adminAccess = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initGuest();
        initUser();
        initAdmin();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getPathInfo();

        if (req.getSession().getAttribute(ROLE) == null) {
            if (!guestAccess.contains(path)) {
                resp.sendRedirect(WELCOME);
                return;
            }
        } else if (req.getSession().getAttribute(ROLE).equals(USER)) {
            if (!userAccess.contains(path)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else if (req.getSession().getAttribute(ROLE).equals(ADMIN)) {
            if (!adminAccess.contains(path)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else {
            filterChain.doFilter(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }


    private void initGuest() {
        guestAccess.add("/welcome");
        guestAccess.add("/register");
        guestAccess.add("/login");
        guestAccess.add("/set-language");
    }

    private void initUser() {
        userAccess.add("/books");
        userAccess.add("/account");
        userAccess.add("/profileEdit");
        userAccess.add("/aboutReader");
        userAccess.add("/aboutBook");
        userAccess.add("/deptCustomerBook");
        userAccess.add("/deleteProfile");
        userAccess.add("/password-edit");
        userAccess.add("/deleteProfile");
        userAccess.add("/returnCustomerBook");
        userAccess.add("/takeBook");
        userAccess.add("/returnBook");
        userAccess.add("/set-language");
        userAccess.add("/logout");
    }

    private void initAdmin() {
        adminAccess.add("/books");
        adminAccess.add("/account");
        adminAccess.add("/readers");
        adminAccess.add("/management");
        adminAccess.add("/aboutBook");
        adminAccess.add("/returnCustomerBook");
        adminAccess.add("/registerBook");
        adminAccess.add("/personalDataEdit");
        adminAccess.add("/bookEdit");
        adminAccess.add("/aboutReader");
        adminAccess.add("/aboutBook");
        adminAccess.add("/profileEdit");
        adminAccess.add("/adminReturnBook");
        adminAccess.add("/deleteBook");
        adminAccess.add("/deleteProfile");
        adminAccess.add("/logout");
        adminAccess.add("/set-language");
    }

    @Override
    public void destroy() {

    }
}
