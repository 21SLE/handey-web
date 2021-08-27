package com.handey.web.repository;

import com.handey.web.domain.ToDoBox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateToDoRepository implements ToDoRepository{

    private final JdbcTemplate jdbcTemplate;

    // 생성자 하나면 @Autowired 생략 가능
    public JdbcTemplateToDoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ToDoBox save(ToDoBox toDoBox) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", toDoBox.getToDoBoxTitle());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        toDoBox.setToDoBoxId(key.longValue());
        return toDoBox;
    }

    @Override
    public Optional<ToDoBox> findById(Long toDoBoxId) {
        List<ToDoBox> result = jdbcTemplate.query("select * from todo where id = ?", toDoBoxRowMapper(), toDoBoxId);
        return result.stream().findAny();
    }

    @Override
    public Optional<ToDoBox> findByName(String toDoBoxTitle) {
        List<ToDoBox> result = jdbcTemplate.query("select * from todo where title = ?", toDoBoxRowMapper(), toDoBoxTitle);
        return result.stream().findAny();
    }

    @Override
    public List<ToDoBox> findAll() {
        return jdbcTemplate.query("select * from todd", toDoBoxRowMapper());
    }

    private RowMapper<ToDoBox> toDoBoxRowMapper() {
        return (rs, rowNum) -> {
            ToDoBox toDoBox = new ToDoBox();
            toDoBox.setToDoBoxId(rs.getLong("id"));
            toDoBox.setToDoBoxTitle(rs.getString("title"));
            return toDoBox;
        };
    }
}
