package com.handey.web.common.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // spring data jpa 방법
//    private final ToDoBoxRepository toDoBoxRepository;
//    private final ToDoElmRepository toDoElmRepository;
//
//    @Autowired
//    public SpringConfig(ToDoBoxRepository toDoBoxRepository, ToDoElmRepository toDoElmRepository) {
//        this.toDoBoxRepository = toDoBoxRepository;
//        this.toDoElmRepository = toDoElmRepository;
//    }


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

//    @Bean
//    public ToDoBoxService toDoService() {
//        return new ToDoBoxService(toDoBoxRepository, toDoElmRepository);
////        return new ToDoService(toDoRepository());
//    }

//    @Bean
//    public ToDoRepository toDoRepository() {
////        return new JpaToDoRepository(em);
////        return new JdbcTemplateToDoRepository(dataSource);
////        return new MemoryToDoRepository();
//    }

//    @Bean
//    public WeeklyElmRepository weeklyElmRepository() {
//        return new WeeklyElmRepository();
//    }
}
