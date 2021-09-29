package com.handey.web.service;

import com.handey.web.controller.join.JoinController;
import com.handey.web.domain.join.Member;
import com.handey.web.repository.join.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class JoinService {

    private final MemberRepository memberRepository;

    public JoinService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(Member newMember) {
        //같은 email 중복회원은 안된다.

        String username = newMember.getUsername();
        String password = JoinController.Hashing.hashingPassword(newMember.getPassword());
        String email = newMember.getEmail();

        if(username.equals("")||password.equals("")||email.equals(""))
            return "fail";

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setEmail(email);

//        if (memberRepository.findByUsername(username) != null)
//            return "fail";

        memberRepository.save(member);
        return "success";
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberUsername){
        return memberRepository.findByUsername(memberUsername);
    }
 }

