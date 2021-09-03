package com.handey.web.common.config;

import com.handey.web.repository.home.ToDoBoxRepository;
import com.handey.web.service.ToDoBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // spring data jpa 방법
    private final ToDoBoxRepository toDoBoxRepository;

    @Autowired
    public SpringConfig(ToDoBoxRepository toDoBoxRepository) {
        this.toDoBoxRepository = toDoBoxRepository;
    }


    // jpa 방법
//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // jdbcTemplate 방법
//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public ToDoBoxService toDoService() {
        return new ToDoBoxService(toDoBoxRepository);
//        return new ToDoService(toDoRepository());
    }

//    @Bean
//    public ToDoRepository toDoRepository() {
////        return new JpaToDoRepository(em);
////        return new JdbcTemplateToDoRepository(dataSource);
////        return new MemoryToDoRepository();
//    }
}
