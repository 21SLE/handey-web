package com.handey.web.repository.join;


import com.handey.web.domain.join.member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<member,Long> {

    public member findByUsername(String username);
    public member findByUsernameAndPassword(String username, String password);
}
