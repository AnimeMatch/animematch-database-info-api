package animatch.app.api.controller;

import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.dto.TopicoAdicionalListaComentariosDto;
import animatch.app.model.Topico;
import animatch.app.repository.ComentarioRepository;
import animatch.app.repository.TopicoRepository;
import animatch.app.domain.topico.Topico;
import animatch.app.domain.topico.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid Topico topico) {
        topicoRepository.save(topico);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<Topico>> getListaTopicos() {
        var topicos = topicoRepository.findAll();

        return topicos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(topicos);
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<TopicoAdicionalListaComentariosDto> getTopico(@PathVariable int idTopico) {
        TopicoAdicionalListaComentariosDto topico = new TopicoAdicionalListaComentariosDto();
        if (topicoRepository.existsById(idTopico)) {
           var topicoSimples = topicoRepository.findTopicoById(idTopico);
           var comentario = comentarioRepository.findAllComentariosByTopico(idTopico);
           topico.setId(topicoSimples.getId());
           topico.setTitulo(topicoSimples.getTitulo());
           topico.setComentarios(comentario);
        } else {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(topico);
    }
}
