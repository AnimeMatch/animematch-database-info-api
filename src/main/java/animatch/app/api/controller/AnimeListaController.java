package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.service.AnimeLista.dto.AnimeListaInfoDTO;
import animatch.app.service.AnimeLista.AnimeListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import animatch.app.utils.ListaObj;
import java.util.List;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    private AnimeListaRepository repository;
    @Autowired
    private AnimeListaService service;

    @GetMapping("/")
    public ResponseEntity<List<AnimeLista>> getAnimes(){
//        ListaObj<Anime> lista = service.vetorDeAnimes();
        List<AnimeLista> lista = service.receberAnimes();
//        return lista.getTamanho() == 0 ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
        return lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/animes-e-listas-do-usuario")
    public ResponseEntity<List<AnimeLista>> getAnimesLista(@RequestParam int userId){
        List<AnimeLista> animes = service.animeListaPorUsuario(userId);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/animes-e-listas-do-usuario-paginado")
    public ResponseEntity<List<AnimeLista>> getAnimesListaPaginacao(@RequestParam int userId, @RequestParam int paginacao){
        List<AnimeLista> animes = service.animeListaPorUsuarioPaginado(userId, paginacao);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/animes-da-lista")
    public ResponseEntity<List<Anime>> getAnimeLista(@RequestParam int listaId) {
        List<Anime> animes = service.receberAnimesDeUmaLista(listaId);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }
//    public ResponseEntity<Anime[]> getAnimeLista(@RequestParam int listaId) {
//        ListaObj<Anime> animes = service.receberAnimesDeUmaLista(listaId);
//        return animes.getTamanho() == 0 ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes.getLista());
//    }

    @GetMapping("/animes-da-lista-paginado")
    public ResponseEntity<List<Anime>> getAnimeListaPaginacao(@RequestParam int listaId, @RequestParam int paginacao) {
        List<Anime> animes = service.recebreAnimesDeUmaListaPaginado(listaId, paginacao);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @PostMapping("/")
    public ResponseEntity AdicionarAnimeLista(@RequestParam int idApi, @RequestParam int idLista) {
        service.salvarAnimeLista(idApi, idLista);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/")
    public ResponseEntity deleteAnimeLista(@RequestParam int animeListaId){
        if (repository.existsById(animeListaId)) {
            repository.deleteById(animeListaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
