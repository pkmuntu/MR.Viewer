package com.muntu.dao;

import java.util.List;

import com.muntu.entity.UserModelEntity;

public interface UserModelDAO {

	List<UserModelEntity> getAllUsers1();

	UserModelEntity insertUser1(UserModelEntity user);

	UserModelEntity updateUser1(UserModelEntity entity);

	void deleteUser1(int userId);

	UserModelEntity getUserById(int userId);

}
