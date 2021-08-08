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
    public String loginView() {
        return "login";
    }

    @GetMapping("joinagree")
    public String joinAgreeView() {
        return "joinagree";
    }

    @GetMapping("join")
    public String joinView() {
        return "join";
    }

    @GetMapping("home")
    public String homeView() {
        return "home";
    }

    @GetMapping("history")
    public String historyView() {
        return "history";
    }
}
