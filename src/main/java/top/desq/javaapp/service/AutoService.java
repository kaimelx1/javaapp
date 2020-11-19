package top.desq.javaapp.service;


import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.desq.javaapp.model.Auto;
import top.desq.javaapp.repository.AutoRepository;

import java.time.LocalDate;
import java.util.List;

import static top.desq.javaapp.util.DateTimeUtil.atStartOfDayOrMin;
import static top.desq.javaapp.util.DateTimeUtil.atStartOfNextDayOrMax;
import static top.desq.javaapp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class AutoService {

    //@Autowired
    //@Qualifier("jpaAutoRepository")
    private AutoRepository repository;

    public AutoService(AutoRepository repository) {
        this.repository = repository;
    }

    public Auto get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Auto> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Auto auto, int userId) {
        Assert.notNull(auto, "auto must not be null");
        checkNotFoundWithId(repository.save(auto, userId), auto.getId());
    }

    public Auto create(Auto auto, int userId) {
        Assert.notNull(auto, "auto must not be null");
        return repository.save(auto, userId);
    }

    public List<Auto> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }
}
