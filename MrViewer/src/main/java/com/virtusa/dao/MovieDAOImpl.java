package com.virtusa.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.entity.LikeEntity;
import com.virtusa.entity.MovieEntity;
import com.virtusa.entity.UserModelEntity;
import com.virtusa.exception.MovieNotFoundException;
import com.virtusa.exception.UserNotFoundException;

@Repository("movieDAO")
public class MovieDAOImpl implements IMovieDAO {

	@Autowired
	private Logger logger;
	@Autowired
	private HibernateTemplate ht;

	@Override
	public Integer addMovie(MovieEntity movie) throws Exception {
		logger.debug("MovieDAOImpl class  addMovie method");
		logger.info("MovieDAOImpl class  addMovie method");
		Integer it = (Integer) ht.save(movie);
		return it;
	}

	@Override
	public Boolean deleteMovie(Integer movieId) throws Exception {
		logger.debug("MovieDAOImpl class  deleteMovie method");
		logger.info("MovieDAOImpl class  deleteMovie method");
		MovieEntity entity = ht.get(MovieEntity.class, movieId);
		if (entity != null)
			ht.delete(entity);
		else
			throw new MovieNotFoundException();
		return true;
	}

	@Override
	public MovieEntity updateMovie(MovieEntity movie) throws Exception {
		logger.debug("MovieDAOImpl class  updateMovie method");
		logger.info("MovieDAOImpl class  updateMovie method");
		MovieEntity entity = ht.get(MovieEntity.class, movie.getMovieId());

		if (movie.getCategory() != null)
			entity.setCategory(movie.getCategory());
		if (movie.getMovieName() != null && !movie.getMovieName().isBlank() && !movie.getMovieName().isEmpty())
			entity.setMovieName(movie.getMovieName());
		if (movie.getMoviePosterUrl() != null && !movie.getMoviePosterUrl().isBlank()
				&& !movie.getMoviePosterUrl().isEmpty())
			entity.setMoviePosterUrl(movie.getMoviePosterUrl());
		if (movie.getMovieUrl() != null && !movie.getMovieUrl().isBlank() && !movie.getMovieUrl().isEmpty())
			entity.setMovieUrl(movie.getMovieUrl());

		if (entity != null) {
			ht.merge(entity);
			return entity;
		} else
			throw new MovieNotFoundException();
	}

	@Override
	public UserModelEntity getPresentAdmin(Integer userId) throws Exception {
		logger.debug("MovieDAOImpl class  getPresentAdmin method");
		logger.info("MovieDAOImpl class  getPresentAdmin method");
		UserModelEntity user = ht.get(UserModelEntity.class, userId);
		if (user != null)
			return user;
		else
			throw new UserNotFoundException();
	}

	@Override
	public List<MovieEntity> getAllMovieUploadedByUserByPagenation(Integer start, Integer pageSize) throws Exception {
		logger.debug("MovieDAOImpl class  getAllMovie method");
		logger.info("MovieDAOImpl class  getAllMovie method");
		Session ses = ht.getSessionFactory().getCurrentSession();
		Query query = ses.createQuery("FROM MovieEntity");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<MovieEntity> list = query.getResultList();
		return list;
	}

	@Override
	public long getRecordsCount() throws Exception {
		logger.debug("MovieDAOImpl class  getRecordsCount method");
		logger.info("MovieDAOImpl class  getRecordsCount method");
		// get Session
		Session ses = ht.getSessionFactory().getCurrentSession();
		// Dummy Tx
		Transaction tx = null;
		if (!ses.getTransaction().isActive())
			tx = ses.beginTransaction();

		// Execute HQL with pagination
		Query query = ses.createQuery("SELECT COUNT(*) FROM MovieEntity");
		List<Long> list = query.getResultList();
		return (Long) list.get(0);
	}

	@Override
	public MovieEntity getMovie(Integer movieId) throws Exception {
		logger.debug("MovieDAOImpl class  getMovie method");
		logger.info("MovieDAOImpl class  getMovie method");
		MovieEntity entity = ht.get(MovieEntity.class, movieId);
		if (entity != null)
			return entity;
		else
			throw new MovieNotFoundException();
	}

	@Override
	@Transactional
	public List<MovieEntity> loadData() {
		logger.debug("MovieDAOImpl class  loadData method");
		logger.info("MovieDAOImpl class  loadDAta method");
		// get Data form data base
		Session ses = ht.getSessionFactory().getCurrentSession();
		Query query = ses.createQuery("FROM MovieEntity");
		System.out.println("MovieCatelog constructor before calling getResult************");
		List<MovieEntity> list = query.getResultList();
		System.out.println("MovieCatelog constructor after calling get result************");
		return list;
	}

	@Override
	public List<String> getMovieCategory() throws Exception {
		logger.debug("MovieDAOImpl class  getMovieCategory method");
		logger.info("MovieDAOImpl class  getMovieCategory method");
		Session ses = ht.getSessionFactory().getCurrentSession();
		Query query = ses.createNativeQuery("SELECT CATEGORYNAME FROM CATEGORY");
		List<String> list = query.getResultList();
		return list;
	}

	@Override
	public Long getAllLike(Integer movieId) throws Exception {
		logger.debug("MovieDAOImpl class  getAllLike method");
		logger.info("MovieDAOImpl class  getAllLike method");
		Session ses = ht.getSessionFactory().getCurrentSession();
		Query query = ses.createNativeQuery(" SELECT LIKE_ID FROM MOVIE_LIKE WHERE MOVIE_ID=:movieId");
		query.setParameter("movieId", movieId);
		List<String> list = query.getResultList();
		return (long) list.size();
	}

	@Override
	public Integer addLike(Integer userId, Integer MovieId) throws Exception {
		logger.debug("MovieDAOImpl class  addLike method");
		logger.info("MovieDAOImpl class  AddLike method");
		MovieEntity movie = ht.get(MovieEntity.class, MovieId);

		UserModelEntity user = ht.get(UserModelEntity.class, userId);

		LikeEntity like = new LikeEntity();
		like.setMovie(movie);
		like.setUser(user);
		return (Integer) ht.save(like);
	}

	@Override
	public Integer removeLike(Integer userId, Integer movieId) throws Exception {
		logger.debug("MovieDAOImpl class  removeLike method");
		logger.info("MovieDAOImpl class  removeLike method");
		Session ses = ht.getSessionFactory().getCurrentSession();
		Query query = ses.createNativeQuery("DELETE FROM MOVIE_LIKE WHERE USER_ID=:userId AND MOVIE_ID=:movieId");
		query.setParameter("userId", userId);
		query.setParameter("movieId", movieId);
		return query.executeUpdate();
	}

	@Override
	public Boolean checkUserLike(Integer userId, Integer movieId) throws Exception {
		Session ses = ht.getSessionFactory().getCurrentSession();
		MovieEntity me = ht.get(MovieEntity.class, movieId);
		UserModelEntity ue = ht.get(UserModelEntity.class, userId);
		Query query = ses.createQuery(" from LikeEntity l where user=:user and movie=:movie");
		query.setParameter("user", ue);
		query.setParameter("movie", me);
		List<LikeEntity> list = query.getResultList();
		logger.debug("MovieDAOImpl class  checkLikeCount method" + list.size());
		logger.info("MovieDAOImpl class  checkLikeCount method" + list.size());
		if (list.size() == 0)
			return false;
		else
			return true;
	}

}
