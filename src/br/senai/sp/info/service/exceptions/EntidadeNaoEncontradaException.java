package br.senai.sp.info.service.exceptions;


public class EntidadeNaoEncontradaException extends Exception{

	/**
	 * Seria UID
	 */
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException() {
		super();
	}

	public EntidadeNaoEncontradaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntidadeNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

	public EntidadeNaoEncontradaException(Throwable cause) {
		super(cause);
	}
	
	

}
