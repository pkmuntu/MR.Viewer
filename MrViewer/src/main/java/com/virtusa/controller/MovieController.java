package com.virtusa.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtusa.database.copy.IMovieSearchable;
import com.virtusa.dto.MovieDTO;
import com.virtusa.service.IMovieService;
import com.virtusa.utility.FileUploadUtil;
import com.virtusa.validator.CustomUpdateFileValidator;
import com.virtusa.validator.CustomUploadFileValidator;
import com.virtusa.vo.MovieVO;

@Controller
@RequestMapping("/admin")
public class MovieController {

	@Autowired
	private Logger logger;

	@Autowired
	private IMovieService service;

	@Autowired
	private CustomUploadFileValidator fileValidator;
	@Autowired
	private CustomUpdateFileValidator updateFileValidator;

	@Autowired
	private IMovieSearchable searchable;

	public MovieController() {
		System.out.println("Controller controller*************");

	}

	@GetMapping("/home")
	public String showHomeFormPage(@ModelAttribute("movieForm") MovieVO movie, Map<String, Object> map,
			HttpServletRequest request, HttpSession ses) throws Exception {
		logger.debug("Controler class showHomeFormPage");
		logger.info("Controler class showHomeFormPage");
		ses = request.getSession();
		ses.setAttribute("movieCount", 8);
		map.put("pageNo", 1);
		List<MovieDTO> list = service.getAllMovie(1, (Integer) ses.getAttribute("movieCount"));
		map.put("listMovies", list);
		map.put("recordCount", list.size());
		return "first_show_movie_list";
	}

	@RequestMapping(value = "/setPazeSize", method = RequestMethod.GET)
	public String setPageSize(HttpSession ses, HttpServletRequest request, @RequestParam("noOfMovie") int number) {
		ses.setAttribute("movieCount", number);
		logger.debug("MovieControler class  setPageSize method");
		logger.info("MovieControler class setPageSize method");
		return "redirect:getMovies";
	}

	@GetMapping("/addMoviePage")
	public String showAddFormPage(@ModelAttribute("movieForm") MovieVO movie) {
		logger.debug("MovieControler class ShowAddFormPage");
		logger.info("MovieControler class showAddFormPage");
		return "add_movie_form_page";
	}

	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String UploadForm(Map<String, Object> map, @ModelAttribute("movieForm") MovieVO movie,
			BindingResult bindingResult, HttpSession ses) throws Exception {

		logger.debug("MovieControler class  uploadForm before validate");
		logger.info("MovieControler class uploadForm before validate");
		// Form validation
		fileValidator.validate(movie, bindingResult);
		if (bindingResult.hasErrors()) {
			movie.setMovieCast(null);
			return "add_movie_form_page";
		}

		if (searchable.searchExactMovie(movie.getMovieName()) != null) {
			movie.setMovieCast(null);
			map.put("conformation", "Movie already present!");
			return "add_movie_form_page";
		}
		// convert MovieVO to MovieDTO
		MovieDTO dto = new MovieDTO();
		dto.setMovieName(movie.getMovieName());
		dto.setCategory(movie.getCategory());
		dto.setMovieCasts(movie.getMovieCast());
		dto.setMoviePosterUrl(FileUploadUtil.saveFile("poster", movie.getMoviePoster()));
		dto.setMovieUrl(FileUploadUtil.saveFile("movie", movie.getMovie()));
		logger.debug("MovieControler class uploadForm after file upload");
		logger.info("MovieControler class uploadForm after file upload");
		// replaceable code
		dto.setUserId((Integer) ses.getAttribute("userId"));
		// use service
		String msg = service.saveMovie(dto);
		map.put("confirmation", msg);
		Long size = searchable.getRecordCount();
		List<MovieDTO> list = service.getAllMovie(1, (Integer) ses.getAttribute("movieCount"));
		map.put("pageNo", 1);
		map.put("recordCount", list.size());
		map.put("listMovies", list);
		return "show_movie_list";
	}

	@GetMapping("/searchMovie")
	public String searchMovie(Map<String, Object> map, @RequestParam("name") String name, HttpSession ses)
			throws Exception {
		if (name == null)
			return "redirect:getMovies";
		logger.debug("MovieControler class  searchMovie method");
		logger.info("MovieControler class searchMovie method");
		List<MovieDTO> list = searchable.searchMovie(name.trim());

		map.put("pageNo", 1);
		map.put("recordCount", list.size());
		map.put("listMovies", list);
		return "show_movie_list";
	}

	@GetMapping("/getMovies")
	public String getAllMovie(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			Map<String, Object> map, HttpSession ses) throws Exception {
		logger.debug("MovieControler class  getAllMovies method");
		logger.info("MovieControler class getAllMoives method");
		map.put("pageNo", pageNo);
		List<MovieDTO> list = service.getAllMovie(pageNo, (Integer) ses.getAttribute("movieCount"));
		map.put("recordCount", list.size());
		map.put("listMovies", list);
		return "show_movie_list";
	}

	@GetMapping("/getMovie")
	public String showMovieDetails(@RequestParam("movieId") Integer movieId, @ModelAttribute("movieForm") MovieVO movie,
			Map<String, Object> map) throws Exception {
		logger.debug("MovieControler class  showMovieDetails method");
		logger.info("MovieControler class showMovieDetails method");
		MovieDTO dto = service.getMovie(movieId);
		movie.setMovieName(dto.getMovieName());
		movie.setCategory(dto.getCategory());
		map.put("movie", dto);
		map.put("likeCount", service.getLikeCount(movieId));
		return "admin_movie_details_page";
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateMovieData(Map<String, Object> map, @ModelAttribute("movieForm") MovieVO movie,
			BindingResult bindingResult, HttpSession ses) throws Exception {

		logger.debug("MovieControler class  updateMovieDate method before validation");
		logger.info("MovieControler class updateMovieData method before validation");

		// Form validation
		updateFileValidator.validate(movie, bindingResult);
		if (bindingResult.hasErrors()) {
			MovieDTO dto = service.getMovie(movie.getMovieId());
			map.put("movie", dto);
			return "admin_movie_details_page";
		}

		if (searchable.searchExactMovie(movie.getMovieName()) != null) {

			movie.setMovieCast(null);
			MovieDTO dto = service.getMovie(movie.getMovieId());
			movie.setMovieName(dto.getMovieName());
			movie.setCategory(dto.getCategory());
			map.put("movie", dto);
			map.put("confirmation", "Movie already present with same Name!");
			return "admin_movie_details_page";
		}

		// convert MovieVO to MovieDTO
		MovieDTO dto = new MovieDTO();
		dto.setMovieId(movie.getMovieId());
		dto.setMovieName(movie.getMovieName());
		dto.setCategory(movie.getCategory());
		dto.setMovieCasts(movie.getMovieCast());
		if (movie.getMoviePoster() != null && !movie.getMoviePoster().isEmpty()) {
			dto.setMoviePosterUrl(FileUploadUtil.saveFile("poster", movie.getMoviePoster()));
		}
		if (movie.getMovie() != null && !movie.getMovie().isEmpty()) {
			dto.setMovieUrl(FileUploadUtil.saveFile("movie", movie.getMovie()));
		}
		// replaceable code
		dto.setUserId(100);
		// use service
		MovieDTO dto1 = service.updateMovie(dto);
		map.put("pageNo", 1);
		List<MovieDTO> list = service.getAllMovie(1, (Integer) ses.getAttribute("movieCount"));
		map.put("listMovies", list);
		map.put("recordCount", list.size());
		map.put("confirmation", "Movie Updated!");
		return "show_movie_list";
	}

	@GetMapping("/delete")
	public String deleteMovie(Map<String, Object> map, @RequestParam("movieId") Integer movieId, HttpSession ses)
			throws Exception {
		logger.debug("MovieControler class  deletMovie method");
		logger.info("MovieControler class  deletMovie method");

		String res = service.deleteMovie(movieId);
		map.put("confirmation", res);
		// use service
		map.put("pageNo", 1);
		List<MovieDTO> list = service.getAllMovie(1, (Integer) ses.getAttribute("movieCount"));
		map.put("listMovies", list);
		map.put("recordCount", list.size());
		return "show_movie_list";
	}

	@RequestMapping(value = "/watchMovie", method = RequestMethod.GET)
	public String showVideosPage(@RequestParam("url") String url, Map<String, Object> map) {
		logger.debug("MovieControler class  showVideo method");
		logger.info("MovieControler class  showVideo method");
		map.put("url", url);
		return "video_player";
	}

	/*
	 * @ModelAttribute("categoryList") public Set<String> populateCategorys() throws
	 * Exception { return service.getMovieCategory(); }
	 */

}
