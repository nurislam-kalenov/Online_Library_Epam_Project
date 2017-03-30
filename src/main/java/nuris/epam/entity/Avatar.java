package nuris.epam.entity;

import java.io.InputStream;

/**
 * Created by User on 18.03.2017.
 */
public class Avatar extends BaseEntity{
    private InputStream picture;

    public InputStream getPicture() {
        return picture;
    }

    public void setPicture(InputStream picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return getId() + "/"+picture;
    }
}
