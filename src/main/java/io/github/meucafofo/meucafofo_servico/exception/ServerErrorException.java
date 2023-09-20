package io.github.meucafofo.meucafofo_servico.exception;

public class ServerErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6241674797102249931L;
	
	public ServerErrorException(String msg) {
		super(msg);
	}
	
	public ServerErrorException(String msg, Throwable error) {
		super(msg, error);
	}
}
