package project.dao;

import org.springframework.stereotype.Repository;
import project.data.Card;
import project.data.Deck;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CardDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveCard(Card card) {
        entityManager.persist(card);
    }

    public Card findById(int id) {
        return entityManager.find(Card.class, id);
    }

    public void updateCard(Card card) {
        entityManager.merge(card);
    }

    public void deleteCard(Card card) {
        entityManager.remove(entityManager.contains(card) ? card : entityManager.merge(card));
    }

    public void saveCardsByDeckId(List<Card> cards) {
        for (Card card : cards) {
            Card newCard = new Card();
            newCard.setFront(card.getFront());
            newCard.setBack(card.getBack());
            newCard.setDeck(card.getDeck());
            entityManager.persist(newCard);
        }

    }
}
