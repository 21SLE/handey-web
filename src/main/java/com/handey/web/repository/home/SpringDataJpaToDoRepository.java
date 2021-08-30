package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaToDoRepository extends JpaRepository<ToDoBox, Long>, ToDoRepository {
    @Override
    ToDoBox save(ToDoBox toDoBox);

    @Override
    Optional<ToDoBox> findById(Long id);

    // JPQL select m from ToDoBox m where m.name = ?
    @Override
    Optional<ToDoBox> findByTitle(String title);

    @Override
    List<ToDoBox> findAll();

//    Optional<Member> findByNameAndId(String name, Long id);
//    Optional<Member> findByNameOrId(String name, Long id);
}
