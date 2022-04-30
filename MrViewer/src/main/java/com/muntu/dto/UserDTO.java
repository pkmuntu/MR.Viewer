package com.muntu.dto;

public class UserDTO {

	private Integer userId;
	private String email;
	private String userName;
	private String password;
	private String mobileNumber;
	private String userActive;
	private String role;

	public UserDTO() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userEmail) {
		this.email = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserModelDTO [userId=" + userId + ", userEmail=" + email + ", userName=" + userName + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", userActive=" + userActive + ", role=" + role + "]";
	}

}
