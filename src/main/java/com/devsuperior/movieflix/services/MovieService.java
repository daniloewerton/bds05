package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    public MovieDTO findById(Long id) {
        Optional<Movie> movie = repository.findById(id);
        if (movie.isPresent()) {
            return new MovieDTO(movie.get(), movie.get().getReviews());
        }
        throw new ResourceNotFoundException("Filme não encontrado.");
    }

    public Page<MovieDTO> findAllPaged(Pageable pageable, Long genreId) {
        Genre genre = genreId != 0 ? genreRepository.findById(genreId).get() : null;
        Page<Movie> page = repository.find(pageable, genre);
        return page.map(MovieDTO::new);
    }
}
