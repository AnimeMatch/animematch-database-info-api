package animatch.app.api.controller;

import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.Topico;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.dto.ComentarioSimplesDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping("/{idTopico}")
    public ResponseEntity<Comentario> criarComentario(@PathVariable int idTopico, @RequestBody @Valid Comentario comentario) {
        Topico topico = new Topico();
        if (topicoRepository.existsById(idTopico)) {
            topico = topicoRepository.findTopicoById(idTopico);
        } else {
            return ResponseEntity.status(404).build();
        }
        comentario.setTopico(topico);
        comentarioRepository.save(comentario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/anime/{idAnime}")
    public ResponseEntity<Comentario> criarComentarioAnime(@PathVariable int idAnime, @RequestBody @Valid Comentario comentario) {
        var topico = topicoRepository.findTopicoById(9999);
        comentario.setTopico(topico);
        comentario.setIdAnimeApi(idAnime);
        comentarioRepository.save(comentario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{idComentarioPai}/comentar_comentario")
    public ResponseEntity<Comentario> criarComentarioFilho(@PathVariable int idComentarioPai, @RequestBody @Valid Comentario comentario) {
        Comentario comentarioPai = new Comentario();
        if (comentarioRepository.existsById(idComentarioPai)) {
            comentarioPai = comentarioRepository.findComentarioById(idComentarioPai);
        } else {
            return ResponseEntity.status(404).build();
        }
        comentario.setTopico(comentarioPai.getTopico());
        comentario.setComentario(comentarioPai);
        comentarioRepository.save(comentario);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{idComentario}/lista_comentarios_filhos")
    public ResponseEntity<List<ComentarioSimplesDTO>> getListaComentariosFilhos(@PathVariable int idComentario) {
        List<ComentarioSimplesDTO> comentarioSimplesDTOS;
        comentarioSimplesDTOS = comentarioRepository.findBySimplesComentarioPaiId(idComentario);

        return comentarioSimplesDTOS.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(comentarioSimplesDTOS);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<Comentario> getComentario(@PathVariable int idComentario) {
        Comentario comentario;
        if (comentarioRepository.existsById(idComentario)) {
            comentario = comentarioRepository.findComentarioById(idComentario);
        } else {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(comentario);
    }

    @PatchMapping("/{idComentario}")
    public ResponseEntity<Comentario> atualizarTextoComentario(@PathVariable int idComentario, @RequestBody Comentario comentario) {
        Comentario comentarioExistente;
        if (comentarioRepository.existsById(idComentario)) {
            comentarioExistente = comentarioRepository.findComentarioById(idComentario);
        } else {
            return ResponseEntity.status(404).build();
        }
        comentarioExistente.setTexto(comentario.getTexto());
        comentarioRepository.save(comentarioExistente);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Comentario> deletarComentario(@PathVariable int idComentario) {
        List<Comentario> comentarios;
        if (comentarioRepository.existsById(idComentario)) {
            comentarios = comentarioRepository.findByComentarioPaiId(idComentario);
            if (comentarios.isEmpty()) {
                comentarioRepository.deleteById(idComentario);
            } else {
                comentarioRepository.deleteAll(comentarios);
                comentarioRepository.deleteById(idComentario);
            }
        } else {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }
}
