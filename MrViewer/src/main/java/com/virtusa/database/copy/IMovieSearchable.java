package com.virtusa.database.copy;

import java.util.ArrayList;

import com.virtusa.dto.MovieDTO;
import com.virtusa.enumeration.Category;

public interface IMovieSearchable {

	public Boolean addMovie(MovieDTO dto);

	public ArrayList<MovieDTO> searchMovie(String mName);

	public ArrayList<MovieDTO> getAllMovieByCategory(Category cat);

	public Boolean updateMovie(MovieDTO dto);

	public Long getRecordCount();

	public MovieDTO searchExactMovie(String name);

	public MovieDTO deleteMovieById(Integer movieId, Category cat);
}
