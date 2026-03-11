package com.secuirty.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.secuirty.demo.entity.UsersInfo;
import com.secuirty.demo.repository.UserInfoRepository;

@Component
public class TestDataCreator implements CommandLineRunner {
    
    @Autowired
    private UserInfoRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createUsersIfNotExist();
    }
    
    private void createUsersIfNotExist() {
 
        if (userRepository.findByUsername("admin").isEmpty()) {
        	UsersInfo admin = new UsersInfo();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Created ADMIN user: admin/admin123");
        }
        
        System.out.println("🎉 Test data initialization COMPLETE!");
    }
}
