package com.virtusa.database.copy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.virtusa.dao.IMovieDAO;
import com.virtusa.dto.MovieDTO;
import com.virtusa.entity.MovieEntity;
import com.virtusa.enumeration.Category;

@Component
public class MovieCatalog implements IMovieSearchable {

	private IMovieDAO dao;

	@Autowired
	private Logger logger;

	private HashMap<Category, ArrayList<MovieDTO>> categoryMovieMap;
	ArrayList<MovieDTO> listAction = new ArrayList<>();
	ArrayList<MovieDTO> listThriller = new ArrayList<>();
	ArrayList<MovieDTO> listDrama = new ArrayList<>();
	ArrayList<MovieDTO> listCrime = new ArrayList<>();
	ArrayList<MovieDTO> listComedy = new ArrayList<>();
	ArrayList<MovieDTO> listAdventure = new ArrayList<>();
	ArrayList<MovieDTO> listSci_fi = new ArrayList<>();

	@Autowired
	public MovieCatalog(IMovieDAO dao) {
		System.out.println("MovieCatelog constructor before all statement***********");
		this.dao = dao;

		List<MovieEntity> list = null;
		try {
			list = dao.loadData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("MovieCatelog constructor catch block************");
			e.printStackTrace();
		}
		System.out.println("MovieCatelog constructor  after catch block************");
		List<MovieDTO> save = listAction;
		for (MovieEntity entity : list) {
			MovieDTO dto = new MovieDTO();
			dto.setMovieName(entity.getMovieName());
			dto.setMovieId(entity.getMovieId());
			dto.setMoviePosterUrl(entity.getMoviePosterUrl());
			dto.setMovieUrl(entity.getMovieUrl());
			dto.setCategory(entity.getCategory());
			dto.setMovieCasts(entity.getMovieCasts());
			dto.setUserId(entity.getUser().getUserId());
			switch (entity.getCategory()) {
			case ACTION: {
				listAction.add(dto);
				break;
			}
			case THRILLER: {
				listThriller.add(dto);
				break;
			}
			case DRAMA: {
				listDrama.add(dto);
				break;
			}
			case CRIME: {
				listCrime.add(dto);
				break;
			}
			case COMEDY: {
				listComedy.add(dto);
				break;
			}
			case ADVENTURE: {
				listAdventure.add(dto);
				break;
			}
			case SCI_FI: {
				listSci_fi.add(dto);
				break;
			}

			}

		}
		System.out.println("MovieCatelog constructor before hashMap insertion************");
		categoryMovieMap = new HashMap<Category, ArrayList<MovieDTO>>();
		categoryMovieMap.put(Category.ACTION, listAction);
		categoryMovieMap.put(Category.ADVENTURE, listAdventure);
		categoryMovieMap.put(Category.COMEDY, listComedy);
		categoryMovieMap.put(Category.DRAMA, listDrama);
		categoryMovieMap.put(Category.CRIME, listCrime);
		categoryMovieMap.put(Category.THRILLER, listThriller);
		categoryMovieMap.put(Category.SCI_FI, listSci_fi);

	}

	public Boolean addMovie(MovieDTO dto) {
		logger.debug("MovieCatelog class  addMovie method");
		logger.info("MovieCatelog class  addMovie method");
		Category cat = dto.getCategory();
		ArrayList<MovieDTO> movieList = categoryMovieMap.get(cat);
		return movieList.add(dto);
	}

	@Override
	public ArrayList<MovieDTO> searchMovie(String mName) {
		logger.debug("MovieCatelog class  searchMovie method");
		logger.info("MovieCatelog class  searchMovie method");
		ArrayList<MovieDTO> list = new ArrayList<>();
		for (ArrayList<MovieDTO> movieList : categoryMovieMap.values()) {
			for (MovieDTO movie : movieList) {
				if (movie.getMovieName().toLowerCase().contains(mName.toLowerCase()))
					list.add(movie);
			}
		}
		return list;
	}

	@Override
	public ArrayList<MovieDTO> getAllMovieByCategory(Category cat) {
		logger.debug("MovieCatelog class  getAllMovieByCategory method");
		logger.info("MovieCatelog class  getAllMovieByCategory method");
		return categoryMovieMap.get(cat);
	}

	@Override
	public Boolean updateMovie(MovieDTO dto) {
		logger.debug("MovieCatelog class  updateMovie method");
		logger.info("MovieCatelog class  updateMovie method");
		MovieDTO moviedto = null;
		Category cat = null;
		ArrayList<MovieDTO> listdto = null;
		for (ArrayList<MovieDTO> list : categoryMovieMap.values()) {
			listdto = new ArrayList<MovieDTO>();
			for (MovieDTO movie : list) {

				if (movie.getMovieId() != dto.getMovieId()) {
					cat = movie.getCategory();
					movie.setCategory(dto.getCategory());
					if (dto.getMovieName() != null)
						movie.setMovieName(dto.getMovieName());
					if (dto.getMovieUrl() != null)
						movie.setMovieUrl(dto.getMovieUrl());
					if (dto.getMoviePosterUrl() != null)
						movie.setMoviePosterUrl(dto.getMoviePosterUrl());
					moviedto = movie;

				} else {
					listdto.add(movie);
				}
				if (cat != null)
					break;
			}

		}
		categoryMovieMap.remove(cat);
		categoryMovieMap.put(cat, listdto);
		addMovie(moviedto);
		return true;
	}

	@Override
	public Long getRecordCount() {
		logger.debug("MovieCatelog class  getRecordCount method");
		logger.info("MovieCatelog class  getRecordCount method");
		Long size = 0L;
		for (List<MovieDTO> list : categoryMovieMap.values()) {
			size = size + list.size();
		}
		return size;
	}

	@Override
	public MovieDTO searchExactMovie(String name) {
		logger.debug("MovieCatelog class  searchExactMovie method");
		logger.info("MovieCatelog class  searchExactMovie method");
		for (ArrayList<MovieDTO> movieList : categoryMovieMap.values()) {
			for (MovieDTO movie : movieList) {
				if (movie.getMovieName().equalsIgnoreCase(name))
					return movie;
			}
		}
		return null;
	}

	@Override
	public MovieDTO deleteMovieById(Integer movieId, Category cat) {
		logger.debug("MovieCatelog class  deleteMovie method");
		logger.info("MovieCatelog class  deleteMovie method");
		ArrayList<MovieDTO> mList = new ArrayList<MovieDTO>();
		for (ArrayList<MovieDTO> movieList : categoryMovieMap.values()) {

			for (MovieDTO movie : movieList) {

				if (movie.getMovieId() == movieId && movie.getCategory().equals(cat)) {
					mList.add(movie);
				}

			}

		}
		categoryMovieMap.remove(cat);
		categoryMovieMap.put(cat, mList);
		return null;
	}

}
