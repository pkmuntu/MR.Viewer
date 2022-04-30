package com.muntu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "request_request")
public class UserRequestEntity {

	@Id
	@Column(name = "REQUEST_ID")
	@SequenceGenerator(name = "gen12", sequenceName = "USER_REQUEST_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen12")
	private Integer requestId;

	@Column(name = "DESCRIPTION12", length = 50)
	private String description;

	@Column(name = "COMMENT12", length = 50)
	private String comment;

	@OneToOne(targetEntity = UserModelEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "USER_ID")
	private UserModelEntity userEntity;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserModelEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserModelEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String toString() {
		return "UserRequestEntity [requestId=" + requestId + ", description=" + description + ", comment=" + comment
				+ ", userEntity=" + userEntity + "]";
	}

}
