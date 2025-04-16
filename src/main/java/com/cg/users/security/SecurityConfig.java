package com.cg.users.security;

import com.cg.users.config.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req->
                        req.requestMatchers("/v1/user/*","/v1/auth/*")
                                .permitAll()
                                .requestMatchers("/v1/management/**").hasAnyRole("ADMIN","MEMBER")
                                .requestMatchers("/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/v1/admin/**").hasAuthority("ADMIN_READ")
                                .requestMatchers(HttpMethod.POST,"/v1/admin/**").hasAuthority("ADMIN_CREATE")
                                .requestMatchers(HttpMethod.GET,"/v1/management/**").hasAnyAuthority("ADMIN_READ","MEMBER_READ")
                                .requestMatchers(HttpMethod.POST,"/v1/management/**").hasAnyAuthority("ADMIN_CREATE","MEMBER_CREATE")
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
