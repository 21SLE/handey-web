package com.handey.web.userinfo;

import com.handey.web.member.Member;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    UserInfo save(UserInfo userInfo);
    Optional<UserInfo> findByUserId(Long userId);
    List<Member> findMembersByResetTime(String resetTime);
    List<UserInfo> findAll();
    void deleteById(Long id);
    void deleteByUserId(Long userId);
}
