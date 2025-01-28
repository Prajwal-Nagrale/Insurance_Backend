package com.cg.users.register;

import com.cg.users.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterEntity registerEntity){
        AuthResponse authResponse=registerService.register(registerEntity);
        return ResponseEntity.ok(authResponse);
    }

}
