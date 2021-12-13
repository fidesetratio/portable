package com.app.configuration.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

public class JwtAuthenticationProvider implements AuthenticationProvider {
	 public static final long TOKEN_DURATION_SECONDS = 60 * 60 * 24 * 7; // 1 week
	    public static final long TOKEN_CREATION_BUFFER_SECONDS = 60 * 5; // 5 min
	    public static final String ISSUER_ID = "FooBar";
	    protected String secret;

	    public JwtAuthenticationProvider(String tSecret) {
	        secret = tSecret;
	    }
	    
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean supports(Class<?> authentication) {
			// TODO Auto-generated method stub
			return false;
		}


}
