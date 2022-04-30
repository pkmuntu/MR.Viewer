package com.virtusa.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.exception.DataBaseError;
import com.virtusa.exception.LIkeIdNotFoundException;
import com.virtusa.exception.MovieNotFoundException;
import com.virtusa.exception.ServerException;
import com.virtusa.exception.UserNotFoundException;

@ControllerAdvice(assignableTypes = { MovieController.class, StreamingController.class, UserController.class })
class GlobalDefaultExceptionHandler {

	/*
	 * @ResponseStatus(HttpStatus.CONFLICT) // 409
	 * 
	 * @ExceptionHandler(DataIntegrityViolationException.class) public void
	 * handleConflict() { // Nothing to do }
	 */

	@ExceptionHandler({ DataBaseError.class, SQLException.class, LIkeIdNotFoundException.class,
			MovieNotFoundException.class, UserNotFoundException.class, ServerException.class })
	public ModelAndView databaseError(HttpServletRequest req, Exception ex) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("databaseError");
		return mav;
	}

	@ExceptionHandler({ java.lang.IllegalStateException.class })
	public ModelAndView sessionInvalidate(HttpServletRequest req, Exception ex) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("databaseError");
		return mav;
	}
}