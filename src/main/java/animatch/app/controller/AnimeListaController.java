package animatch.app.controller;

import animatch.app.repository.AnimeListaRepository;
import animatch.app.repository.ListaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    AnimeListaRepository animeListaRepository;

    @GetMapping("/{idLista}")
    public AnimeListaRepository getAnimeListaRepository() {
        return animeListaRepository;
    }

    @PostMapping("/")
    public ResponseEntity setAnimeListaRepository(@RequestBody @Valid AnimeListaRepository animeListaRepository) {

    }

}
