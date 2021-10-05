package com.handey.web.afterhistory;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAfterHistoryRepository implements AfterHistoryRepository{
    private final EntityManager em;

    public JpaAfterHistoryRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public AfterHistory save(AfterHistory afterHistory) {
        return afterHistory;
    }

    @Override
    public Optional<AfterHistory> findById(Long id) {
        AfterHistory afterHistory = em.find(AfterHistory.class, id);
        return Optional.ofNullable(afterHistory);
    }

    @Override
    public List<AfterHistory> findByDate(Long userId, LocalDate date) {
        return em.createQuery("select m from AfterHistory m where m.member.id =: userId and m.hist_date = :date", AfterHistory.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<AfterHistory> findByUserId(Long userId) {
        return em.createQuery("select m from AfterHistory m where m.member.id =: userId", AfterHistory.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<AfterHistory> findAll() {
        return em.createQuery("select m from AfterHistory m", AfterHistory.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        AfterHistory afterHistory = em.find(AfterHistory.class, id);
        Assert.notNull(afterHistory,"AfterHistory Element must not be null!");
        em.remove(afterHistory);
    }
}
