package com.cg.users.authenticate;

import com.cg.users.auth.AuthResponse;

public interface AuthService {

    public AuthResponse authenticate(AuthRequest authRequest);
}
