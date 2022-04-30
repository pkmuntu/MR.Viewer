package com.virtusa.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.virtusa.vo.MovieVO;

@Component
public class CustomUpdateFileValidator implements Validator {

	public static final String IMAGE_MIME_TYPE = "image/jpeg";
	public static final String VIDEO_MIME_TYPE = "video/mp4";
	public static final long TWO_MB_IN_BYTES = 2000000000;

	@Override
	public boolean supports(Class<?> clazz) {
		return MovieVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MovieVO vo = (MovieVO) target;
		/*
		 * if (vo.getMovieName() == null || vo.getMovieName().isBlank()) // required
		 * rule errors.rejectValue("movieName", "movie.name");
		 * 
		 * if (Objects.isNull(vo.getCategory())) { errors.rejectValue("category",
		 * "movie.category"); }
		 */

		/*
		 * if (vo.getMovieCast().isEmpty() || vo.getMovieCast().size() < 1) // required
		 * rule errors.rejectValue("movieCast", "movie.cast");
		 */

		/*
		 * if (vo.getMoviePoster().isEmpty()) { errors.rejectValue("moviePoster",
		 * "movie.poster"); }
		 * 
		 */
		if (vo.getMoviePoster() != null && !vo.getMoviePoster().isEmpty()) {
			if (!IMAGE_MIME_TYPE.equalsIgnoreCase(vo.getMoviePoster().getContentType())) {
				errors.rejectValue("moviePoster", "movie.filetype");
			} else if (vo.getMoviePoster().getSize() > TWO_MB_IN_BYTES) {
				errors.rejectValue("moviePoster", "movie.filetype.size");
			}
		}
		/*
		 * if (vo.getMovie().isEmpty()) { errors.rejectValue("movie", "movie.video"); }
		 * else
		 */
		if (vo.getMovie() != null && !vo.getMovie().isEmpty()) {
			if (!VIDEO_MIME_TYPE.equalsIgnoreCase(vo.getMovie().getContentType())
					|| vo.getMovie().getContentType().equalsIgnoreCase("video/quicktime")) {
				errors.rejectValue("movie", "movie.filetype");
			}
		}
	}

}
