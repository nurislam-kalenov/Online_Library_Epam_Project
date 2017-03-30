package nuris.epam.entity;

import java.sql.Date;

/**
 * Created by User on 17.03.2017.
 */
public class Customer extends BaseEntity {

    private Date registerDate;
    private String password;
    private String login;
    private Person person;
    private CustomerRole customerRole;
    private Avatar avatar;

    public Customer() {
        person = new Person();
        customerRole = new CustomerRole();
        avatar = new Avatar();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CustomerRole getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(CustomerRole customerRole) {
        this.customerRole = customerRole;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return getId() + "/" + registerDate + "/" + login + "/" + password +""+person +" "+customerRole ;
    }
}
