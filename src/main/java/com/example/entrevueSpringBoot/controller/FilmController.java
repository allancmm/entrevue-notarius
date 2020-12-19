package com.example.entrevueSpringBoot.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entrevueSpringBoot.dto.request.FilmPostRequestBody;
import com.example.entrevueSpringBoot.dto.response.FilmGetResponse;
import com.example.entrevueSpringBoot.dto.response.FilmPostResponse;
import com.example.entrevueSpringBoot.service.FilmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * @author Allan Martins
 */
@RestController
@RequestMapping(path = "/api/film", produces = APPLICATION_JSON_VALUE)
public class FilmController {
			
	@Autowired
    private FilmService filmService;
		
	@Operation(summary = "Retourne Film basé sur ID")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Objet Film trouvé", 
	                                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, 
	                                    schema = @Schema(implementation = FilmGetResponse.class))}),
	                       @ApiResponse(responseCode = "404", description = "Film non-trouvé", content = @Content)}) 
	@GetMapping("/{id}")
	public ResponseEntity<FilmGetResponse> getFilm(@PathVariable("id") Long id){
		final Optional<FilmGetResponse> film =  filmService.getFilms(id);
		if(film.isEmpty()) {
			return ResponseEntity.notFound().build();
	    }		
         return new ResponseEntity<FilmGetResponse>(film.get(), HttpStatus.OK);
	}
	
	
	@Operation(summary = "Enregistre un Film")
	@ApiResponse(responseCode = "201", description = "Objet Film crée", 
	             content = {@Content(mediaType = APPLICATION_JSON_VALUE, 
	             schema = @Schema(implementation = FilmPostResponse.class))})
	@PostMapping(consumes = APPLICATION_JSON_VALUE) 
	public ResponseEntity<FilmGetResponse> postFilm(@Valid @RequestBody FilmPostRequestBody filmRequest){	
        return new ResponseEntity<FilmGetResponse>(filmService.saveFilm(filmRequest), HttpStatus.CREATED);
	}
	

}
