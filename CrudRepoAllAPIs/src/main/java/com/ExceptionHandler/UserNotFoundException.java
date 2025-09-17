package com.ExceptionHandler;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException{

	
	public UserNotFoundException(String msg) {
		
		super(msg);
	}
	
}
