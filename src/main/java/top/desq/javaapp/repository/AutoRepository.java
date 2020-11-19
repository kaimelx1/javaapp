package top.desq.javaapp.repository;

import top.desq.javaapp.model.Auto;

import java.time.LocalDateTime;
import java.util.List;

public interface AutoRepository {

    // null if not found, when updated
    Auto save(Auto user, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Auto get(int id, int userId);

    // ORDERED dateTime desc
    List<Auto> getAll(int userId);

    // ORDERED dateTime desc
    List<Auto> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default Auto getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
