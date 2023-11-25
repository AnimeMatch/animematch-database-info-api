package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.service.Anime.dto.AnimeDadosComplementaresDto;
import animatch.app.service.Anime.dto.AnimeIdDto;
import animatch.app.service.Anime.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    private AnimeRepository repository;

    @Autowired
    private AnimeService service;

    @GetMapping("/")
    public ResponseEntity<List<Anime>> getInstance() {
        List<Anime> animes = repository.findAll();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<Anime>> getOrdenadosPelaNota() {
        List<Anime> animes = service.ordenarPelaNota();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/mais-likes")
    public ResponseEntity<List<Anime>> getMaisLikes() {
        List<Anime> animes = repository.findAllByOrderByLikesDesc();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/dados-complementares")
    public  ResponseEntity<AnimeDadosComplementaresDto> dadosComplementares(@RequestParam int id){
        return ResponseEntity.status(200).body(service.dadosComplementares(id));
    }

    @PostMapping("/")
    public ResponseEntity postAnimes(@RequestParam int idApi) {
        service.salvarAnime(idApi);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/like")
    public ResponseEntity darLike(@RequestParam int idApi){
        HttpStatus status = service.darLike(idApi);
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping("/{animeId}")
    public ResponseEntity deleteAnime(@PathVariable int animeId){
        if (repository.existsById(animeId)) {
            repository.deleteById(animeId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}

