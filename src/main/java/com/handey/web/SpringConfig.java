package com.handey.web;

import com.handey.web.repository.MemoryToDoRepository;
import com.handey.web.repository.ToDoRepository;
import com.handey.web.service.ToDoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ToDoService toDoService() {
        return new ToDoService((toDoRepository()));
    }

    @Bean
    public ToDoRepository toDoRepository() {
        return new MemoryToDoRepository();
    }
}
