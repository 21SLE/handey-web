package com.handey.web.repository.home;

import com.handey.web.domain.home.Memo;
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
    public Optional<Memo> findById(Long id) {
        Memo memo = em.find(Memo.class, id);
        return Optional.ofNullable(memo);
    }
}
