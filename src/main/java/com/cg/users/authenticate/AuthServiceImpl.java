package com.cg.users.authenticate;

import com.cg.users.auth.AuthResponse;
import com.cg.users.repository.UserRepository;
import com.cg.users.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService{

    private  final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        // we need to validate email and password is correct
        //Verify whether the user is present in the database
        //Which Authentication Provider ->DaoAuthentication Provider (Inject)
        //we need to authenticate using Authentication manager injecting this Authentication provider
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        var user=userRepository.findByEmail(authRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        String jwtToken=jwtService.generateToken(user);
        return AuthResponse.builder().accessToken(jwtToken).build();
    }
}
