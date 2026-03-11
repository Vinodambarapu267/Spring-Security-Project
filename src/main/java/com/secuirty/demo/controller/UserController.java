package com.secuirty.demo.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secuirty.demo.dto.UserDto;
import com.secuirty.demo.entity.User;
import com.secuirty.demo.service.UserService;
import com.secuirty.demo.utility.ResponseMessage;
import com.secuirty.demo.utility.ResponseStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final SecurityFilterChain filterChain;

	private UserService userService;

	public UserController(UserService userService, SecurityFilterChain filterChain) {
		this.userService = userService;
		this.filterChain = filterChain;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		User register = userService.register(user);
		if (register.getEmail().isEmpty() && register.getPassword().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_UNAUTHORIZED,
					ResponseStatus.FAILURE.name(), "Please enter the email and password"));
		}
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, ResponseStatus.SUCCESS.name(),
				"User register successfully", register));
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		User updateUser = userService.updateUser(id, userDto);
		if (updateUser.getEmail().isEmpty() && updateUser.getPassword().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_UNAUTHORIZED,
					ResponseStatus.FAILURE.name(), "Please enter the email and password"));
		}
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, ResponseStatus.SUCCESS.name(),
				"User updated successfully", updateUser));
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<User> allUsers = userService.findAll();
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, ResponseStatus.SUCCESS.name(),
				"All users retrived successfully", allUsers));
	}

	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable Long id) {
		userService.deleteById(id);
		return "deleted successfully";
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, ResponseStatus.SUCCESS.name(),
				"User found successfully", user));
	}
}
