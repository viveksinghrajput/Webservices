package com.accenture.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.entity.UserInfo;
import com.accenture.repository.UserDetailsDao;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDetailsDao UserDetailsDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserInfo userInfo=UserDetailsDao.getActiveUser(userName);
		
		GrantedAuthority authority=new SimpleGrantedAuthority(userInfo.getRole());
		
		User user=new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
		
		UserDetails userDetails = (UserDetails)user;
		//System.out.println(userDetails);
		
		return userDetails;
	}

}
