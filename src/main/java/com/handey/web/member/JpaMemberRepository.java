package com.handey.web.member;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserIdAndPw(String email, String password) {
        return Optional.ofNullable(em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class)
                .setParameter("email", email).setParameter("password", password).getSingleResult());
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        Member member = em.find(Member.class, email);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void deleteByUserEmailAndPw(String email, String password) {
        Member member = em.createQuery("select m from Member m where m.email = :email and m.password = :password", Member.class)
                .setParameter("email", email).setParameter("password", password).getSingleResult();
        Assert.notNull(member,"Member must not be null!");
        em.remove(member);
    }

}
