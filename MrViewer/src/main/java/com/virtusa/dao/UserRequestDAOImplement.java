package com.virtusa.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.UserModelEntity;
import com.virtusa.entity.UserRequestEntity;

@Repository("userRequestDao")
public class UserRequestDAOImplement implements UserRequestDAO {

	@Autowired(required = true)
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<UserRequestEntity> getAllUserRequest1() {
		List<UserRequestEntity> userRequestList = hibernateTemplate.loadAll(UserRequestEntity.class);
		return userRequestList;
	}

	@Override
	public void addRequest1(UserRequestEntity userRequest, int userId) {
		UserModelEntity enity = hibernateTemplate.get(UserModelEntity.class, userId);
		userRequest.setUserEntity(enity);
		hibernateTemplate.save(userRequest);

	}

	@Override
	public void deleteUser1(int requestId) {
		UserRequestEntity entity = hibernateTemplate.get(UserRequestEntity.class, requestId);
		UserModelEntity user = hibernateTemplate.get(UserModelEntity.class, entity.getUserEntity().getUserId());
		user.setUserActive("Online");
		hibernateTemplate.saveOrUpdate(user);
		entity.setUserEntity(null);
		hibernateTemplate.delete(entity);
	}

}
