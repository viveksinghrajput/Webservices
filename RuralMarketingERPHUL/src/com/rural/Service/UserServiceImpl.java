package com.rural.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rural.DAO.UserDAO;
import com.rural.Model.SecurityAnswers;
import com.rural.Model.SecurityQuestions;
import com.rural.Model.UserMaster;

@Component
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public UserMaster validateUser(String username, String password) {

		UserMaster user = userDAO.validateUser(username, password);

		return user;
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException
	{
	    //Always use a SecureRandom generator
	    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	    //Create array for salt
	    byte[] salt = new byte[16];
	    //Get a random salt
	    sr.nextBytes(salt);
	    //return salt
	    return salt;
	}
	
	private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

	@Override
	public String saveAnswers(SecurityAnswers sAnswers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countLogin(String username) {
		// TODO Auto-generated method stub
		return userDAO.countLogin(username);
	}

	@Override
	public String resethitcount(String username) {
		// TODO Auto-generated method stub
		return userDAO.resethitcount(username);
	}

	@Override
	public List<SecurityQuestions> securityQnsList() {
		// TODO Auto-generated method stub
		return userDAO.getAllitemDesc();
	}

}
