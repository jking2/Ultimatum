package com.joshuaking.exceptions;

public class JIllegalStateException extends Exception{

	public JIllegalStateException(){
		System.out.println("Tried to enter a state that does not exist");
	}
	public JIllegalStateException(String message){
		super(message);
	}
}
