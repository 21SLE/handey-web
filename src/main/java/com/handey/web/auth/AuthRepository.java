package com.handey.web.auth;

import java.util.Optional;


public interface AuthRepository {
    Optional<AuthEntity> findByUserId(Long userId);
    AuthEntity save(AuthEntity authEntity);
}
