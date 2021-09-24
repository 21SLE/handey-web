package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAToDoElmRepository implements ToDoElmRepository{

    private final EntityManager em;

    public JPAToDoElmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ToDoElm save(ToDoBox toDoBox, ToDoElm toDoElm) {
        toDoElm.setToDoBox(toDoBox);
        em.persist(toDoElm);
        return toDoElm;
    }

    @Override
    public Optional<ToDoElm> findById(Long id) {
        ToDoElm toDoElm = em.find(ToDoElm.class, id);
        return Optional.ofNullable(toDoElm);
    }

    @Override
    public List<ToDoElm> findByToDoBoxId(Long toDoBoxId) {
        return em.createQuery("select m from ToDoElm m where m.toDoBox.id = :toDoBoxId", ToDoElm.class)
                .setParameter("toDoBoxId", toDoBoxId)
                .getResultList();
    }

    @Override
    public List<ToDoElm> findAll() {
        return em.createQuery("select m from ToDoElm m", ToDoElm.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ToDoElm toDoElm = em.find(ToDoElm.class, id);
        Assert.notNull(toDoElm,"To Do Element must not be null!");
        em.remove(toDoElm);
    }
}
