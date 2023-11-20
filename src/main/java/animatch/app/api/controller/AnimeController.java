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
    AnimeRepository repository;

    @Autowired
    AnimeService service;

    @GetMapping("/")
    public ResponseEntity<List<Anime>> getInstance() {
        List<Anime> animes = repository.findAll();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<Anime>> getOrdenadosPelaNota() {
        List<Anime> animes = repository.findAll();
        if (animes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        for (int i = 0; i < animes.size() - 1; i++) {
            for (int j = 0; j < animes.size() - 1; j++) {
                if (animes.get(j).getNotaMedia() > animes.get(j+1).getNotaMedia()) {
                    var aux = animes.get(j);
                    animes.set(j,animes.get(j+1));
                    animes.set(j+1,aux);
                }
            }
        }
        return ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/mais-likes")
    public ResponseEntity<List<Anime>> getMaisLikes() {
        List<Anime> animes = repository.findAllOrderByLikes();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @PostMapping("/dados-complementares")
    public  ResponseEntity<AnimeDadosComplementaresDto> dadosComplementares(@RequestBody AnimeIdDto id){
        try{
            Integer like = repository.qtdDeslikesAnime(id.getId());
            Integer deslike = repository.qtdLikesAnime(id.getId());
            Integer assistido = repository.qtdAssistido(id.getId());
            AnimeDadosComplementaresDto retorno = new AnimeDadosComplementaresDto(
                    like,
                    deslike,
                    assistido);
            return ResponseEntity.status(200).body(retorno);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "%s".formatted(e));
        }
    }

    @PostMapping("/")
    public ResponseEntity postAnimes(@RequestBody Anime anime) {
        repository.save(anime);
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

