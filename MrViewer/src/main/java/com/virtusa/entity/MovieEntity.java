package com.virtusa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ListIndexBase;

import com.virtusa.enumeration.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MOVIE")
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = -7065363954656742755L;
	@Id
	@Column(name = "MOVIE_ID")
	@SequenceGenerator(name = "gen2", sequenceName = "USER_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen2")
	private Integer movieId;
	@Column(name = "MOVIE_NAME", length = 20)
	private String movieName;
	@Column(name = "MOVIE_URL", length = 150)
	private String movieUrl;
	@Column(name = "MOVIE_POSTER_URL", length = 150)
	private String moviePosterUrl;

	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY", length = 20)
	private Category category;

	@NonNull
	@OrderColumn(name = "CAST_INDEX")
	@ListIndexBase(value = 1)
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")
	@CollectionTable(name = "MOVIE_CAST")
	private Set<String> movieCasts;

	@OneToMany(targetEntity = LikeEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "movie")
	private Set<LikeEntity> likes;

	@ManyToOne(targetEntity = UserModelEntity.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private UserModelEntity user;

}
