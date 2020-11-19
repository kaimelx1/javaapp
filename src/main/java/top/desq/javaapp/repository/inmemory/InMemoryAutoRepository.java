package top.desq.javaapp.repository.inmemory;


import org.springframework.stereotype.Repository;
import top.desq.javaapp.model.Auto;
import top.desq.javaapp.repository.AutoRepository;
import top.desq.javaapp.util.Util;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryAutoRepository implements AutoRepository {

    private Map<Integer, Auto> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        Util.AUTO.forEach((x) -> save(x, 1));
    }

    @Override
    public Auto save(Auto auto, int userId) {
        if (auto.isNew()) {
            auto.setId(counter.incrementAndGet());
            repository.put(auto.getId(), auto);
            return auto;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(auto.getId(), (id, oldAuto) -> auto);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.remove(id) != null;
    }

    @Override
    public Auto get(int id, int userId) {
        return repository.get(id);
    }

    @Override
    public List<Auto> getAll(int userId) {
        return filterByPredicate(userId, meal -> true);
    }

    @Override
    public List<Auto> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, meal -> Util.isBetweenHalfOpen(meal.getDateTime(), startDateTime, endDateTime));
    }

    private List<Auto> filterByPredicate(int userId, Predicate<Auto> filter) {
        return repository.isEmpty() ? Collections.emptyList() :
                repository.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Auto::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
