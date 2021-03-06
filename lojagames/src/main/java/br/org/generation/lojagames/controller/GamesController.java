package br.org.generation.lojagames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojagames.model.Games;
import br.org.generation.lojagames.repository.GamesRepository;

@RestController
@RequestMapping("/games")
@CrossOrigin (origins = "*", allowedHeaders = "*")

public class GamesController {
	
	@Autowired
	private GamesRepository gamesRepository;
	
	@GetMapping
	public ResponseEntity <List<Games>> getAll() {
		return ResponseEntity.ok(gamesRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Games> getById (@PathVariable Long id) {
		return gamesRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("titulo/{titulo}")
	public ResponseEntity <List<Games>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(gamesRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity <Games> postGames(@Valid @RequestBody Games games) {
		return ResponseEntity.status(HttpStatus.CREATED).body(gamesRepository.save(games));
	}
	
	@PutMapping
	public ResponseEntity <Games> putGames(@Valid @RequestBody Games games){ 
		return ResponseEntity.status(HttpStatus.OK).body(gamesRepository.save(games));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		gamesRepository.deleteById(id);
	}
	

	
}
