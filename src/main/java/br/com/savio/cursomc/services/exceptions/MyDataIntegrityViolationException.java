package br.com.savio.cursomc.services.exceptions;

public class MyDataIntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyDataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyDataIntegrityViolationException(String message) {
		super(message);
	}

}
