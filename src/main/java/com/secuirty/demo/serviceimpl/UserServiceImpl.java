package com.secuirty.demo.serviceimpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.secuirty.demo.dto.UserDto;
import com.secuirty.demo.entity.User;
import com.secuirty.demo.exceptions.UserAlreadyExistException;
import com.secuirty.demo.exceptions.UserNorFoundException;
import com.secuirty.demo.repository.UserRepository;
import com.secuirty.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('WRITE')")
	public User register(User user) {
		Optional<User> existedUser = userRepository.findByEmail(user.getEmail());
		if (existedUser.isPresent()) {
			throw new UserAlreadyExistException("User Already exist");
		}
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setRole(user.getRole());
		newUser.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		User save = userRepository.save(newUser);
		return save;
	}

	@Override
	@PreAuthorize("hasAuthority('WRITE')")
	public User updateUser(Long id, UserDto dto) {
		User existedUSer = userRepository.findById(id).orElseThrow(() -> new UserNorFoundException("User not Found"));
		existedUSer.setUsername(dto.getUsername());
		existedUSer.setEmail(dto.getEmail());
		existedUSer.setPassword(Base64.getEncoder().encodeToString(dto.getPassword().getBytes()));
		existedUSer.setRole(dto.getRole());
		User update = userRepository.save(existedUSer);

		return update;
	}

	@Override
	@PreAuthorize("hasAuthority('DELETE')")
	public void deleteById(Long id) {
		User existedUSer = userRepository.findById(id).orElseThrow(() -> new UserNorFoundException("User not Found"));
		userRepository.existsById(existedUSer.getId());
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public List<User> findAll() {
		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty()) {
			throw new UserNorFoundException("User list is empty");
		}
		return allUsers;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public User getById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNorFoundException("User not Found"));
		return user;
	}

}
