package animatch.app.api.controller;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentarioAnime.ComentarioAnime;
import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.service.comentario.ComentarioService;
import animatch.app.service.comentarioAnime.ComentarioAnimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios-animes")
public class ComentarioAnimeController {

    @Autowired
    private ComentarioAnimeService comentarioService;

    @PostMapping("/{idMidiaApi}")
    public ResponseEntity<ComentarioAnime> criarComentario(@PathVariable int idMidiaApi, @RequestBody @Valid ComentarioAnime comentario) {
        return comentarioService.criarComentario(idMidiaApi,comentario);
    }

    @PostMapping("/{idComentarioPai}/comentar_comentario")
    public ResponseEntity<ComentarioAnime> criarComentarioFilho(@PathVariable int idComentarioPai, @RequestBody @Valid ComentarioAnime comentario) {
        return comentarioService.criarComentarioFilho(idComentarioPai,comentario);
    }

    @PatchMapping("/like/{idComentario}")
    public ResponseEntity darLike(@PathVariable int idComentario) {
        return comentarioService.darLike(idComentario);
    }

    @PatchMapping("/deslike/{idComentario}")
    public ResponseEntity darDeslike(@PathVariable int idComentario) {
        return comentarioService.darDeslike(idComentario);
    }

    @GetMapping("/{idMidiaApi}/lista_comentarios_animes")
    public ResponseEntity<List<ComentarioSimplesDTO>> getListaComentariosAnimes(@PathVariable int idMidiaApi){
        return comentarioService.getListaComentariosAnime(idMidiaApi);
    }

    @GetMapping("/{idComentario}/lista_comentarios_filhos")
    public ResponseEntity<List<ComentarioSimplesDTO>> getListaComentariosFilhos(@PathVariable int idComentario) {
        return comentarioService.getListaComentariosFilhos(idComentario);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<ComentarioAnime> getComentario(@PathVariable int idComentario) {
        return comentarioService.getComentario(idComentario);
    }

    @PatchMapping("/{idComentario}")
    public ResponseEntity<ComentarioAnime> atualizarTextoComentario(@PathVariable int idComentario, @RequestBody ComentarioAnime comentario) {
        return comentarioService.atualizarTextoComentario(idComentario,comentario);
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<ComentarioAnime> deletarComentario(@PathVariable int idComentario) {
        return comentarioService.deletarComentario(idComentario);
    }
}
