package com.devsuperior.movieflix.resource;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movie = service.findById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAllPaged(
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> page = service.findAllPaged(pageable, genreId);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<MovieDTO> insertReview(@PathVariable Long id) {
        return null;
    }
}
