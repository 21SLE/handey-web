package com.handey.web.common.security;

import com.handey.web.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class TokenResponse {
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private boolean isSucceed;

    @Builder
    public TokenResponse(Long userId, String accessToken, String refreshToken, boolean isSucceed) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isSucceed =isSucceed;
    }
}
