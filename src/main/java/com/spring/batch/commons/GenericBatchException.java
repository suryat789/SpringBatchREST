package com.spring.batch.commons;

public class GenericBatchException extends Exception {

	private static final long serialVersionUID = -5635081090907088058L;

	public GenericBatchException() {
		super();
	}

	public GenericBatchException(String message) {
		super(message);
	}
	
	public static void main(String[] args) {
		if(null instanceof String)
			System.out.println("1");
		else 
			System.out.println("2");
	}
}


