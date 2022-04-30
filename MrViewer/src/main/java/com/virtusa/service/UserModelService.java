package com.virtusa.service;

import java.util.List;

import com.virtusa.dto.UserDTO;

public interface UserModelService {

	List<UserDTO> getAllUsers();

	UserDTO insertUser(UserDTO user);

	UserDTO updateUser(UserDTO usermodelDto);

	void deleteUser(int userId);

	UserDTO getUserById(int userId);

}
