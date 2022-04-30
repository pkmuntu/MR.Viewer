package com.muntu.service;

import java.util.List;

import com.muntu.dto.UserRequestDTO;

public interface UserRequestService {

	List<UserRequestDTO> getAllUsersRequest();

	UserRequestDTO insertUserRequest(UserRequestDTO user);

	void deleteUser(int requestId);

}
