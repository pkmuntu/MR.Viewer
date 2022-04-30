package com.muntu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muntu.dao.UserModelDAO;
import com.muntu.dao.UserRequestDAO;
import com.muntu.dto.UserRequestDTO;
import com.muntu.entity.UserModelEntity;
import com.muntu.entity.UserRequestEntity;

@Service("userRequestService")
@Transactional
public class UserRequestServiceImplement implements UserRequestService {

	@Autowired
	@Qualifier("userRequestDao")
	private UserRequestDAO userRequestDao;

	@Autowired
	@Qualifier("usermodelDao")
	private UserModelDAO usermodelDao;

	public List<UserRequestDTO> getAllUsersRequest() {

		List<UserRequestDTO> userlistdto = new ArrayList<UserRequestDTO>();

		List<UserModelEntity> entityRequestlist = usermodelDao.getAllUsers1();

		List<UserRequestEntity> entitylist = userRequestDao.getAllUserRequest1();
		// System.out.println(entitylist+"******");

		entitylist.forEach(entity -> {
			UserRequestDTO requestDto = new UserRequestDTO();

			// UserDTO requestDto = new UserDTO();

			// UserDTO userdto = new UserDTO();
			// userdto.setUserId(entity.getUserEntity().getUserId());

			requestDto.setRequestId(entity.getRequestId());
			requestDto.setUserId(entity.getUserEntity().getUserId());
			requestDto.setDescription12(entity.getDescription());
			requestDto.setComment12(entity.getComment());

			// BeanUtils.copyProperties(entity, requestDto);
			userlistdto.add(requestDto);
		});
		return userlistdto;

	}

	public UserRequestDTO insertUserRequest(UserRequestDTO userRequest) {

		UserRequestEntity entity = new UserRequestEntity();

		entity.setDescription(userRequest.getDescription12());
		entity.setComment(userRequest.getComment12());

		userRequestDao.addRequest1(entity, userRequest.getUserId());
		return userRequest;
	}

	@Override
	public void deleteUser(int requestId) {

		userRequestDao.deleteUser1(requestId);
	}

}
