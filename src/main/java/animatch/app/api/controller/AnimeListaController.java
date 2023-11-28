package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.animelista.AnimeLista;
import animatch.app.domain.animelista.repository.AnimeListaRepository;
import animatch.app.service.AnimeLista.AnimeListaService;
import animatch.app.utils.FilaObj;
import animatch.app.utils.PilhaObj;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/anime-lista")
public class AnimeListaController {
    @Autowired
    private AnimeListaRepository repository;
    @Autowired
    private AnimeListaService service;
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
           var obj = repository.save(filaObj.poll());
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

    @DeleteMapping("/")
    public ResponseEntity deleteAnimeLista(@RequestParam int animeListaId){
        if (repository.existsById(animeListaId)) {
            repository.deleteById(animeListaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
