package com.app.sec.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.app.model.Group;
import com.app.model.Role;
import com.app.model.User;
import com.app.services.DashboardServices;

public class MysqlAuthenticationProvider implements AuthenticationProvider{

	private static final Logger logger = LogManager.getLogger(MysqlAuthenticationProvider.class);
	
	private DashboardServices dashboardServices;
	
	
	public MysqlAuthenticationProvider(DashboardServices dashboardServices) {
		super();
		this.dashboardServices = dashboardServices;
	}

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
	        
	        
	        
	        String username = userEmail;
	        String password = (String) userPassword;
	
	        if(username != null && password != null) {
	        	
	        	if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
	        		User user = dashboardServices.selectUsernameByPassword(username, password);
	        		logger.info("user:"+user);
	        		if(user != null) {
	        		List<SimpleGrantedAuthority> role = getAuthority(user);
	        		if(user != null && role.size()>0) {
	        			UsernamePasswordAuthenticationToken auth =  new UsernamePasswordAuthenticationToken(
	        					username, password, role);
	        			auth.setDetails(user);
	        			UsernamePasswordAuthenticationToken authenticated =  new UsernamePasswordAuthenticationToken(
	        					auth, password, role);
	    	        	return authenticated;
	        		}
	        		};
	        	}
	        };
	        throw new UsernameNotFoundException("Invalid username or password");

	        
	}


	 	@Override
	    public boolean supports(final Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    }

	    private List<SimpleGrantedAuthority> getAuthority(User u) {
	    		
	    	List<Group> groups = u.getGroups() == null ? new ArrayList<Group>():u.getGroups();
	    	List<SimpleGrantedAuthority> grantedRoles = new ArrayList<SimpleGrantedAuthority>();
	    	if(groups.size()>0) {
	    		List<String> groupId = new ArrayList<String>();
	    		for(Group g:groups) {
	    			groupId.add(Integer.toString(g.getGroupId()));
	    		}
	    		if(groupId.size()>0) {
	    			List<Role> roles = dashboardServices.selectRolesFromGroups(groupId);
	    			logger.info("size of roles:"+roles.size()+ ": "+roles);
	    			for(Role r:roles) {
	    				grantedRoles.add(new SimpleGrantedAuthority(r.getRole_name().toUpperCase()));	
	    			}
	    		}
	    	}
	    	
	    	return grantedRoles;
	    	  
	    }
}
