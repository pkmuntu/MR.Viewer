package com.muntu.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_DETAILS")
public class UserModelEntity implements Serializable {

	private static final long serialVersionUID = 4214452155277999478L;
	@Id
	@Column(name = "USER_ID")
	@SequenceGenerator(name = "gen1", sequenceName = "USER_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen1")
	private Integer userId;
	@Column(name = "USER_NAME", length = 20)
	private String userName;
	@Column(name = "USER_EMAIL", length = 20)
	private String email;
	@Column(name = "USER_PASSWORD", length = 20)
	private String password;
	@Column(name = "USER_MOBILE_NO")
	private String mobileNumber;
	@Column(name = "USER_ACTIVE")
	private String userActive;
	@Column(name = "ROLE", length = 20)
	private String role;
	@OneToMany(targetEntity = LikeEntity.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
	private List<LikeEntity> like;
	@OneToMany(targetEntity = MovieEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private List<MovieEntity> movies;

}
