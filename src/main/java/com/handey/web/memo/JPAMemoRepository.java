package com.handey.web.memo;

import com.handey.web.memo.Memo;
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
        Memo memo = em.find(Memo.class, userId);
        return Optional.ofNullable(memo);
    }
}
