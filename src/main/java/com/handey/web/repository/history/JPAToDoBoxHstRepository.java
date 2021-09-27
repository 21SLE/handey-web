package com.handey.web.repository.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;
import com.handey.web.domain.home.ToDoElm;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAToDoBoxHstRepository implements ToDoBoxHstRepository{
    private final EntityManager em;

    public JPAToDoBoxHstRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public ToDoBoxHst save(ToDoBoxHst toDoBoxHst) {
        em.persist(toDoBoxHst);
        return toDoBoxHst;
    }

    @Override
    public Optional<ToDoBoxHst> findById(Long id) {
        ToDoBoxHst toDoBoxHst = em.find(ToDoBoxHst.class, id);
        return Optional.ofNullable(toDoBoxHst);
    }

    @Override
    public List<ToDoBoxHst> findByDate(LocalDate saveDt) {
        return em.createQuery("select m from ToDoBoxHst m where m.saveDt = :saveDt", ToDoBoxHst.class)
                .setParameter("saveDt", saveDt)
                .getResultList();
    }

    @Override
    public List<ToDoBoxHst> findAll() {
        return em.createQuery("select m from ToDoBoxHst m", ToDoBoxHst.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        ToDoBoxHst toDoboxHst = em.find(ToDoBoxHst.class, id);
        Assert.notNull(toDoboxHst,"To Do Box History must not be null!");
        em.remove(toDoboxHst);
    }
}
