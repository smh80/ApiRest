package org.innocv.restiud.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String name) {
		super("User with name: " + name + " already exists.");

	}

}
