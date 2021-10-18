package com.handey.web.userinfo;

import com.handey.web.auth.AuthEntity;
import com.handey.web.member.Member;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserInfoRepository implements UserInfoRepository{
    private final EntityManager em;

    public JpaUserInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        em.persist(userInfo);
        return userInfo;
    }

    @Override
    public Optional<UserInfo> findByUserId(Long userId) {
        return Optional.ofNullable(em.createQuery("select m from UserInfo m where m.member.id =: userId", UserInfo.class)
                .setParameter("userId", userId)
                .getSingleResult());
    }

    @Override
    public List<Member> findMembersByResetTime(String resetTime) {
        return em.createQuery("select m.member from UserInfo m where m.resetTime = :resetTime", Member.class)
                .setParameter("resetTime", resetTime)
                .getResultList();
    }

    @Override
    public List<UserInfo> findAll() {
        return em.createQuery("select m from UserInfo m", UserInfo.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        UserInfo userInfo = em.find(UserInfo.class, id);
        Assert.notNull(userInfo,"User Info must not be null!");
        em.remove(userInfo);
    }

    @Override
    public void deleteByUserId(Long userId) {
        UserInfo userInfo = em.find(UserInfo.class, userId);
        Assert.notNull(userInfo,"User Info must not be null!");
        em.remove(userInfo);
    }
}
