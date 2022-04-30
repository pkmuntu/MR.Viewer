package com.virtusa.dao;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.virtusa.entity.UserModelEntity;
import com.virtusa.exception.EmailOrMobileNumberAlreadyExistException;

@Transactional
@Repository("usermodelDao")
public class UserModelDAOImplement implements UserModelDAO {

	public UserModelDAOImplement() {
		System.out.println(" UserModelDAOImplement class loading");
	}

	@Autowired(required = true)
	private HibernateTemplate hibernateTemplate;
	// private SessionFactory sessionFactory;

	@Override
	public List<UserModelEntity> getAllUsers1() {
		List<UserModelEntity> userList = new LinkedList<UserModelEntity>();
		userList = hibernateTemplate.loadAll(UserModelEntity.class);
		// System.out.println(userList);
		return userList;
	}

//	@Override
//	public UserModelEntity insertUser1(UserModelEntity user) {
//		this.hibernateTemplate.save(user);
//		return user;
//	}

	@Override
	public UserModelEntity insertUser1(UserModelEntity userEntity) throws EmailOrMobileNumberAlreadyExistException {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query qry = session
				.createQuery("select count(*) from UserModelEntity where email=:email OR mobileNumber=:mobileNumber");

		qry.setString("email", userEntity.getEmail());
		qry.setString("mobileNumber", userEntity.getMobileNumber());
		long count = (long) qry.uniqueResult();
		if (count == 0) {

			this.hibernateTemplate.save(userEntity);
		} else {
			throw new EmailOrMobileNumberAlreadyExistException();
		}
		System.out.println(count);
		return userEntity;
	}

	@Override
	public UserModelEntity updateUser1(UserModelEntity userEntity) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query qry = session
				.createQuery("select count(*) from UserModelEntity where email=:email OR mobileNumber=:mobileNumber");

		qry.setString("email", userEntity.getEmail());
		qry.setString("mobileNumber", userEntity.getMobileNumber());
		long count = (long) qry.uniqueResult();
		if (count == 1) {

			this.hibernateTemplate.saveOrUpdate(userEntity);
		} else {
			throw new EmailOrMobileNumberAlreadyExistException();
		}
		System.out.println(count);
		return userEntity;
	}

//	@Override
//	public UserModelEntity updateUser1(UserModelEntity entity) {
//		hibernateTemplate.saveOrUpdate(entity);
//		return entity;
//	}

	@Override
	public void deleteUser1(int userId) {
		UserModelEntity entity = hibernateTemplate.get(UserModelEntity.class, userId);
		hibernateTemplate.delete(entity);
	}

	@Override
	public UserModelEntity getUserById(int userId) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(UserModelEntity.class, userId);
	}

}
