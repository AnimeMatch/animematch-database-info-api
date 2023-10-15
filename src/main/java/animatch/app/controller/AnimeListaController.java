package animatch.app.controller;

import animatch.app.model.Anime;
import animatch.app.model.AnimeLista;
import animatch.app.dto.AnimeInfoDTO;
import animatch.app.repository.AnimeListaRepository;
import animatch.app.repository.ListaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    AnimeListaRepository animeListaRepository;
    @Autowired
    ListaRepository listaRepository;

    @GetMapping("/")
    public ResponseEntity<List<Anime>> getAnimes(){
        List<AnimeInfoDTO> animes = animeListaRepository.findAllInfo();
        List<Anime> list = new ArrayList<>();
        for (int i = 0; i < animes.size(); i++) {
            list.add(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{listaId}")
    public ResponseEntity<List<Anime>> getAnimeListaRepository(@PathVariable int listaId) {
        List<AnimeInfoDTO> animes = animeListaRepository.findAllAnimeInfoByListaId(listaId);
        List<Anime> list = new ArrayList<>();
        for (int i = 0; i < animes.size(); i++) {
            list.add(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(list);
    }

    @PostMapping("/")
    public ResponseEntity AdicionarAnimeLista(@RequestBody @Valid AnimeLista animeLista) {
        animeListaRepository.save(animeLista);
        return ResponseEntity.status(201).build();
    }
}
