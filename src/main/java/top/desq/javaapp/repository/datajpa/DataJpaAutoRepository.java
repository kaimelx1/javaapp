package top.desq.javaapp.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.desq.javaapp.model.Auto;
import top.desq.javaapp.repository.AutoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaAutoRepository implements AutoRepository {

    private final CrudAutoRepository crudAutoRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaAutoRepository(CrudAutoRepository crudAutoRepository, CrudUserRepository crudUserRepository) {
        this.crudAutoRepository = crudAutoRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Auto save(Auto auto, int userId) {
        if (!auto.isNew()) { // && get(auto.getId(), userId) == null
            return null;
        }
        auto.setUser(crudUserRepository.getOne(userId));
        return crudAutoRepository.save(auto);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudAutoRepository.delete(id, userId) != 0;
    }

    @Override
    public Auto get(int id, int userId) {
        return crudAutoRepository.findById(id)
                .filter(auto -> auto.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Auto> getAll(int userId) {
        return crudAutoRepository.getAll(userId);
    }

    @Override
    public List<Auto> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudAutoRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }
}
