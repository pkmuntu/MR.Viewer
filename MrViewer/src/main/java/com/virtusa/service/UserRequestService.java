package com.virtusa.service;

import java.util.List;

import com.virtusa.dto.UserRequestDTO;

public interface UserRequestService {

	List<UserRequestDTO> getAllUsersRequest();

	UserRequestDTO insertUserRequest(UserRequestDTO user);

	void deleteUser(int requestId);

}
