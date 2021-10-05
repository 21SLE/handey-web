package com.handey.web.member;

import com.handey.web.auth.AuthEntity;
import com.handey.web.auth.AuthRepository;
import com.handey.web.common.security.TokenResponse;
import com.handey.web.common.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenUtils tokenUtils;
    private final AuthRepository authRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, TokenUtils tokenUtils, AuthRepository authRepository) {
        this.memberRepository = memberRepository;
        this.tokenUtils = tokenUtils;
        this.authRepository = authRepository;
    }

    @Transactional
    public TokenResponse join(Member newMember) {
        String username = newMember.getUsername();
        String password = MemberController.Hashing.hashingPassword(newMember.getPassword());
        String email = newMember.getEmail();

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setEmail(email);

        Member savedMember = memberRepository.save(member);

        String accessToken = tokenUtils.generateJwtToken(savedMember);
        System.out.println("memberService - accessToken: "+accessToken);
        String refreshToken = tokenUtils.saveRefreshToken(savedMember);
        System.out.println("memberService - refreshToken: "+refreshToken);

        authRepository.save(AuthEntity.builder().member(member).refreshToken(refreshToken).build());

        return TokenResponse.builder().userId(member.getId()).accessToken(accessToken).refreshToken(refreshToken).isSucceed(true).build();
    }

    @Transactional
    public TokenResponse signIn(Member member) {
        Member findMem = memberRepository
                        .findByUserIdAndPw(member.getEmail(), member.getPassword())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        AuthEntity authEntity =
                authRepository
                        .findByMember(findMem)
                        .orElseThrow(() -> new IllegalArgumentException("Token 이 존재하지 않습니다."));
        String accessToken = "";
        String refreshToken= authEntity.getRefreshToken();

        if (tokenUtils.isValidRefreshToken(refreshToken)) {
            accessToken = tokenUtils.generateJwtToken(authEntity.getMember());
            return TokenResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(authEntity.getRefreshToken())
                    .build();
        } else {
            refreshToken = tokenUtils.saveRefreshToken(findMem);
            authEntity.refreshUpdate(refreshToken);
        }

        return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findByUserId(Long userId){
        return memberRepository.findById(userId);
    }
 }

