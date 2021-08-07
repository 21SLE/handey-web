package com.handey.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("")
    public String indexView() {
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("joinagree")
    public String joinAgree() {
        return "joinagree";
    }

    @GetMapping("join")
    public String join() {
        return "join";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }
}
