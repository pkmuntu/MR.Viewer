package com.muntu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.muntu.dto.UserDTO;
import com.muntu.dto.UserRequestDTO;
import com.muntu.service.UserModelService;
import com.muntu.service.UserRequestService;

@Controller
public class RequestController {

	@Autowired
	private UserRequestService userRequestService;

	@Autowired
	private UserModelService usermodelService;

	@RequestMapping(value = "/getAllUsersRequestList", method = RequestMethod.GET)
	public String getAllUsersRequestList(Map<String, Object> model) {

		List<UserDTO> dto = usermodelService.getAllUsers();
		// checking email exist or not
		List<UserRequestDTO> list = userRequestService.getAllUsersRequest();
		System.out.println(list);
		// model.put("requestmodel", dto.get(0));
		model.put("requestmodel", list);

		System.out.println(list);
		// return "usermodelsList";
		return "adminRequest";
	}

	@RequestMapping(value = "/userRequest", method = RequestMethod.POST)
	public String addUserRequest(@ModelAttribute("request") UserRequestDTO userRequestDto, Map<String, Object> model) {
		userRequestService.insertUserRequest(userRequestDto);
		model.put("confirmation", "Request has sent");
		return "anyTimeHelp";
	}

	@RequestMapping(path = "/validaterequest", method = RequestMethod.POST)
	public String requestLoginValidate(HttpSession session, @ModelAttribute("request") UserDTO userDto,
			Map<String, Object> model, Errors errors) {

		// List<UserDTO> dtoList = usermodelService.getAllUsers();
		List<UserDTO> dtoList = usermodelService.getAllUsers();

		if (errors.hasErrors()) {
			System.out.println("Errors Checking");
			return "login1";
		} else {
			for (UserDTO user1 : dtoList) {
				if ((user1.getEmail().equals(userDto.getEmail()))
						&& (user1.getPassword().equals(userDto.getPassword()))) {
					if (user1.getUserActive().equalsIgnoreCase("Offline")) {
						session.setAttribute("UserName", user1.getUserName());
						model.put("confirm", "Welcome User" + userDto.getUserName());
						model.put("userId", user1.getUserId());
						return "anyTimeHelp";
					} else if (user1.getRole().equalsIgnoreCase("admin")) {
						model.put("confirm", "Page For Only User Request Purposes");
						return "login1";
					} else if (user1.getUserActive().equalsIgnoreCase("Online")) {
						session.setAttribute("UserName", user1.getUserName());
						model.put("confirma",
								"- " + user1.getUserName() + " You Alreay Online no need to access this page");
						return "login1";
					} else {
						model.put("confirma", " Does't Exist");
						return "login1";
					}
				}
			}
			return "login1";
		}
	}

	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public ModelAndView deleteUserRequest(@RequestParam("requestId") int requestId, Map<String, Object> model) {
		userRequestService.deleteUser(requestId);

		// List<UserRequestDTO> userRequestDtoList =
		// userRequestService.getAllUsersRequest();

		// model.put("requestmodel", userRequestDtoList);

		model.put("confirmation", "Request is approved");
		return new ModelAndView("adminpage");
	}

//	private UserRequestEntity prepareRequest(UserRequestDTO userRequestDto){
//		UserRequestEntity userRequest = new UserRequestEntity();
//		//usermodel.setUserId(usermodelDto.getUserEmail());
//		userRequest.setRequestId(userRequestDto.getRequestId());
//		userRequest.setDescription(userRequestDto.getDescription12());
//		userRequest.setComment(userRequestDto.getComment12());
//		return userRequest;
//	}

}
