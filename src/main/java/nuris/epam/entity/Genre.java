package nuris.epam.entity;

/**
 * Created by User on 14.03.2017.
 */
public class Genre extends BaseEntity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId() + "/"+name;
    }
}
