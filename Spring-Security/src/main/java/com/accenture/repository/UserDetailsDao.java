package com.accenture.repository;

import com.accenture.entity.UserInfo;

public interface UserDetailsDao {

	UserInfo getActiveUser(String userName);

}
