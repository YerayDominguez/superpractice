package dev.yeray.sp.exception;

public class ClientNotFoundException extends RuntimeException {
	public ClientNotFoundException(String message) {
		super(message);
	}
}
