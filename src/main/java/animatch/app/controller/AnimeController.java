package animatch.app.controller;

import animatch.app.domain.Anime;
import animatch.app.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    // ONLY FOR TESTS
    @Autowired
    AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<List<Anime>> getInstance() {
        List<Anime> animes = animeRepository.findAll();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @PostMapping("/")
    public ResponseEntity postAnimes(@RequestBody Anime anime) {
        animeRepository.save(anime);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{animeId}")
    public ResponseEntity deleteAnime(@PathVariable int animeId){
        if (animeRepository.existsById(animeId)) {
            animeRepository.deleteById(animeId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}

