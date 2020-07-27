package com.rural.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rural.Model.SecurityQuestions;
import com.rural.Model.UserMaster;

@Component
public interface UserDAO {

	public UserMaster validateUser(String username,String password);
	public List<SecurityQuestions> getAllitemDesc();
	public String resethitcount(String username);
	public int countLogin(String username);
}
