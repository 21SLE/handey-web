package com.handey.web.finishedweekly;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaFwElmRepository implements FwElmRepository{
    private final EntityManager em;

    public JpaFwElmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public FwElm save(FwElm fwElm) {
        em.persist(fwElm);
        return fwElm;
    }

    @Override
    public Optional<FwElm> findById(Long id) {
        FwElm fwElm = em.find(FwElm.class, id);
        return Optional.ofNullable(fwElm);
    }

    @Override
    public Optional<FwElm> findByWeeklyElmId(Long weeklyElmId) {
        return Optional.ofNullable(em.createQuery("select m from FwElm m where m.weeklyElm.id = :weeklyElmId", FwElm.class)
                .setParameter("weeklyElmId", weeklyElmId)
                .getSingleResult());
    }

    @Override
    public void deleteById(Long id) {
        FwElm fwElm = em.find(FwElm.class, id);
        Assert.notNull(fwElm,"FwElm Element must not be null!");
        em.remove(fwElm);
    }
}
