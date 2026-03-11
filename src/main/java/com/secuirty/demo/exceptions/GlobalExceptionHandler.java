package com.secuirty.demo.exceptions;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNorFoundException.class)
	public ResponseEntity<?> handleUserNot(UserNorFoundException exception, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpURLConnection.HTTP_NOT_FOUND,
				exception.getLocalizedMessage(), request.getDescription(false));
		return ResponseEntity.ok(errorMessage);
	}
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<?> handleUserAlready(UserAlreadyExistException exception, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpURLConnection.HTTP_UNAVAILABLE,
				exception.getLocalizedMessage(), request.getDescription(false));
		return ResponseEntity.ok(errorMessage);
	}
}
