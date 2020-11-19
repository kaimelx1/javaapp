package top.desq.javaapp.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import top.desq.javaapp.model.Auto;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudAutoRepository extends JpaRepository<Auto, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Auto m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Auto m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Auto> getAll(@Param("userId") int userId);

    @Query("SELECT m from Auto m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Auto> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT m FROM Auto m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2")
    Auto getWithUser(int id, int userId);

}
