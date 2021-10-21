package com.handey.web.memo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JPAMemoRepository implements MemoRepository{

    private final EntityManager em;

    public JPAMemoRepository(EntityManager em) { this.em = em; }

    @Override
    public Memo save(Memo memo) {
        em.persist(memo);
        return memo;
    }

    @Override
    public Optional<Memo> findByUserId(Long userId) {
        return Optional.ofNullable(em.createQuery("select m from Memo m where m.member.id =: userId", Memo.class)
                .setParameter("userId", userId)
                .getSingleResult());
    }
}
