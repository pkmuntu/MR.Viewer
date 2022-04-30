package com.muntu.database.copy;

import java.util.ArrayList;

import com.muntu.dto.MovieDTO;
import com.muntu.enumeration.Category;

public interface IMovieSearchable {

	public Boolean addMovie(MovieDTO dto);

	public ArrayList<MovieDTO> searchMovie(String mName);

	public ArrayList<MovieDTO> getAllMovieByCategory(Category cat);

	public Boolean updateMovie(MovieDTO dto);

	public Long getRecordCount();

	public MovieDTO searchExactMovie(String name);

	public MovieDTO deleteMovieById(Integer movieId, Category cat);
}
