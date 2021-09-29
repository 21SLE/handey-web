package com.handey.web.controller.join;

import com.handey.web.domain.join.Member;
import com.handey.web.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinservice) {
        this.joinService = joinservice;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Member newMember){
        return joinService.join(newMember);
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
