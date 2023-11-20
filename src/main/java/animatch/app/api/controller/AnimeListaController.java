package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.service.Anime.dto.AnimeInfoDTO;
import animatch.app.service.Anime.dto.AnimeListaInfoDTO;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.service.AnimeLista.AnimeListaService;
import animatch.app.utils.ListaObj;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    AnimeListaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AnimeListaService service;

    @GetMapping("/")
    public ResponseEntity<Anime[]> getAnimes(){
        List<AnimeInfoDTO> animes = repository.findAllInfo();
        ListaObj<Anime> lista = new ListaObj<Anime>(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @GetMapping("/animes-e-lista/{userId}")
    public ResponseEntity<List<AnimeListaInfoDTO>> getAnimesLista(@PathVariable int userId){
        List<AnimeListaInfoDTO> animes = repository.findAllAnimeListaInfoByUserId(usuarioRepository.findUserById(userId));
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/animes-e-lista/{userId}/{paginacao}")
    public ResponseEntity<List<AnimeListaInfoDTO>> getAnimesListaPaginacao(@PathVariable int userId, @PathVariable int paginacao){
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeListaInfoDTO> animes = repository.findAllAnimeListaInfoByUserIdPaginacao(usuarioRepository.findUserById(userId), pageable);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/{listaId}")
    public ResponseEntity<Anime[]> getAnimeLista(@PathVariable int listaId) {
        List<AnimeInfoDTO> animes = repository.findAllAnimeInfoByListaId(listaId);
        ListaObj<Anime> lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @GetMapping("/{listaId}/{paginacao}")
    public ResponseEntity<Anime[]> getAnimeListaPaginacao(@PathVariable int listaId, @PathVariable int paginacao) {
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeInfoDTO> animes = repository.findAllAnimePaginadoInfoByListaId(listaId, pageable);
        ListaObj<Anime> lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @PostMapping("/")
    public ResponseEntity AdicionarAnimeLista(@RequestParam int idApi, @RequestParam int idLista) {
        service.salvarAnimeLista(idApi, idLista);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{animeListaId}")
    public ResponseEntity deleteAnimeLista(@PathVariable int animeListaId){
        if (repository.existsById(animeListaId)) {
            repository.deleteById(animeListaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
