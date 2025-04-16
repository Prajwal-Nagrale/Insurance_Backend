package com.cg.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/management/")
public class MemberController {
    @GetMapping
    public String getMember() {
        return "Secured Endpoint :: Get - Member Controller";
    }

    @PostMapping
    public String postMember() {
        return "Secured Endpoint :: POST - Member Controller";
    }
}
