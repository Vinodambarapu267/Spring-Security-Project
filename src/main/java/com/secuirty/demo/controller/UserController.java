package com.secuirty.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		User register = userService.register(user);
		if (register.getEmail().isEmpty() && register.getPassword().isEmpty()) {
			return ResponseEntity.ok("Please enter the email and password");
		}
		return ResponseEntity.ok(register);
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		User updateUser = userService.updateUser(id, userDto);
		if (updateUser.getEmail().isEmpty() && updateUser.getPassword().isEmpty()) {
			return ResponseEntity.ok("Please enter the email and password");
		}
		return ResponseEntity.ok(updateUser);
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<User> allUsers = userService.findAll();
		return ResponseEntity.ok(allUsers);
	}

	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable Long id) {
		userService.deleteById(id);
		return "deleted successfully";
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
}
