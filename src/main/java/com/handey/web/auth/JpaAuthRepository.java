package com.handey.web.auth;

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
    public Optional<AuthEntity> findByUserId(Long userId) {
        return Optional.ofNullable(em.createQuery("select m from AuthEntity m where m.member.id =: userId", AuthEntity.class)
                .setParameter("userId", userId)
                .getSingleResult());
    }

    @Override
    public AuthEntity save(AuthEntity authEntity) {
        em.persist(authEntity);
        return authEntity;
    }
}
