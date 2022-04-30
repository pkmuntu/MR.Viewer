package com.muntu.vo;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.muntu.enumeration.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieVO {

	private Integer movieId;
	private String movieName;
	private Set<String> movieCast;
	private Category category;
	private MultipartFile moviePoster;
	private MultipartFile movie;

}
