package com.rural.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rural.Model.SecurityAnswers;
import com.rural.Model.SecurityQuestions;
import com.rural.Model.UserMaster;

@Component
public interface UserService {

	public UserMaster validateUser(String username,String password);
	public String saveAnswers(SecurityAnswers sAnswers);
	public int countLogin(String username);
	public String resethitcount(String username);
	public List<SecurityQuestions> securityQnsList();
	
}
