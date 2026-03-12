package com.secuirty.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secuirty.demo.repository.UserInfoRepository;

@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserInfoRepository userRepository;
@Cacheable(value = "userdetails",key="#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}
