package com.handey.web.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtTokenInterceptor implements HandlerInterceptor { // jwt 토큰 생성, 검증
    private final TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("JwtToken 호출");
        String accessToken = request.getHeader("ACCESS_TOKEN");
        System.out.println("AccessToken:" + accessToken);
        String refreshToken = request.getHeader("REFRESH_TOKEN");
        System.out.println("RefreshToken:" + refreshToken);

        if (accessToken != null) {
            if (tokenUtils.isValidToken(accessToken)) {
                return true;
            }
        }

        response.setStatus(401);
        response.setHeader("ACCESS_TOKEN", accessToken);
        response.setHeader("REFRESH_TOKEN", refreshToken);
        response.setHeader("msg", "Check the tokens.");
        return false;
    }


}
