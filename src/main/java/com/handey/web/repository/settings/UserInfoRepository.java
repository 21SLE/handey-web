package com.handey.web.repository.settings;

import com.handey.web.domain.settings.UserInfo;
import com.handey.web.domain.trash.TrashBox;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    UserInfo save(UserInfo userInfo);
    Optional<UserInfo> findByUserId(Long userId);
    List<UserInfo> findAll();
    void deleteById(Long id);
    void deleteByUserId(Long userId);
}
