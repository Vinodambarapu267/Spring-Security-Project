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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}
