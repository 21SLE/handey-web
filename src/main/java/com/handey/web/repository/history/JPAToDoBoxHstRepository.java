package com.handey.web.repository.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.home.ToDoElm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
        return Optional.empty();
    }

    @Override
    public List<ToDoBoxHst> findByDate(String saveDt) {
        return null;
    }

    @Override
    public List<ToDoBoxHst> findAll() {
        return em.createQuery("select m from ToDoBoxHst m", ToDoBoxHst.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {

    }
}
