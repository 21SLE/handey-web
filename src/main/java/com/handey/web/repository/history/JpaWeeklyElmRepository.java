package com.handey.web.repository.history;

import com.handey.web.domain.history.WeeklyElm;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaWeeklyElmRepository implements WeeklyElmRepository{
    private final EntityManager em;

    public JpaWeeklyElmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public WeeklyElm save(WeeklyElm weeklyElm) {
        em.persist(weeklyElm);
        return weeklyElm;
    }

    @Override
    public Optional<WeeklyElm> findById(Long id) {
        WeeklyElm weeklyElm = em.find(WeeklyElm.class, id);
        return Optional.ofNullable(weeklyElm);
    }

    @Override
    public Optional<WeeklyElm> findBySubtitle(Long weeklyBoxId) {
        return Optional.empty();
    }

    @Override
    public List<WeeklyElm> findAll() {
        return em.createQuery("select m from WeeklyElm m", WeeklyElm.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        WeeklyElm weeklyElm = em.find(WeeklyElm.class, id);
        Assert.notNull(weeklyElm,"WeeklyElm Element must not be null!");
        em.remove(weeklyElm);
    }
}
