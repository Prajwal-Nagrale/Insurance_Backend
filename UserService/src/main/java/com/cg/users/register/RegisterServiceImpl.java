package com.cg.users.register;

import com.cg.users.auth.AuthResponse;
import com.cg.users.entity.User;
import com.cg.users.repository.UserRepository;
import com.cg.users.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements  RegisterService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private static final Logger logger= LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Override
    public AuthResponse register(RegisterEntity registerEntity) {

            var user=User.builder()
                    .firstName(registerEntity.getFirstName())
                    .lastName(registerEntity.getLastName())
                    .email(registerEntity.getEmail())
                    .password(passwordEncoder.encode(registerEntity.getPassword()))
                    .role(registerEntity.getRole())
                    .build();
        if (userRepository.findByEmail(user.getEmail()).isEmpty()){
            userRepository.save(user);
            String jwtToken=jwtService.generateToken(user);
            return AuthResponse.builder().accessToken(jwtToken).build();
        }
        return AuthResponse.builder().accessToken("User Already Exists").build();
    }
}
