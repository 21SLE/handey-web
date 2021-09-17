package com.handey.web.controller.join;

import com.handey.web.domain.join.member;
import com.handey.web.repository.join.MemberRepository;
import com.handey.web.service.Joinservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class joinController {

    @Autowired
    MemberRepository memberRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("")
    @ResponseBody
    public String registerUser(@RequestBody member newmember){

        String username = newmember.getUsername();
        String password = Hashing.hashingpassword(newmember.getPassword());
        String email = newmember.getEmail();

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

    public static class Hashing{

        public static final String must = "~!@#$%^&*";

        public static String hashingpassword(String input){
            try{
                MessageDigest md = MessageDigest.getInstance("sHA-256");
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
