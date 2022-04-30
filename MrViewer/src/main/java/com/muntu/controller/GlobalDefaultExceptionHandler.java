package com.muntu.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.muntu.exception.DataBaseError;
import com.muntu.exception.LIkeIdNotFoundException;
import com.muntu.exception.MovieNotFoundException;
import com.muntu.exception.ServerException;
import com.muntu.exception.UserNotFoundException;

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