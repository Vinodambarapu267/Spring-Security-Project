package com.secuirty.demo.service;

import java.util.List;

import com.secuirty.demo.dto.UserDto;
import com.secuirty.demo.entity.User;

public interface UserService {
	public User register(User user);

	public User updateUser(Long id, UserDto dto);

	public void deleteById(Long id);

	public List<User> findAll();

	public User getById(Long id);
}
