package com.muntu.dto;

import java.util.Set;

import com.muntu.enumeration.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {

	private Integer movieId;
	private String movieName;
	private String movieUrl;
	private String moviePosterUrl;
	private Category category;
	private Set<String> movieCasts;
	private Integer userId;

}