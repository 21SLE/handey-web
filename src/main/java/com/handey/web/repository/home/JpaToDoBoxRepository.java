package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaToDoBoxRepository implements ToDoBoxRepository{
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
    public List<ToDoBox> findAll() {
        return em.createQuery("select m from ToDoBox m", ToDoBox.class)
                .getResultList();
    }

    @Override
    public List<ToDoBox> findAllByUserId(Long userId) {
        return em.createQuery("select m from ToDoBox m where m.member.id = :userId", ToDoBox.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        ToDoBox toDoBox = em.find(ToDoBox.class, id);
        Assert.notNull(toDoBox,"To Do Box must not be null!");
        em.remove(toDoBox);
    }
}
