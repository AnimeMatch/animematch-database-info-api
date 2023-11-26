package animatch.app.api.controller;

import animatch.app.domain.anime.Anime;
import animatch.app.domain.anime.repository.AnimeRepository;
import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.ComentarioSimplesDTO;
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

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public ResponseEntity<List<Anime>> getInstance() {
        List<Anime> animes = animeRepository.findAll();
        return animes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(animes);
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<Anime>> getOrdenadosPelaNota() {
        List<Anime> animes = animeRepository.findAll();
        if (animes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        for (int i = 0; i < animes.size() - 1; i++) {
            for (int j = 0; j < animes.size() - 1; j++) {
                if (animes.get(j).getNotaMedia() > animes.get(j + 1).getNotaMedia()) {
                    var aux = animes.get(j);
                    animes.set(j, animes.get(j + 1));
                    animes.set(j + 1, aux);
                }
            }
        }
        return ResponseEntity.status(200).body(animes);
    }

    @PostMapping("/")
    public ResponseEntity postAnimes(@RequestBody Anime anime) {
        animeRepository.save(anime);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{animeId}")
    public ResponseEntity deleteAnime(@PathVariable int animeId) {
        if (animeRepository.existsById(animeId)) {
            animeRepository.deleteById(animeId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/comentarios-anime/{animeId}")
    public ResponseEntity<List<ComentarioSimplesDTO>> getComentarios(@PathVariable int animeId) {
        List<ComentarioSimplesDTO> comentariosDtos;
        if (animeRepository.existsByIdApi(animeId)) {
            comentariosDtos = comentarioRepository.findAllComentariosByIdAnimeApi(animeId);
            var comentarios = comentarioRepository.findByIdAnimeApiAndComentarioPai(animeId,null);
            var cont = 0;
            for (Comentario comentario : comentarios
            ) {
                var userComentario = usuarioRepository.findUserByIdDtoSimples(comentario.getIdUsuario());
                comentariosDtos.get(cont++).setUsuarioSimplesDto(userComentario);
            }
        } else {
            return ResponseEntity.status(404).build();
        }
        if (comentariosDtos.isEmpty()) {
            return ResponseEntity.status(204).body(comentariosDtos);
        }
        return ResponseEntity.status(200).body(comentariosDtos);
    }
}

