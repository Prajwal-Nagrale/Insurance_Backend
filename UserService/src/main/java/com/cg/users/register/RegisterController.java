package com.cg.users.register;

import com.cg.users.auth.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity register(@RequestBody RegisterEntity registerEntity){
        AuthResponse authResponse=registerService.register(registerEntity);
        if(authResponse.accessToken.equals("User Already Exists")) return new ResponseEntity<>("User Already Exists",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(authResponse);
    }

}
