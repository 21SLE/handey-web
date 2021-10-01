package com.handey.web.service;

import com.handey.web.common.exception.UserInfoNoDataFoundException;
import com.handey.web.controller.settings.UserInfoParam;
import com.handey.web.domain.join.Member;
import com.handey.web.domain.settings.UserInfo;
import com.handey.web.repository.settings.UserInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public Long createDefaultUserInfo(Member member) {
        UserInfo userInfo = new UserInfo();
        userInfo.setMember(member);
        userInfo.setResetTime("0");
        userInfo.setTheme("1");

        return userInfoRepository.save(userInfo).getId();
    }

    public Long updateUserInfo(Long userId, UserInfoParam param) {
        UserInfo userInfo = userInfoRepository.findByUserId(userId).orElseThrow(UserInfoNoDataFoundException::new);
        userInfo.update(param);
        return userInfoRepository.save(userInfo).getId();
    }

    public Optional<UserInfo> getUserInfoByUserId(Long userId) {
        return userInfoRepository.findByUserId(userId);
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoRepository.findAll();
    }

    public List<Member> getUserListByResetTime(String resetTime) {
        return userInfoRepository.findMembersByResetTime(resetTime);
    }

    public void deleteUserInfoByUserInfoId(Long id) {
        userInfoRepository.deleteById(id);
    }

    public void deleteUserInfoByUserId(Long userId) {
        userInfoRepository.deleteByUserId(userId);
    }
}
