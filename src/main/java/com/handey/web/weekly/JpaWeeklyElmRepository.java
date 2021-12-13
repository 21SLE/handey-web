package com.handey.web.weekly;

import com.handey.web.finishedweekly.FwBox;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public WeeklyElm save(WeeklyElm weeklyElm, WeeklyBox weeklyBox) {
        weeklyElm.setWeeklyBox(weeklyBox);
        em.persist(weeklyElm);
        return weeklyElm;
    }

    @Override
    public List<WeeklyElm> findByWeeklyId(Long weeklyId) {
        return em.createQuery("select m from WeeklyElm m where m.weeklyBox.id = :weeklyId", WeeklyElm.class)
                .setParameter("weeklyId", weeklyId)
                .getResultList();
    }

    @Override
    public Optional<WeeklyElm> findById(Long id) {
        try{
            WeeklyElm weeklyElm = em.find(WeeklyElm.class, id);
            return Optional.ofNullable(weeklyElm);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
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
