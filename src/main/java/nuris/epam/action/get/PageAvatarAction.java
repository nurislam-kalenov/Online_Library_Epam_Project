package nuris.epam.action.get;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Avatar;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 07.04.2017.
 */
public class PageAvatarAction implements Action{
    private static final int DEFAULT_BUFFER_SIZE = 10240;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        CustomerService customerService = new CustomerService();
        Avatar avatar;
        InputStream inputStream = null;
        try {
            avatar = customerService.findByIdAvatar(5);
            inputStream = avatar.getPicture();
            resp.setContentType("image/jpeg");
            if (inputStream != null) {
                try {
                    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, DEFAULT_BUFFER_SIZE);
                         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(resp.getOutputStream(), DEFAULT_BUFFER_SIZE)) {

                        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                        int length;
                        while ((length = bufferedInputStream.read(buffer)) > 0) {
                            bufferedOutputStream.write(buffer, 0, length);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("avatar_pic" , inputStream);
    return new ActionResult("picture");
    }

}
