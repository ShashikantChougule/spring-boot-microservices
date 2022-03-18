package com.training.moviedetailsservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.moviedetailsservice.model.Movie;
import com.training.moviedetailsservice.repository.MovieRepository;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
	
	@Autowired
	private MovieRepository movieRepository;
	
	private static Logger LOG = LoggerFactory.getLogger(MovieController.class.getName());

	@GetMapping("/movies/{movieId}")
	public ResponseEntity<Movie> getMovieDetailsById(@PathVariable int movieId) {
		
		LOG.info("Inside MovieController::getMovieDetailsById() method."); 
		
		Movie movie = movieRepository.findById(movieId).get();
		
		LOG.info("Inside MovieController: Response => " + movie);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);                        
	}
	
	@PostMapping("/movies")
	public ResponseEntity<Movie> addNewMovie(@RequestBody Movie movie) {
		
		Movie savedMovie = movieRepository.save(movie);
		return new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
	}
}







