package com.handey.web.service;

import com.handey.web.controller.join.joinController;
import com.handey.web.domain.join.member;
import com.handey.web.repository.join.MemberRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Transactional

public class Joinservice {

    private final MemberRepository memberRepository;

    public Joinservice(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(member newMember) {
        //같은 email 중복회원은 안된다.

        String username = newMember.getUsername();
        String password = joinController.Hashing.hashingPassword(newMember.getPassword());
        String email = newMember.getEmail();

        if(username.equals("")||password.equals("")||email.equals(""))
            return "fail";

        member member = new member();
        member.setUsername(username);
        member.setPassword(password);
        member.setEmail(email);

        if (memberRepository.findByUsername(username) != null)
            return "fail";

        memberRepository.save(member);
        return "success";

    }

    /**
     * 전체 회원 조회
     */
    public List<member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<member> findOne(String memberUsername){
        return memberRepository.findByUsername(memberUsername);
    }
 }

