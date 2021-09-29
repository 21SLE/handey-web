package com.handey.web.repository.join;


import com.handey.web.domain.join.member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository{

    member save(member member);//회원이 저장소에 저장됨
    Optional <member> findByUsername(String username);
    Optional <member> findByUsernameAndPassword(String username, String password);
    List<member> findAll();//저장된 모든 회원 list 반환
}
