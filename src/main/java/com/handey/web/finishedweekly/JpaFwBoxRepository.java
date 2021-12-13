package com.handey.web.finishedweekly;

import com.handey.web.member.Member;
import com.handey.web.todohistory.ToDoBoxHst;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaFwBoxRepository implements FwBoxRepository{

    private final EntityManager em;

    public JpaFwBoxRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public FwBox save(FwBox fwBox) {
        em.persist(fwBox);
        return fwBox;
    }

    @Override
    public Optional<FwBox> findById(Long id) {
        FwBox fwBox = em.find(FwBox.class, id);
        return Optional.ofNullable(fwBox);
    }

    @Override
    public Optional<FwBox> findByWeeklyBoxIdAndDate(Long weeklyBoxId, LocalDate saveDt) {
        try{
            return Optional.ofNullable(em.createQuery("select m from FwBox m where m.weeklyBox.id = :weeklyBoxId and m.saveDt = :saveDt", FwBox.class)
                    .setParameter("weeklyBoxId", weeklyBoxId).setParameter("saveDt", saveDt)
                    .getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public List<FwBox> findByUserIdAndDate(Long userId, LocalDate saveDt) {
        return em.createQuery("select m from FwBox m where m.member.id = :userId and m.saveDt = :saveDt", FwBox.class)
                .setParameter("userId", userId).setParameter("saveDt", saveDt)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        FwBox fwBox = em.find(FwBox.class, id);
        Assert.notNull(fwBox,"FwBox must not be null!");
        em.remove(fwBox);
    }

    @Override
    public List<FwBox> findByUserId(Long userId) {
        return em.createQuery("select m from FwBox m where m.member.id = :userId", FwBox.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
