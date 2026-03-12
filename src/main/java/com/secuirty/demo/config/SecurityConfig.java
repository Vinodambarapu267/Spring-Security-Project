package com.secuirty.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.secuirty.demo.filters.JwtAuthFilter;
import com.secuirty.demo.security.CustomUserDetailService;
import com.secuirty.demo.utility.JWTUtil;
import com.secuirty.demo.utility.Permission;

@Configuration@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
               .requestMatchers("/authenticate").permitAll()
                //.requestMatchers(HttpMethod.POST,"/api/users/**").hasAuthority(Permission.WRITE.name())
               // .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasAuthority(Permission.DELETE.name())
               // .requestMatchers(HttpMethod.GET,"/api/users").hasAuthority(Permission.READ.name())
               // .requestMatchers(HttpMethod.GET,"/api/users/**").hasAuthority(Permission.READ.name())
               .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JWTUtil jwtUtil,CustomUserDetailService customUserDetailService ) {
        return new JwtAuthFilter(jwtUtil, customUserDetailService);
    }

    // ✅ FIXED: Add PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
