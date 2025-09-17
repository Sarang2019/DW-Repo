package com.IES.Security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerUserService extends UserDetails {

	String getEmail();
}
