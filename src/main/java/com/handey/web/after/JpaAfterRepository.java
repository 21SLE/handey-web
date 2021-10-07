package com.handey.web.after;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class JpaAfterRepository implements AfterRepository{
    private final EntityManager em;

    public JpaAfterRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public AfterBox save(AfterBox afterBox) {
        em.persist(afterBox);
        return afterBox;
    }

    @Override
    public Optional<AfterBox> findById(Long id) {
        AfterBox weeklyBox = em.find(AfterBox.class, id);
        return Optional.ofNullable(weeklyBox);
    }


    @Override
    public List<AfterBox> findByUserId(Long userId) {
        return em.createQuery("select m from AfterBox m where m.member.id = :userId", AfterBox.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<AfterBox> findAll() {
        return em.createQuery("select m from AfterBox as m", AfterBox.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        AfterBox afterBox = em.find(AfterBox.class, id);
        Assert.notNull(afterBox,"AfterBox must not be null!");
        em.remove(afterBox);
    }
}
