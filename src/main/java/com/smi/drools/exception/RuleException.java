package com.smi.drools.exception;

public class RuleException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -5273112492094429138L;

	public RuleException(String exceptionMessage) {
	        super(exceptionMessage);
	    }

	    public RuleException(final String msg, final Throwable cause) {
	        super(msg, cause);
	    }
}
