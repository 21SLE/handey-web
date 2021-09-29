package com.handey.web.controller.join;

import com.handey.web.domain.join.member;
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

    private final Joinservice joinservice;

    @Autowired
    public joinController(Joinservice joinservice) {
        this.joinservice = joinservice;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register/{Id}")
    @ResponseBody

    public String registerUser(@PathVariable Long Id, @RequestBody member newMember){

        return joinservice.join(newMember);
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
