package com.virtusa.dao;

import java.util.List;

import com.virtusa.entity.UserModelEntity;

public interface UserModelDAO {

	List<UserModelEntity> getAllUsers1();

	UserModelEntity insertUser1(UserModelEntity user);

	UserModelEntity updateUser1(UserModelEntity entity);

	void deleteUser1(int userId);

	UserModelEntity getUserById(int userId);

}
