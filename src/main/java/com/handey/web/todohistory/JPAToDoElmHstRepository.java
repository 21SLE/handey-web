package com.handey.web.todohistory;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAToDoElmHstRepository implements ToDoElmHstRepository{
    private final EntityManager em;

    public JPAToDoElmHstRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ToDoElmHst save(ToDoElmHst toDoElmHst) {
        em.persist(toDoElmHst);
        return toDoElmHst;
    }

    @Override
    public Optional<ToDoElmHst> findById(Long id) {
        ToDoElmHst toDoElmHst = em.find(ToDoElmHst.class, id);
        return Optional.ofNullable(toDoElmHst);
    }

    @Override
    public List<ToDoElmHst> findByToDoBoxHstId(String toDoBoxHstId) {
        return em.createQuery("select m from ToDoElmHst m where m.toDoBoxHst.id = :todoBoxHstId", ToDoElmHst.class)
                .setParameter("todoBoxHstId", toDoBoxHstId)
                .getResultList();
    }

    @Override
    public List<ToDoElmHst> findAll() {
        return em.createQuery("select m from ToDoElmHst m", ToDoElmHst.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        ToDoElmHst toDoElmHst = em.find(ToDoElmHst.class, id);
        Assert.notNull(toDoElmHst,"To Do Element History must not be null!");
        em.remove(toDoElmHst);
    }
}
