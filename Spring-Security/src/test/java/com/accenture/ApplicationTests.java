package com.accenture;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationTests{
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("v@123"));
		System.out.println(bCryptPasswordEncoder.encode("p@123"));
	}
}
 