package top.desq.javaapp.repository.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import top.desq.javaapp.model.Auto;
import top.desq.javaapp.repository.AutoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcAutoRepository implements AutoRepository {

    private static final RowMapper<Auto> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Auto.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertAuto;

    @Autowired
    public JdbcAutoRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertAuto = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("auto")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Auto save(Auto auto, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", auto.getId())
                .addValue("brand", auto.getBrand())
                .addValue("model", auto.getModel())
                .addValue("body", auto.getBody())
                .addValue("color", auto.getColor())
                .addValue("power", auto.getPower())
                .addValue("fuel", auto.getFuel())
                .addValue("date_time", auto.getDateTime())
                .addValue("user_id", userId);

        if (auto.isNew()) {
            Number newKey = insertAuto.executeAndReturnKey(map);
            auto.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE auto SET brand=:brand, model=:model, body=:body, color=:color, " +
                        "power=:power, fuel=:fuel, date_time=:date_time WHERE id=:id AND user_id=:user_id", map) == 0) {
            return null;
        }
        return auto;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM auto WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Auto get(int id, int userId) {
        List<Auto> auto = jdbcTemplate.query(
                "SELECT * FROM auto WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(auto);
    }

    @Override
    public List<Auto> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM auto WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Auto> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM auto WHERE user_id=?  AND date_time >=  ? AND date_time < ? ORDER BY date_time DESC",
                ROW_MAPPER, userId, startDateTime, endDateTime);
    }
}
