package com.muntu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.muntu.database.copy.IMovieSearchable;
import com.muntu.dto.MovieDTO;
import com.muntu.service.IMovieService;

@Controller
public class UserMovieController {

	@Autowired
	private Logger logger;

	@Autowired
	private IMovieService service;

	@Autowired
	private IMovieSearchable searchable;

	@GetMapping("/home")
	public String showHomePage(HttpSession ses, HttpServletRequest req, Map<String, Object> map) throws Exception {
		logger.debug("UserContrller class  showHomePage method");
		logger.info("UserController class  showHomePage method");
		ses.setAttribute("movieCount", 8);
		ses.setMaxInactiveInterval(3000000);
		map.put("pageNo", 1);
		List<MovieDTO> list = service.getAllMovie(1, (Integer) ses.getAttribute("movieCount"));
		map.put("listMovies", list);
		map.put("recordCount", list.size());
		return "user_first_show_movie_list";
	}

	@GetMapping("/setPazeSize")
	public String setPageSize(@RequestParam("noOfMovie") Integer noOfMovie, HttpServletRequest req, HttpSession ses,
			Map<String, Object> map) throws Exception {
		logger.debug("UserContrller class  setPageSize method");
		logger.info("UserController class  setPageSize method");
		ses.setAttribute("movieCount", noOfMovie);
		map.put("pageNo", 1);
		List<MovieDTO> list = service.getAllMovie(1, (Integer) ses.getAttribute("movieCount"));
		map.put("listMovies", list);
		map.put("recordCount", list.size());
		return "user_show_movie_list";
	}

	@GetMapping("/getMovies")
	public String showMovieByPagenation(@RequestParam("pageNo") Integer pageNo, Map<String, Object> map,
			HttpSession ses) throws Exception {
		logger.debug("UserContrller class  getMovies method");
		logger.info("UserController class  getMovies method");
		map.put("pageNo", pageNo);
		List<MovieDTO> list = service.getAllMovie(pageNo, (Integer) ses.getAttribute("movieCount"));
		map.put("listMovies", list);
		map.put("recordCount", list.size());
		return "user_show_movie_list";
	}

	@GetMapping("/searchMovie")
	public String searchMovie(Map<String, Object> map, @RequestParam("name") String name, HttpSession ses)
			throws Exception {
		logger.debug("UserContrller class  searchMovie method");
		logger.info("UserController class  searchMovie method");
		if (name == null)
			return "redirect:getMovies";
		List<MovieDTO> list = searchable.searchMovie(name.trim());
		map.put("pageNo", 1);
		map.put("recordCount", list.size());
		map.put("listMovies", list);
		return "user_show_movie_list";
	}

	@GetMapping("/getMovie")
	public String showMovieDetails(@RequestParam("movieId") Integer movieId, Map<String, Object> map, HttpSession ses)
			throws Exception {
		logger.debug("UserContrller class  showMovieDetails method");
		logger.info("UserController class  showMovieDetails method");
		MovieDTO dto = service.getMovie(movieId);
		map.put("movie", dto);
		map.put("likeCount", service.getLikeCount(movieId));
		map.put("likeFlag", service.checkUserLike((Integer) ses.getAttribute("userId"), movieId));
		return "user_movie_details_page";
	}

	@RequestMapping(value = "/watchMovie", method = RequestMethod.GET)
	public String showVidosPage(@RequestParam("url") String url, Map<String, Object> map, HttpSession ses) {
		logger.debug("UserContrller class  showVideoPage method");
		logger.info("UserController class  showVideoPage method");
		map.put("url", url);
		return "video_player";
	}

	@GetMapping("/addLike")
	public String addLike(@RequestParam("movieId") Integer movieId, Map<String, Object> map, HttpSession ses)
			throws Exception {
		service.addLike((Integer) ses.getAttribute("userId"), movieId);
		MovieDTO dto = service.getMovie(movieId);
		map.put("movie", dto);
		map.put("likeCount", service.getLikeCount(movieId));
		map.put("likeFlag", service.checkUserLike((Integer) ses.getAttribute("userId"), movieId));
		return "user_movie_details_page";
	}

	@GetMapping("/removeLike")
	public String removeLike(@RequestParam("movieId") Integer movieId, Map<String, Object> map, HttpSession ses)
			throws Exception {
		service.removeLike((Integer) ses.getAttribute("userId"), movieId);
		MovieDTO dto = service.getMovie(movieId);
		map.put("movie", dto);
		map.put("likeCount", service.getLikeCount(movieId));
		map.put("likeFlag", service.checkUserLike((Integer) ses.getAttribute("userId"), movieId));
		return "user_movie_details_page";
	}

}
