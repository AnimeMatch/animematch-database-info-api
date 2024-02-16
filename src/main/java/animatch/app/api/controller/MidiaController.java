package animatch.app.api.controller;

import animatch.app.domain.midia.Midia;
import animatch.app.domain.midia.repository.MidiaRepository;
import animatch.app.domain.comentario.Comentario;
import animatch.app.domain.comentario.repository.ComentarioRepository;
import animatch.app.domain.topico.repository.TopicoRepository;
import animatch.app.domain.usuario.repository.UsuarioRepository;
import animatch.app.dto.ComentarioSimplesDTO;
import animatch.app.service.Midia.MidiaService;
import animatch.app.service.Midia.dto.MidiaDadosComplementaresDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midia")
public class MidiaController {
    @Autowired
    private MidiaRepository repository;

    @Autowired
    private MidiaService service;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public ResponseEntity<List<Midia>> getInstance() {
        List<Midia> midias = repository.findAll();
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }

    @GetMapping("/ordenados-pela-nota")
    public ResponseEntity<List<Midia>> getOrdenadosPelaNota() {
        List<Midia> midias = service.ordenarPelaNota();
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }

    @GetMapping("/mais-likes")
    public ResponseEntity<List<Midia>> getMaisLikes() {
        List<Midia> midias = repository.findAllByOrderByLikesDesc();
        return midias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(midias);
    }

    @GetMapping("/dados-complementares")
    public ResponseEntity<MidiaDadosComplementaresDto> dadosComplementares(@RequestParam int id) {
        return ResponseEntity.status(200).body(service.dadosComplementares(id));
    }

    @PostMapping("/")
    public ResponseEntity postMidias(@RequestParam int idApi) {
        service.salvarMidia(idApi);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/like")
    public ResponseEntity darLike(@RequestParam int idApi) {
        HttpStatus status = service.darLike(idApi);
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping("/{midiaId}")
    public ResponseEntity deleteMidia(@PathVariable int midiaId) {
        if (repository.existsById(midiaId)) {
            service.deleteMidia(midiaId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/comentarios-midia/{midiaId}")
    public ResponseEntity<List<ComentarioSimplesDTO>> getComentarios(@PathVariable int midiaId) {
        List<ComentarioSimplesDTO> comentariosDtos;

        comentariosDtos = comentarioRepository.findAllComentariosByIdMidiaApi(midiaId);
        var comentarios = comentarioRepository.findByIdMidiaApiAndComentarioPai(midiaId, null);
        var cont = 0;
        for (Comentario comentario : comentarios
        ) {
            var userComentario = usuarioRepository.findUserByEmailDtoSimples(comentario.getEmailUsuario());
            var qtdComentariosFilhos = comentarioRepository.countByComentarioPaiId(comentario.getId());
            comentariosDtos.get(cont).setUsuarioSimplesDto(userComentario);
            comentariosDtos.get(cont).setQtdComentariosFilhos(qtdComentariosFilhos);
            cont++;
        }
        if (comentariosDtos.isEmpty()) {
            return ResponseEntity.status(204).body(comentariosDtos);
        }
        return ResponseEntity.status(200).body(comentariosDtos);
    }
}

