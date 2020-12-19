package com.example.entrevueSpringBoot.mapper;

import org.mapstruct.Mapper;

import com.example.entrevueSpringBoot.dto.request.FilmPostRequestBody;
import com.example.entrevueSpringBoot.dto.response.FilmGetResponse;
import com.example.entrevueSpringBoot.dto.response.FilmPostResponse;
import com.example.entrevueSpringBoot.model.Film;

@Mapper(componentModel = "spring") 
public interface FilmMapper {	
	public Film mapToFilm(FilmPostRequestBody film);
	public FilmGetResponse mapToFilmGetResponse(Film film);
	public FilmPostResponse matToFilPostResponse(Film film);
}