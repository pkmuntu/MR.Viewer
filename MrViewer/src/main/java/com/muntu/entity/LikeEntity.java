package com.muntu.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MOVIE_LIKE")
public class LikeEntity implements Serializable {

	private static final long serialVersionUID = 3676358577348946274L;
	@Id
	@Column(name = "LIKE_ID")
	@SequenceGenerator(name = "gen3", sequenceName = "LIKE_SEQ", initialValue = 100, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen3")
	private Integer likeId;
	@ManyToOne(targetEntity = UserModelEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private UserModelEntity user;

	@ManyToOne(targetEntity = MovieEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
	private MovieEntity movie;

}
