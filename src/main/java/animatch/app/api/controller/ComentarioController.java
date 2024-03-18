package animatch.app.api.controller;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.Topico;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.service.comentario.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/{idTopico}")
    public ResponseEntity<Comentario> criarComentario(@PathVariable int idTopico, @RequestBody @Valid Comentario comentario) {
        return comentarioService.criarComentario(idTopico,comentario);
    }

//    @PostMapping("/midia/{idApi}")
//    public ResponseEntity<Comentario> criarComentarioMidia(@PathVariable int idApi, @RequestBody @Valid Comentario comentario) {
//        var topico = topicoRepository.findTopicoById(9999);
//        comentario.setTopico(topico);
//        comentario.setIdMidiaApi(idApi);
//        comentarioRepository.save(comentario);
//        return ResponseEntity.status(201).build();
//    }

    @PostMapping("/{idComentarioPai}/comentar_comentario")
    public ResponseEntity<Comentario> criarComentarioFilho(@PathVariable int idComentarioPai, @RequestBody @Valid Comentario comentario) {
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

    @GetMapping("/{idComentario}/lista_comentarios_filhos")
    public ResponseEntity<List<ComentarioSimplesDTO>> getListaComentariosFilhos(@PathVariable int idComentario) {
        return comentarioService.getListaComentariosFilhos(idComentario);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<Comentario> getComentario(@PathVariable int idComentario) {
        return comentarioService.getComentario(idComentario);
    }

    @PatchMapping("/{idComentario}")
    public ResponseEntity<Comentario> atualizarTextoComentario(@PathVariable int idComentario, @RequestBody Comentario comentario) {
        return comentarioService.atualizarTextoComentario(idComentario,comentario);
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Comentario> deletarComentario(@PathVariable int idComentario) {
        return comentarioService.deletarComentario(idComentario);
    }
}
