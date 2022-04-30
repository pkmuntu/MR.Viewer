package com.virtusa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.dao.IMovieDAO;
import com.virtusa.database.copy.IMovieSearchable;
import com.virtusa.dto.MovieDTO;
import com.virtusa.entity.MovieEntity;
import com.virtusa.entity.UserModelEntity;
import com.virtusa.enumeration.Category;
import com.virtusa.exception.ServerException;

@Service("movieService")
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private Logger logger;

	@Autowired
	private IMovieDAO dao;

	@Autowired
	private IMovieSearchable searchMovie;

	@Override
	@Transactional
	public String saveMovie(MovieDTO dto) throws Exception {
		logger.debug("MovieServiceImpl class  saveMovie method");
		logger.info("MovieServiceImpl class  saveMovie method");
		UserModelEntity user = dao.getPresentAdmin(dto.getUserId());
		MovieEntity entity = new MovieEntity();
		entity.setMovieName(dto.getMovieName());
		entity.setMovieUrl(dto.getMovieUrl());
		entity.setMoviePosterUrl(dto.getMoviePosterUrl());
		entity.setCategory(dto.getCategory());
		entity.setMovieCasts(dto.getMovieCasts());
		entity.setUser(user);
		Integer idVal = dao.addMovie(entity);
		if (searchMovie.addMovie(dto) && idVal != 0)
			return (idVal != 0) ? "Movie Added!" : "Problem while adding movies!";
		else
			throw new ServerException();
	}

	@Override
	@Transactional
	public List<MovieDTO> getAllMovie(Integer pageNo, Integer pageSize) throws Exception {
		logger.debug("MovieServiceImpl class  getAllMovie method");
		logger.info("MovieServiceImpl class  getAllMovie method");
		// logic for pagination
		Integer startPos = (pageNo * pageSize) - pageSize;
		List<MovieEntity> movies = dao.getAllMovieUploadedByUserByPagenation(startPos, pageSize);
		List<MovieDTO> listDTO = new ArrayList<>();
		movies.forEach(movie -> {
			MovieDTO dto = new MovieDTO();
			dto.setMovieId(movie.getMovieId());
			dto.setMovieName(movie.getMovieName());
			dto.setMovieUrl(movie.getMovieUrl());
			dto.setMoviePosterUrl(movie.getMoviePosterUrl());
			dto.setCategory(movie.getCategory());
			dto.setMovieCasts(movie.getMovieCasts());
			listDTO.add(dto);
		});
		return listDTO;
	}

	@Override
	@Transactional
	public String deleteMovie(Integer movieId) throws Exception {
		logger.debug("MovieServiceImpl class deleteMovie  method");
		logger.info("MovieServiceImpl class  deleteMovie method");
		Category cat=dao.getMovie(movieId).getCategory();
		searchMovie.deleteMovieById(movieId,cat);
		Boolean cfm = dao.deleteMovie(movieId);
		if (cfm)
			return "Movie Deleted!";
		else
			throw new ServerException();
	}

	@Override
	@Transactional
	public MovieDTO updateMovie(MovieDTO dto) throws Exception {
		logger.debug("MovieServiceImpl class  updateMovie method");
		logger.info("MovieServiceImpl class  updateMovie method");
		MovieEntity entity = new MovieEntity();
		entity.setMovieId(dto.getMovieId());
		entity.setCategory(dto.getCategory());
		entity.setMovieName(dto.getMovieName());
		entity.setMoviePosterUrl(dto.getMoviePosterUrl());
		entity.setMovieUrl(dto.getMovieUrl());
		MovieEntity entity1 = dao.updateMovie(entity);
		// convert entity1 to movieDTO
		dto.setCategory(entity1.getCategory());
		dto.setMovieCasts(entity1.getMovieCasts());
		dto.setMovieName(entity1.getMovieName());
		dto.setMoviePosterUrl(entity1.getMoviePosterUrl());
		dto.setMovieUrl(entity1.getMovieUrl());
		searchMovie.updateMovie(dto);
		return dto;
	}

	@Override
	@Transactional
	public Long getRecordCount() throws Exception {
		logger.debug("MovieServiceImpl class  getRecordCount method");
		logger.info("MovieServiceImpl class  getRecordCount method");
		return dao.getRecordsCount();
	}

	@Override
	@Transactional
	public MovieDTO getMovie(Integer movieId) throws Exception {
		logger.debug("MovieServiceImpl class  getMovie method");
		logger.info("MovieServiceImpl class  getMovie method");
		MovieEntity entity = dao.getMovie(movieId);
		MovieDTO dto = new MovieDTO();
		dto.setMovieName(entity.getMovieName());
		dto.setMovieId(entity.getMovieId());
		dto.setCategory(entity.getCategory());
		dto.setMoviePosterUrl(entity.getMoviePosterUrl());
		dto.setMovieUrl(entity.getMovieUrl());
		dto.setUserId(entity.getUser().getUserId());
		dto.setMovieCasts(entity.getMovieCasts());
		return dto;
	}

	@Override
	public List<MovieDTO> searchMovie(String name) throws Exception {
		logger.debug("MovieServiceImpl class  searchMovie method");
		logger.info("MovieServiceImpl class  searchMovie method");
		return searchMovie.searchMovie(name);
	}

	@Override
	@Transactional
	public Set<String> getMovieCategory() throws Exception {
		logger.debug("MovieServiceImpl class  getMovieCategory method");
		logger.info("MovieServiceImpl class  getMovieCategory method");
		return dao.getMovieCategory().stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional
	public Long getLikeCount(Integer movieId) throws Exception {
		logger.debug("MovieServiceImpl class  getLikeCount method");
		logger.info("MovieServiceImpl class  getLikeCount method");
		return dao.getAllLike(movieId);
	}

	@Override
	@Transactional
	public Integer addLike(Integer userId, Integer MovieId) throws Exception {
		logger.debug("MovieServiceImpl class  addLike method");
		logger.info("MovieServiceImpl class  addLike method");
		return dao.addLike(userId, MovieId);
	}

	@Override
	@Transactional
	public Integer removeLike(Integer userId, Integer movieId) throws Exception {
		logger.debug("MovieServiceImpl class  removeLike method");
		logger.info("MovieServiceImpl class  removeLike method");
		return dao.removeLike(userId, movieId);
	}

	@Override
	@Transactional
	public Boolean checkUserLike(Integer userId, Integer movieId) throws Exception {
		return dao.checkUserLike(userId, movieId);
	}
}
