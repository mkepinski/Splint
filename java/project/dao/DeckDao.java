package project.dao;

import org.springframework.stereotype.Repository;
import project.data.Card;
import project.data.Deck;
import project.data.Study;
import project.data.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DeckDao {

    @PersistenceContext
    EntityManager entityManager;
    private CardDao cardDao;

    public DeckDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public void saveDeck(Deck deck) {
        entityManager.persist(deck);
    }

    public void updateCurrentDeck(Deck deck, List<Card> cards) {
//        removeCardsFromDeck(deck);
//        cardDao.saveCardsByDeckId(cards);
//        deck.setCards(cards);
        deck.setCards(cards);

        entityManager.merge(deck);
    }

    public Deck findById(int id) {
        return entityManager.find(Deck.class, id);
    }

    public Deck getDeckByName(String deck_name, User user) {
        Query query = entityManager.createQuery("SELECT d FROM Deck d WHERE d.deckName=:dName AND d.user=:usr");
        query.setParameter("dName", deck_name);
        query.setParameter("usr", user);
        return (Deck) query.getSingleResult();
    }

    public List<Deck> getAllUserDecks(User user) {
        Query query = entityManager.createQuery("SELECT d FROM Deck d WHERE d.user=:user");
        query.setParameter("user", user);
        return query.getResultList();
    }

    public boolean deckExists(User user, String deck_name) {
        Query query = entityManager.createQuery("SELECT d FROM Deck d WHERE d.deckName=:dName AND d.user=:usr");
        query.setParameter("dName", deck_name);
        query.setParameter("usr", user);
        List<Deck> deckList = query.getResultList();
        return deckList.isEmpty();
    }

    public void deleteDeck(Deck deck) {
        entityManager.remove(entityManager.contains(deck) ? deck : entityManager.merge(deck));
    }

    public List<Deck> getScheduledDecks(User user) {
        List<Deck> allUserDecks = getAllUserDecks(user);
        List<Deck> scheduledDecks = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Deck deck : allUserDecks) {
            Study study = deck.getStudy();
            LocalDate createdDate = study.getCreated();
            System.out.println("createdDate: " + createdDate.toString());
            if(createdDate.plusDays(1).equals(today) || createdDate.plusDays(3).equals(today) || createdDate.plusDays(7).equals(today) || createdDate.plusDays(30).equals(today) || createdDate.plusDays(90).equals(today)) {
                scheduledDecks.add(deck);
            }
        }
        System.out.println(scheduledDecks.toString());
        return scheduledDecks;
    }

    public void removeCardsFromDeck(Deck deck) {
        List<Card> cards = deck.getCards();

        for (Card card : cards) {
            cardDao.deleteCard(card);
        }
    }
}
