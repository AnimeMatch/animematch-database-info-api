package animatch.app.controller;

import animatch.app.domain.Anime;
import animatch.app.domain.AnimeLista;
import animatch.app.dto.AnimeInfoDTO;
import animatch.app.repository.AnimeListaRepository;
import animatch.app.repository.AnimeRepository;
import animatch.app.repository.ListaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    AnimeListaRepository animeListaRepository;
    @Autowired
    ListaRepository listaRepository;

    @GetMapping("/")
    public ResponseEntity<List<AnimeInfoDTO>> getAnimes(){
        List<AnimeInfoDTO> animes = animeListaRepository.findAllInfo();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/{listaId}")
    public ResponseEntity<List<AnimeInfoDTO>> getAnimeListaRepository(@PathVariable int listaId) {
        List<AnimeInfoDTO> animes = animeListaRepository.findAllAnimeInfoByListaId(listaRepository.findListaById(listaId));
        return animes.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(animes);
    }

    @PostMapping("/")
    public ResponseEntity AdicionarAnimeLista(@RequestBody @Valid AnimeLista animeLista) {
        animeListaRepository.save(animeLista);
        return ResponseEntity.status(201).build();
    }
}
