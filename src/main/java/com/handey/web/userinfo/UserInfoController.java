package com.handey.web.userinfo;

import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final ResponseService responseService;

    public UserInfoController(UserInfoService userInfoService, ResponseService responseService) {
        this.userInfoService = userInfoService;
        this.responseService = responseService;
    }

    /**
     * user info 전체 조회
     */
    @GetMapping("/userinfo")
    public ListResponse<UserInfo> getUserInfoList() {
        return responseService.returnListResponse(userInfoService.getUserInfoList());
    }

    /**
     * user info 단건 조회 by userId
     */
    @GetMapping("/user/{userId}/info")
    public SingleResponse<Optional<UserInfo>> getUserInfoByUserId(@PathVariable Long userId) {
        return responseService.returnSingleResponse(userInfoService.getUserInfoByUserId(userId));
    }

    /**
     * user info 수정
     */
    @PutMapping("/user/{userId}/info")
    public Response updateUserInfo(@PathVariable Long userId, @RequestBody UserInfoParam param) {
        userInfoService.updateUserInfo(userId, param);
        return responseService.returnSuccessResponse();
    }
}
