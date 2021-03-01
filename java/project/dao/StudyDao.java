package project.dao;

import org.springframework.stereotype.Repository;
import project.data.Deck;
import project.data.Study;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudyDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveStudy(Study study) {
        entityManager.persist(study);
    }

    public void deleteStudy(Study study) {
        entityManager.remove(entityManager.contains(study) ? study : entityManager.merge(study));
    }
}
