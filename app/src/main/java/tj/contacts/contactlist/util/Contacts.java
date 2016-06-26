package tj.contacts.contactlist.util;


import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by Timur Mahmudov on 09.05.2016.
 */
public class Contacts extends RealmObject {

    /*@PrimaryKey*/
    private int id;
    @Index
    private String name;
    private String lastname;
    private String company;
    private String phone;
    private String email;
    private Boolean favorits;

    public Contacts(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFavorits() {
        return favorits;
    }

    public void setFavorits(Boolean favorits) {
        this.favorits = favorits;
    }
}
