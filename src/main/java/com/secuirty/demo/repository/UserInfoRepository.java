package com.secuirty.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secuirty.demo.entity.UsersInfo;

public interface UserInfoRepository extends JpaRepository<UsersInfo, Long> {
	Optional<UsersInfo> findByUsername(String userName);
}