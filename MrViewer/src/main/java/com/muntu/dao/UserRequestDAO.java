package com.muntu.dao;

import java.util.List;

import com.muntu.entity.UserRequestEntity;

public interface UserRequestDAO {

	List<UserRequestEntity> getAllUserRequest1();

	void addRequest1(UserRequestEntity userRequest, int userId);

	void deleteUser1(int requestId);

}
