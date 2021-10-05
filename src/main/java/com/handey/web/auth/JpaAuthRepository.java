package com.handey.web.auth;

import com.handey.web.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaAuthRepository implements AuthRepository{
     private final EntityManager em;

    public JpaAuthRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<AuthEntity> findByMember(Member member) {
        AuthEntity AuthEntity = em.find(AuthEntity.class, member);
        return Optional.ofNullable(AuthEntity);
    }

    @Override
    public AuthEntity save(AuthEntity authEntity) {
        em.persist(authEntity);
        return authEntity;
    }
}
