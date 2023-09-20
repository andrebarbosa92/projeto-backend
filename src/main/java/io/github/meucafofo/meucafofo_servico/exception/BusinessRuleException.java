package io.github.meucafofo.meucafofo_servico.exception;

public class BusinessRuleException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6723629157902305640L;

	public BusinessRuleException(String msg) {
		super(msg);
	}

	public BusinessRuleException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
