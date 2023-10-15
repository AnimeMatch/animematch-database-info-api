package animatch.app.controller;

import animatch.app.domain.Comentario;
import animatch.app.domain.Topico;
import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.repository.ComentarioRepository;
import animatch.app.repository.TopicoRepository;
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
        return ResponseEntity.status(201).body(comentario);
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
        comentarioSimplesDTOS = comentarioRepository.findByComentarioPaiId(idComentario);

        return comentarioSimplesDTOS.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(comentarioSimplesDTOS);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<Comentario> getComentario(@PathVariable int idComentario) {
        Comentario comentario;
        comentario = comentarioRepository.findComentarioById(idComentario);
        return ResponseEntity.status(200).body(comentario);
    }
}
