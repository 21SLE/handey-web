package com.handey.web;

import com.handey.web.aop.TimeTraceAop;
import com.handey.web.repository.home.JpaToDoRepository;
import com.handey.web.repository.home.ToDoRepository;
import com.handey.web.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // spring data jpa 방법
    private final ToDoRepository toDoRepository;

    @Autowired
    public SpringConfig(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
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
    public ToDoService toDoService() {
        return new ToDoService(toDoRepository);
//        return new ToDoService(toDoRepository());
    }

//    @Bean
//    public ToDoRepository toDoRepository() {
////        return new JpaToDoRepository(em);
////        return new JdbcTemplateToDoRepository(dataSource);
////        return new MemoryToDoRepository();
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
