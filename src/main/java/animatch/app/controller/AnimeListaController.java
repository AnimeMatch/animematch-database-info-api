package animatch.app.controller;

import animatch.app.domain.Anime;
import animatch.app.domain.AnimeLista;
import animatch.app.domain.Lista;
import animatch.app.dto.AnimeInfoDTO;
import animatch.app.dto.AnimeListaInfoDTO;
import animatch.app.repository.AnimeListaRepository;
import animatch.app.repository.AnimeRepository;
import animatch.app.repository.ListaRepository;
import animatch.app.repository.UsuarioRepository;
import animatch.app.utils.ListaObj;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    AnimeListaRepository animeListaRepository;
    @Autowired
    ListaRepository listaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public ResponseEntity<Object> getAnimes(){
        List<AnimeInfoDTO> animes = animeListaRepository.findAllInfo();
        ListaObj lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @GetMapping("/animes-e-lista/{userId}")
    public ResponseEntity<List<AnimeListaInfoDTO>> getAnimesLista(@PathVariable int userId){
        List<AnimeListaInfoDTO> animes = animeListaRepository.findAllAnimeListaInfoByUserId(usuarioRepository.findUserById(userId));
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/animes-e-lista/{userId}/{paginacao}")
    public ResponseEntity<List<AnimeListaInfoDTO>> getAnimesListaPaginacao(@PathVariable int userId, @PathVariable int paginacao){
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeListaInfoDTO> animes = animeListaRepository.findAllAnimeListaInfoByUserIdPaginacao(usuarioRepository.findUserById(userId), pageable);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/{listaId}")
    public ResponseEntity<Object> getAnimeLista(@PathVariable int listaId) {
        List<AnimeInfoDTO> animes = animeListaRepository.findAllAnimeInfoByListaId(listaId);
        ListaObj lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @GetMapping("/{listaId}/{paginacao}")
    public ResponseEntity<Object> getAnimeListaPaginacao(@PathVariable int listaId, @PathVariable int paginacao) {
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeInfoDTO> animes = animeListaRepository.findAllAnimePaginadoInfoByListaId(listaId, pageable);
        ListaObj lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @PostMapping("/")
    public ResponseEntity AdicionarAnimeLista(@RequestBody @Valid AnimeLista animeLista) {
        animeListaRepository.save(animeLista);
        return ResponseEntity.status(201).build();
    }
}
