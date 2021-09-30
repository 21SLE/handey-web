package com.handey.web.controller.settings;

import com.handey.web.domain.settings.UserInfo;
import com.handey.web.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * user info 전체 조회
     */
    @GetMapping("/userinfo")
    public List<UserInfo> getUserInfoList() {
        return userInfoService.getUserInfoList();
    }

    /**
     * user info 단건 조회 by userId
     */
    @GetMapping("/user/{userId}/info")
    public Optional<UserInfo> getUserInfoByUserId(@PathVariable Long userId) {
        return userInfoService.getUserInfoByUserId(userId);
    }

    /**
     * user info 수정
     */
    @PutMapping("/user/{userId}/info")
    public boolean updateUserInfo(@PathVariable Long userId, @RequestBody UserInfoParam param) {
        userInfoService.updateUserInfo(userId, param);
        return true;
    }

    // user 탈퇴시 삭제되는걸로...
//    /**
//     * user info 삭제 by userId
//     */
//    @DeleteMapping("/user/{userId}/info")
//    public boolean deleteUserInfoByUserId(@PathVariable Long userId) {
//        userInfoService.deleteUserInfoByUserId(userId);
//        return true;
//    }
}
