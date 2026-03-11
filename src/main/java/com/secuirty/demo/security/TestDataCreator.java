package com.secuirty.demo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.secuirty.demo.entity.UsersInfo;
import com.secuirty.demo.repository.UserInfoRepository;
import com.secuirty.demo.utility.Role;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestDataCreator{

    @Bean
    public CommandLineRunner createAdminUser(UserInfoRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                UsersInfo admin = new UsersInfo();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234")); 
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                log.info("Default admin user created!");
            }
            if (userRepository.findByUsername("user").isEmpty()) {
            	UsersInfo admin = new UsersInfo();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user1234"));
                admin.setRole(Role.USER);

                userRepository.save(admin);
                log.info("Default admin user created!");
            }
        };
    }
}