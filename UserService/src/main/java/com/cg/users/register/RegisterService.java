package com.cg.users.register;

import com.cg.users.auth.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    public AuthResponse register(RegisterEntity registerEntity);
}
