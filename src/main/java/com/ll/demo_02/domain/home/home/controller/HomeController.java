package com.ll.demo_02.domain.home.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Value("${custom.site.name}")
    private String siteName;
    @Value("${custom.secret.key}")
    private String secretKey;

    @GetMapping("/")
    @ResponseBody
    public String showMain() {
        return "2hnK's" + siteName;
    }

    @GetMapping("/secret")
    @ResponseBody
    public String showSecretKey() {
        return "secretKey: " + secretKey;
    }
}