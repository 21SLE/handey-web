package com.handey.web.member;

import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import com.handey.web.common.security.TokenResponse;
import com.handey.web.memo.MemoService;
import com.handey.web.userinfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final UserInfoService userInfoService;
    private final MemoService memoService;
    private final ResponseService responseService;

    @Autowired
    public MemberController(MemberService memberService, UserInfoService userInfoService, MemoService memoService, ResponseService responseService) {
        this.memberService = memberService;
        this.userInfoService = userInfoService;
        this.memoService = memoService;
        this.responseService = responseService;
    }

    @PostMapping("/register")
    @Transactional
    public SingleResponse<TokenResponse> registerUser(@RequestBody MemberParam newMember){
        String username = newMember.getUsername();
        String password = MemberController.Hashing.hashingPassword(newMember.getPassword());
        String email = newMember.getEmail();

        if(username.equals("")||password.equals("")||email.equals("")) {
            return responseService.returnSingleResponse(TokenResponse.builder().isSucceed(false).build());
        }

        TokenResponse tokenResponse = memberService.join(newMember);
        Member findMem = memberService.findByUserId(tokenResponse.getUserId()).orElseThrow(MemberNoDataFoundException::new);
        userInfoService.createDefaultUserInfo(findMem);
        memoService.createMemo(findMem);

        return responseService.returnSingleResponse(tokenResponse);
    }

    @PostMapping("/login")
    public SingleResponse<TokenResponse> login(@RequestBody MemberParam member) {
        String password = MemberController.Hashing.hashingPassword(member.getPassword());
        member.setPassword(password);
        return responseService.returnSingleResponse(memberService.signIn(member));
    }

    @GetMapping("/register/duplication")
    public SingleResponse<Boolean> checkEmailDuplication(@RequestParam String email) {
        return responseService.returnSingleResponse(memberService.findByUserEmail(email).isPresent());
    }


    @Transactional
    @DeleteMapping("/user/withdrawal")
    public Response userWithdrawal(@RequestBody MemberParam member) {
        memberService.deleteByUserEmailAndPassword(member.getEmail(), MemberController.Hashing.hashingPassword(member.getPassword()));
        return responseService.returnSuccessResponse();
    }

    @Transactional
    @PutMapping("/user/{userId}/username")
    public Response changeUserName(@PathVariable Long userId, @RequestBody MemberParam param) {
        memberService.changeUserName(userId, param);
        return responseService.returnSuccessResponse();
    }

    @Transactional
    @PutMapping("/user/{userId}/password")
    public Response changePassword(@PathVariable Long userId, @RequestBody MemberParam param) {
        String newPw = MemberController.Hashing.hashingPassword(param.getPassword());
        param.setPassword(newPw);
        memberService.changePassword(userId, param);
        return responseService.returnSuccessResponse();
    }


    public static class Hashing {
        public static final String SALT = "!@salt$%^&";

        public static String hashingPassword(String input){
            try{
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashData = md.digest(input.getBytes(StandardCharsets.UTF_8));
                BigInteger number = new BigInteger(1, hashData);
                StringBuilder hexString = new StringBuilder(number.toString(16));//글자수

                while(hexString.length() < 32){
                    hexString.insert(0, '0');
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e){
                return input;
            }
        }
    }
}
