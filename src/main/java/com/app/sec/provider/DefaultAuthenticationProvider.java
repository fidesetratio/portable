package com.app.sec.provider;

import java.util.Collections;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class DefaultAuthenticationProvider implements AuthenticationProvider{

	private static final Logger logger = LogManager.getLogger(DefaultAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		  if (authentication.getName() == null || authentication.getCredentials() == null) {
	            return null;
	        }
		  
		    final String userEmail = authentication.getName();
	        final Object userPassword = authentication.getCredentials();
	        logger.info("userEmail "+userEmail);
	        
	        if (userEmail == null || userPassword == null) {
	            return null;
	        }

	        if (userEmail.isEmpty() || userPassword.toString().isEmpty()) {
	            return null;
	        }
	        String validUserEmail = "demo";
	        String validUserPassword = "demo";
	        if (userEmail.equalsIgnoreCase(validUserEmail)
	                && userPassword.equals(validUserPassword)) {
	            return new UsernamePasswordAuthenticationToken(
	                    userEmail, userPassword, getAuthority());
	        }
	        throw new UsernameNotFoundException("Invalid username or password");

	        
	}


	 @Override
	    public boolean supports(final Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    }

	    private List<SimpleGrantedAuthority> getAuthority() {
	    	  return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	    	  
	    }
}
