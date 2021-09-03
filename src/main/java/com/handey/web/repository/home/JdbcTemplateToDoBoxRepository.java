package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateToDoBoxRepository implements ToDoBoxRepository {

    private final JdbcTemplate jdbcTemplate;

    // 생성자 하나면 @Autowired 생략 가능
    public JdbcTemplateToDoBoxRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ToDoBox save(ToDoBox toDoBox) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", toDoBox.getTitle());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
//        toDoBox.setId(key.LongValue());
        return toDoBox;
    }

    @Override
    public Optional<ToDoBox> findById(Long id) {
        List<ToDoBox> result = jdbcTemplate.query("select * from todo where id = ?", toDoBoxRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<ToDoBox> findByTitle(String title) {
        List<ToDoBox> result = jdbcTemplate.query("select * from todo where title = ?", toDoBoxRowMapper(), title);
        return result.stream().findAny();
    }

    @Override
    public List<ToDoBox> findAll() {
        return jdbcTemplate.query("select * from todd", toDoBoxRowMapper());
    }

    @Override
    public void deleteById(Long id) {

    }

    private RowMapper<ToDoBox> toDoBoxRowMapper() {
        return (rs, rowNum) -> {
            ToDoBox toDoBox = new ToDoBox();
            toDoBox.setId(rs.getLong("id"));
            toDoBox.setTitle(rs.getString("title"));
            return toDoBox;
        };
    }
}
