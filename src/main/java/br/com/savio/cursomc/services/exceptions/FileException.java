package br.com.savio.cursomc.services.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileException(String message) {
		super(message);
	}

}
