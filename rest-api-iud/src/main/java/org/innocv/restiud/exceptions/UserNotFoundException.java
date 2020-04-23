package org.innocv.restiud.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Integer id, HttpStatus status) {
		super("User with id: " + id + " not found.");

	}

}
