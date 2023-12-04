package Data;

public class User {
    private Long id;
    private String name;
    private String lastname;
    private String mail;
    private String password;

    public User (long id, String name, String lastname, String mail, String password) {
        this.setId(id);
        this.setName(name);
        this.setLastname(lastname);
        this.setMail(mail);
        this.setPassword(password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
