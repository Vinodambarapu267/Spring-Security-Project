package com.secuirty.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secuirty.demo.entity.User;
import com.secuirty.demo.entity.UsersInfo;
import com.secuirty.demo.repository.UserInfoRepository;
import com.secuirty.demo.repository.UserRepository;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
  UsersInfo userInfo = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .authorities(userInfo.getRole()) 
            .build();
    }
}
