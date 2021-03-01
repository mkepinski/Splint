package project.dao;

import org.springframework.stereotype.Repository;
import project.data.Card;
import project.data.Deck;
import project.data.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public User getUserByLogin(String log) {
        Query select = entityManager.createQuery("SELECT u FROM User u WHERE u.login=:log");
        select.setParameter("log", log);
        return (User) select.getSingleResult();
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
