package project.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "deck_name")
    private String deckName;

    @OneToMany(mappedBy = "deck", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Card> cards = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Study study;

    @ManyToOne
    private User user;

    public Deck(int id, String deckName, List<Card> cards, Study study, User user) {
        this.id = id;
        this.deckName = deckName;
        this.cards = cards;
        this.study = study;
        this.user = user;
    }

    public Deck() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
