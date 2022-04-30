package com.virtusa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.dao.UserModelDAO;
import com.virtusa.dto.UserDTO;
import com.virtusa.entity.UserModelEntity;
import com.virtusa.exception.EmailOrMobileNumberAlreadyExistException;

@Service("usermodelService")
@Transactional
public class UserModelServiceImplement implements UserModelService {

	@Autowired
	@Qualifier("usermodelDao")
	private UserModelDAO usermodelDao;

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> listdto = new ArrayList<UserDTO>();

		List<UserModelEntity> entitylist = usermodelDao.getAllUsers1();

		entitylist.forEach(entity -> {
			UserDTO userdto = new UserDTO();
			userdto.setUserId(entity.getUserId());
			userdto.setEmail(entity.getEmail());
			userdto.setUserName(entity.getUserName());
			userdto.setPassword(entity.getPassword());
			userdto.setMobileNumber(entity.getMobileNumber());
			userdto.setUserActive(entity.getUserActive());
			userdto.setRole(entity.getRole());

			listdto.add(userdto);
		});

		return listdto;
	}

	@Override
	public UserDTO insertUser(UserDTO dto) throws EmailOrMobileNumberAlreadyExistException {
		UserModelEntity entity = new UserModelEntity();

		entity.setEmail(dto.getEmail());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setMobileNumber(dto.getMobileNumber());
		entity.setUserActive(dto.getUserActive());
		entity.setRole(dto.getRole());

		UserModelEntity entity1 = usermodelDao.insertUser1(entity);
		dto.setUserId(entity1.getUserId());
		return dto;
	}

	@Override
	public UserDTO updateUser(UserDTO userDto) throws EmailOrMobileNumberAlreadyExistException {

		UserModelEntity entity = new UserModelEntity();

		entity.setUserId(userDto.getUserId());
		entity.setEmail(userDto.getEmail());
		entity.setUserName(userDto.getUserName());
		entity.setPassword(userDto.getPassword());
		entity.setMobileNumber(userDto.getMobileNumber());
		entity.setUserActive(userDto.getUserActive());
		entity.setRole(userDto.getRole());
		usermodelDao.updateUser1(entity);

		return userDto;

	}

	@Override
	public void deleteUser(int userId) {

		usermodelDao.deleteUser1(userId);
	}

	@Override
	public UserDTO getUserById(int userId) {

		UserModelEntity entity = usermodelDao.getUserById(userId);

		UserDTO dto = new UserDTO();

		dto.setUserId(entity.getUserId());
		dto.setUserName(entity.getUserName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setMobileNumber(entity.getMobileNumber());
		dto.setUserActive(entity.getUserActive());
		dto.setRole(entity.getRole());

		return dto;
	}

}