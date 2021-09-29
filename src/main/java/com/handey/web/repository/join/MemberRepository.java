package com.handey.web.repository.join;


import com.handey.web.domain.join.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository{

    Member save(Member member);//회원이 저장소에 저장됨
    Optional<Member> findById(String id);
    Optional <Member> findByUsername(String username);
    Optional <Member> findByUsernameAndPassword(String username, String password);
    List<Member> findAll();//저장된 모든 회원 list 반환
}
