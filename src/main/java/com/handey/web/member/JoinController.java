package com.handey.web.member;

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
public class JoinController {

    private final JoinService joinService;
    private final UserInfoService userInfoService;
    private final MemoService memoService;

    @Autowired
    public JoinController(JoinService joinservice, UserInfoService userInfoService, MemoService memoService) {
        this.joinService = joinservice;
        this.userInfoService = userInfoService;
        this.memoService = memoService;
    }

    @PostMapping("/register")
    @Transactional
    public String registerUser(@RequestBody Member newMember){
        String username = newMember.getUsername();
        String password = JoinController.Hashing.hashingPassword(newMember.getPassword());
        String email = newMember.getEmail();

        if(username.equals("")||password.equals("")||email.equals(""))
            return "fail";

        Member savedMember = joinService.join(newMember);
        userInfoService.createDefaultUserInfo(savedMember);
        memoService.createMemo(savedMember);

        return "success";
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
