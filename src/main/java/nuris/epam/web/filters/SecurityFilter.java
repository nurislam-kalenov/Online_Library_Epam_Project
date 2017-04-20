package nuris.epam.web.filters;

import nuris.epam.action.post.LogoutAction;
import nuris.epam.entity.CustomerRole;

import static nuris.epam.action.constants.Constants.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/kz/*")

public class SecurityFilter implements Filter {
    private List<String> guestAccess = new ArrayList<>();
    private List<String> userAccess = new ArrayList<>();
    private List<String> adminAccess = new ArrayList<>();

    public void initUser() {
        userAccess.add("/books");
        userAccess.add("/account");
        userAccess.add("/profileEdit");
        userAccess.add("/aboutReader");
        userAccess.add("/aboutBook");
        userAccess.add("/deptCustomerBook");

    }

    public void initAdmin() {
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
    }

    public void initGuest() {
        guestAccess.add("/welcome");
        guestAccess.add("/register");
        guestAccess.add("/login");
    }

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

        if (req.getSession().getAttribute("role") == null) {
            for (String customerPath : guestAccess) {
                if (path.startsWith(customerPath)) {
                    filterChain.doFilter(req, resp);
                    return;
                }
            }
            resp.sendRedirect(req.getContextPath() + "/kz/welcome");
            return;
        }


        if (req.getSession().getAttribute("role").equals("user")) {
            for (String customerPath : userAccess) {
                if (path.startsWith(customerPath)) {
                    filterChain.doFilter(req, resp);
                    return;
                }
            }
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/kz/welcome");
            return;
        }


    }




    @Override
    public void destroy() {

    }
}
