package project.data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Size(min = 5, max = 12)
    private String login;

    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    private int enable;
    private boolean stayLogged;

    @OneToMany(mappedBy = "user")
    private List<Deck> deckList = new ArrayList<>();

    public User(int id, @NotEmpty String login, @NotEmpty String password, @Email @NotEmpty String email, int enable, boolean stayLogged, List<Deck> deckList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.stayLogged = stayLogged;
        this.deckList = deckList;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public boolean getStayLogged() {
        return stayLogged;
    }

    public void setStayLogged(boolean stayLogged) {
        this.stayLogged = stayLogged;
    }

    public List<Deck> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<Deck> deckList) {
        this.deckList = deckList;
    }
}
