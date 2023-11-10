package dev.yeray.sp.exception;

import javax.persistence.EntityNotFoundException;

public class DataNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
        super(message);
    }
	
	public DataNotFoundException() {
        super("Item not found.");
    }
	
	public DataNotFoundException(Long id) {
        super("Item with ID " + id + " not found.");
    }
}
