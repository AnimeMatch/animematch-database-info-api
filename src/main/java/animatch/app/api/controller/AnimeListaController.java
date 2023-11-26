package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.domain.lista.repository.ListaRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.AnimeInfoDTO;
import animatch.app.dto.AnimeListaInfoDTO;
import animatch.app.utils.FilaObj;
import animatch.app.utils.ListaObj;
import animatch.app.utils.PilhaObj;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    FilaObj<AnimeLista> filaObj = new FilaObj<>(10);

    PilhaObj<AnimeLista> pilhaObj = new PilhaObj<>(10);

//    @GetMapping("/")
//    public ResponseEntity<Anime[]> getAnimes() {
//        List<AnimeInfoDTO> animes = animeListaRepository.findAllInfo();
//        ListaObj<Anime> lista = new ListaObj<Anime>(animes.size());
//        for (int i = 0; i < animes.size(); i++) {
//            lista.adiciona(animes.get(i).getAnime());
//        }
//        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
//    }
    @GetMapping("/")
    public ResponseEntity<List<AnimeLista>> getAnimes(){
        return ResponseEntity.status(200).body(animeListaRepository.findAll());
    }
    @GetMapping("/animes-e-lista/{userId}")
    public ResponseEntity<List<AnimeListaInfoDTO>> getAnimesLista(@PathVariable int userId) {
        List<AnimeListaInfoDTO> animes = animeListaRepository.findAllAnimeListaInfoByUserId(usuarioRepository.findUserById(userId));
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/animes-e-lista/{userId}/{paginacao}")
    public ResponseEntity<List<AnimeListaInfoDTO>> getAnimesListaPaginacao(@PathVariable int userId, @PathVariable int paginacao) {
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeListaInfoDTO> animes = animeListaRepository.findAllAnimeListaInfoByUserIdPaginacao(usuarioRepository.findUserById(userId), pageable);
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/{listaId}")
    public ResponseEntity<List<AnimeLista>> getAnimeLista(@PathVariable int listaId) {
        var lista = listaRepository.findListaById(listaId);
        var animesEncontradoLista = animeListaRepository.findByListaId(lista);
        return ResponseEntity.status(200).body(animesEncontradoLista);
    }

    @GetMapping("/{listaId}/{paginacao}")
    public ResponseEntity<Anime[]> getAnimeListaPaginacao(@PathVariable int listaId, @PathVariable int paginacao) {
        Pageable pageable = PageRequest.of(0, paginacao);
        List<AnimeInfoDTO> animes = animeListaRepository.findAllAnimePaginadoInfoByListaId(listaId, pageable);
        ListaObj<Anime> lista = new ListaObj(animes.size());
        for (int i = 0; i < animes.size(); i++) {
            lista.adiciona(animes.get(i).getAnime());
        }
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista.getLista());
    }

    @PostMapping("/")
    public ResponseEntity adicionarAnimeLista(@RequestBody @Valid AnimeLista animeLista) {
        animeListaRepository.save(animeLista);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/adicionar-anime-fila-espera")
    public ResponseEntity adicionarAnimeFila(@RequestBody @Valid AnimeLista animeLista) {
        if (filaObj.isFull()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fila cheia, salve os animes na lista escolhida");
        }
        filaObj.insert(animeLista);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/esvaziar-fila-de-espera")
    public ResponseEntity esvaziarFila() {
        while (!filaObj.isEmpty()){
           var obj = animeListaRepository.save(filaObj.poll());
            pilhaObj.push(obj);
        }
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/exibir-anime-pilha")
    public ResponseEntity<AnimeLista> exibirAnimePilha() {
        if (pilhaObj.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        var animePilhaTopo = pilhaObj.pop();
        return ResponseEntity.status(200).body(animePilhaTopo);
    }
//    @PutMapping("/")
//    public ResponseEntity<>

    @DeleteMapping("/{animeListaId}")
    public ResponseEntity deleteAnimeLista(@PathVariable int animeListaId) {
        if (animeListaRepository.existsById(animeListaId)) {
            animeListaRepository.deleteById(animeListaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
