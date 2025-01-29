package com.cg.users.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping
    public String getAdmin() {
        return "Secured Endpoint :: Get - Admin Controller Here";
    }

    @PostMapping
    public String postAdmin() {
        return "Secured Endpoint :: POST - AdminController";
    }
}
