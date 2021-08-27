package com.handey.web;

import com.handey.web.repository.JdbcTemplateToDoRepository;
import com.handey.web.repository.MemoryToDoRepository;
import com.handey.web.repository.ToDoRepository;
import com.handey.web.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ToDoService toDoService() {
        return new ToDoService((toDoRepository()));
    }

    @Bean
    public ToDoRepository toDoRepository() {
        return new JdbcTemplateToDoRepository(dataSource);
//        return new MemoryToDoRepository();
    }
}
