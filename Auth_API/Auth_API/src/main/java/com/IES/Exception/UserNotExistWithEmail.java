package com.IES.Exception;

public class UserNotExistWithEmail extends RuntimeException {

	public UserNotExistWithEmail(String msg) {
		super(msg);
	}
}
