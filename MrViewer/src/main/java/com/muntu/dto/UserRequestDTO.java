package com.muntu.dto;

public class UserRequestDTO {

	private Integer userId;
	private Integer requestId;
	private String description12;
	private String comment12;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getDescription12() {
		return description12;
	}

	public void setDescription12(String description12) {
		this.description12 = description12;
	}

	public String getComment12() {
		return comment12;
	}

	public void setComment12(String comment12) {
		this.comment12 = comment12;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [userId=" + userId + ", requestId=" + requestId + ", description12=" + description12
				+ ", comment12=" + comment12 + "]";
	}

}
