package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaToDoBoxRepository implements ToDoBoxRepository {

    private final EntityManager em;

    public JpaToDoBoxRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ToDoBox save(ToDoBox toDoBox) {
        em.persist(toDoBox);
        return toDoBox;
    }

    @Override
    public Optional<ToDoBox> findById(Long id) {
        ToDoBox toDoBox = em.find(ToDoBox.class, id);
        return Optional.ofNullable(toDoBox);
    }

    @Override
    public Optional<ToDoBox> findByTitle(String title) {
        List<ToDoBox> result = em.createQuery("select m from ToDoBox m where m.title = :title", ToDoBox.class)
                .setParameter("title", title)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<ToDoBox> findAll() {

        //객체 자체를 조회
        return em.createQuery("select m from ToDoBox as m", ToDoBox.class)
                .getResultList();
    }
}
