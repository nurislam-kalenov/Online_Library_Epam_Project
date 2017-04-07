package nuris.epam.action.post;

import nuris.epam.action.exception.ActionException;
import nuris.epam.action.manager.Action;
import nuris.epam.action.manager.ActionResult;
import nuris.epam.entity.Avatar;
import nuris.epam.service.CustomerService;
import nuris.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Created by User on 07.04.2017.
 */
public class UploadPictureAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        CustomerService customerService = new CustomerService();
        Avatar avatar = new Avatar();
        Part filePart = null;

        try {
            filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            avatar.setPicture(fileContent);
            if(filePart.getContentType().equals("image/jpeg")) {
                customerService.uploadAvatar(avatar);
            }else{
                System.out.println("Неверный формат ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ActionResult("books", true);

    }
}
