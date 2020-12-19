package com.example.entrevueSpringBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entrevueSpringBoot.dto.request.FilmPostRequestBody;
import com.example.entrevueSpringBoot.dto.response.FilmGetResponse;
import com.example.entrevueSpringBoot.mapper.FilmMapper;
import com.example.entrevueSpringBoot.model.Film;
import com.example.entrevueSpringBoot.repository.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private FilmMapper filmMapper;
	
	public Optional<FilmGetResponse> getFilms(Long id) {
		final Optional<Film> film =  filmRepository.findById(id);
		if(film.isEmpty()) {
			return Optional.empty();
	    }		
		return Optional.of(filmMapper.mapToFilmGetResponse(film.get()));
	}
	
	public FilmGetResponse saveFilm(FilmPostRequestBody film) {
		return filmMapper.mapToFilmGetResponse(filmRepository.save(filmMapper.mapToFilm(film))) ;
	}
}
