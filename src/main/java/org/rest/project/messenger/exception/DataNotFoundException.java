package org.rest.project.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6538772704287313784L;
	
	public DataNotFoundException (String message) {
		super(message);
	}

}
