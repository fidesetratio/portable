package com.app.sec.provider.customer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import com.app.model.User;
import com.app.services.DashboardServices;

@Service("userDetailsService")
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private DashboardServices dashboardServices;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = dashboardServices.selectUsernameByUsername(username);
		if(user !=  null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<GrantedAuthority>());
		}else {
            throw new UsernameNotFoundException("User not exist with name :" +username);
		}
	}

}
