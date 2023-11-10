package dev.yeray.sp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> validationExceptionHandling(MethodArgumentNotValidException exception,
			WebRequest request) {
		StringBuilder errors = new StringBuilder("Validation Errors: ");

		exception.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			errors.append(errorMessage).append(". ");
		});

		return new ResponseEntity<>(
				new ErrorDetails(new Date(), errors.toString().trim(), request.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> dataNotFoundExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandling(Exception exception, WebRequest request) {
		return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
