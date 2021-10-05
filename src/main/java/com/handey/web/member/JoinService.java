package com.handey.web.member;

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

    public Member join(Member newMember) {
        //같은 email 중복회원은 안된다.
        String username = newMember.getUsername();
        String password = JoinController.Hashing.hashingPassword(newMember.getPassword());
        String email = newMember.getEmail();

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setEmail(email);

//        if (memberRepository.findByUsername(username) != null)
//            return "fail";

        return memberRepository.save(member);
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

