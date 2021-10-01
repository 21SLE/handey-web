package com.handey.web.repository.settings;

import com.handey.web.domain.join.Member;
import com.handey.web.domain.settings.UserInfo;
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
        UserInfo userInfo = em.find(UserInfo.class, userId);
        return Optional.ofNullable(userInfo);
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
