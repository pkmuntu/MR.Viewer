package com.muntu.service;

import java.util.List;
import java.util.Set;

import com.muntu.dto.MovieDTO;

public interface IMovieService {

	public String saveMovie(MovieDTO dto) throws Exception;

	public List<MovieDTO> getAllMovie(Integer pageNo, Integer pageSize) throws Exception;

	public String deleteMovie(Integer movieId) throws Exception;

	public MovieDTO updateMovie(MovieDTO dto) throws Exception;

	public Long getRecordCount() throws Exception;

	public MovieDTO getMovie(Integer movieId) throws Exception;

	public List<MovieDTO> searchMovie(String name) throws Exception;

	public Set<String> getMovieCategory() throws Exception;

	public Long getLikeCount(Integer movieId) throws Exception;

	public Integer addLike(Integer userId, Integer MovieId) throws Exception;

	public Integer removeLike(Integer userId, Integer movieId) throws Exception;

	public Boolean checkUserLike(Integer userId, Integer movieId) throws Exception;

}
