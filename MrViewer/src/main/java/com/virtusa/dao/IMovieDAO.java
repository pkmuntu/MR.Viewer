package com.virtusa.dao;

import java.util.List;

import com.virtusa.entity.MovieEntity;
import com.virtusa.entity.UserModelEntity;

public interface IMovieDAO {

	public Integer addMovie(MovieEntity movie) throws Exception;

	public List<MovieEntity> getAllMovieUploadedByUserByPagenation(Integer startPosition, Integer pageSize)
			throws Exception;

	public Boolean deleteMovie(Integer movieId) throws Exception;

	public MovieEntity updateMovie(MovieEntity movie) throws Exception;

	public UserModelEntity getPresentAdmin(Integer UserId) throws Exception;

	public long getRecordsCount() throws Exception;

	public MovieEntity getMovie(Integer movieId) throws Exception;

	public List<MovieEntity> loadData() throws Exception;

	public List<String> getMovieCategory() throws Exception;

	public Long getAllLike(Integer movieId) throws Exception;

	public Integer addLike(Integer userId, Integer movieId) throws Exception;

	public Integer removeLike(Integer userId, Integer movieId) throws Exception;

	public Boolean checkUserLike(Integer userId, Integer movieId) throws Exception;

}
