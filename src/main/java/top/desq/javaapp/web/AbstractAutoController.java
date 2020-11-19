package top.desq.javaapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import top.desq.javaapp.model.Auto;
import top.desq.javaapp.service.AutoService;
import top.desq.javaapp.util.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static top.desq.javaapp.util.ValidationUtil.assureIdConsistent;
import static top.desq.javaapp.util.ValidationUtil.checkNew;

public abstract class AbstractAutoController {

    private static final Logger log = LoggerFactory.getLogger(AbstractAutoController.class);

    @Autowired
    private AutoService service;

    public Auto get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get auto {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete auto {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Auto> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Auto create(Auto auto) {
        int userId = SecurityUtil.authUserId();
        checkNew(auto);
        log.info("create {} for user {}", auto, userId);
        return service.create(auto, userId);
    }

    public void update(Auto auto, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(auto, id);
        log.info("update {} for user {}", auto, userId);
        service.update(auto, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<Auto> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                 @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
        return service.getBetweenInclusive(startDate, endDate, userId);
    }
}
