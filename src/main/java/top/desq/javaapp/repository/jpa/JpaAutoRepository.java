package top.desq.javaapp.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.desq.javaapp.model.Auto;
import top.desq.javaapp.model.User;
import top.desq.javaapp.repository.AutoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaAutoRepository implements AutoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Auto save(Auto auto, int userId) {
        auto.setUser(em.getReference(User.class, userId));
        if (auto.isNew()) {
            em.persist(auto);
            return auto;
        } else if (get(auto.getId(), userId) == null) {
            return null;
        }
        return em.merge(auto);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Auto.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Auto get(int id, int userId) {
        Auto auto = em.find(Auto.class, id);
        return auto != null && auto.getUser().getId() == userId ? auto : null;
    }

    @Override
    public List<Auto> getAll(int userId) {
        return em.createNamedQuery(Auto.ALL_SORTED, Auto.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Auto> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Auto.GET_BETWEEN, Auto.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }

}
