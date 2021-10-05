package com.handey.web.auth;

import com.handey.web.member.Member;
import com.handey.web.memo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AuthRepository {
    Optional<AuthEntity> findByMember(Member member);
    AuthEntity save(AuthEntity authEntity);
}
